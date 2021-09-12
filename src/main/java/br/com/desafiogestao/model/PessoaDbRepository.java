package main.java.br.com.desafiogestao.model;

import main.java.br.com.desafiogestao.connection.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDbRepository implements PessoaRepository {

    public void salvarPessoa(Pessoa pessoa) {

        Conexao con = new Conexao();

        try {

            String existQuery = "SELECT EXISTS(SELECT FROM pessoas WHERE id = ? );";
            PreparedStatement preparedStatement = con.returnConnection().prepareStatement(existQuery);
            preparedStatement.setInt(1, pessoa.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                if(resultSet.getBoolean("exists")) {

                    String sql = "UPDATE pessoas SET nome = ?, cpf = ? WHERE id = ?;";
                    PreparedStatement insidePreparedStatement = con.returnConnection().prepareStatement(sql);
                    insidePreparedStatement.setString(1, pessoa.getNome());
                    insidePreparedStatement.setString(2, pessoa.getCpf());
                    insidePreparedStatement.setInt(3, pessoa.getId());
                    int res = insidePreparedStatement.executeUpdate();
                    if(res > 0) {
                        System.out.println(pessoa.getNome() + " foi editado!");
                    } else {
                        System.out.println("A edição não pôde ser realizada.");
                    }

                } else if (!resultSet.getBoolean("exists")){
                    String sql = "INSERT INTO pessoas(nome, cpf) VALUES('" + pessoa.getNome() +"', '" + pessoa.getCpf() + "');";
                    int res = con.executaSQL(sql);
                    if(res > 0) {
                        System.out.println(pessoa.getNome() + " foi inserido(a) ao banco!");
                    } else {
                        System.out.println("A pessoa não pôde ser adicionada.");
                    }
                }
            }


        } catch (SQLException e) {

            System.out.println("Código inválido");
            e.printStackTrace();

        }

    }

    public Pessoa getPessoaById(Integer id) {

        String sql = "SELECT id, nome, cpf FROM pessoas WHERE id = " + id;
        Pessoa pessoa = retornaPessoaSQL(sql);
        return pessoa;

    }

    public void excluirPessoa(int id) {

        Conexao con = new Conexao();
        String sql = "DELETE FROM pessoas WHERE id = " + id;
        Pessoa pessoa = getPessoaById(id);
        int res = con.executaSQL(sql);
        if(res > 0) {
            System.out.println("Você excluiu " + pessoa.getNome());
        }
    }

    private Pessoa retornaPessoaSQL(String sql) {

        try {

            Conexao con = new Conexao();
            Statement stm = con.returnConnection().createStatement();
            ResultSet resultSet = stm.executeQuery(sql);

            Pessoa pessoa = null;

            while (resultSet.next()) {

                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                int id = resultSet.getInt("id");

                pessoa = new Pessoa(id, nome, cpf);

            }

            resultSet.close();
            stm.close();
            con.returnConnection().close();

            return pessoa;

        } catch (Exception e) {

            System.out.println("Deu ruim");
            e.printStackTrace();

        }

        return null;
    }

    public List<Pessoa> listarTodasPessoas() {

        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "Select id, nome, cpf from pessoas";

        try {
            Conexao con = new Conexao();
            Statement stm = con.returnConnection().createStatement();
            ResultSet resultSet =  stm.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                Pessoa pessoa = new Pessoa(id, nome, cpf);
                pessoas.add(pessoa);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pessoas;
    }

}
