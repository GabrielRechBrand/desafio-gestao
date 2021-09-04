package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDbRepository {

    PessoaService pessoaService = new PessoaService();

    public void salvarEmpresa(Empresa empresa) {

        Conexao con = new Conexao();
        String sql = "INSERT INTO empresas(nome, cnpj, idPresidente) VALUES('" + empresa.getNome() + "', '" + empresa.getCnpj() + "', '" + empresa.getPresidente().getId() + "');";
        int res = con.executaSQL(sql);
        if(res > 0) {
            System.out.println("A empresa " + empresa.getNome() + " foi cadastrada com sucesso");
        } else {
            System.out.println("Erro durante o cadastro");
        }

    }

    public Empresa getEmpresaById(int id) {

        String sql = "SELECT idempresa, nome, cnpj, idpresidente FROM empresas WHERE idempresa='" + id + "'";
        Empresa empresa = retornaEmpresaSQL(sql);
        return empresa;

    }

    public void excluirEmpresa(int id) {

        Conexao con = new Conexao();
        String sql = "DELETE FROM empresas WHERE idempresa = " + pessoaService.getPessoaById(id).getId();
        con.executaSQL(sql);


    }

    public List<Empresa> listarTodasEmpresas() {

        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT idempresa, nome, cnpj, idpresidente";

        try {

            Conexao con = new Conexao();
            Statement statement = con.returnConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {

                int idEmpresa = resultSet.getInt("idempresa");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                int idPresidente = resultSet.getInt("idpresidente");

                Empresa empresa = new Empresa(idEmpresa, nome, cnpj, pessoaService.getPessoaById(idPresidente));
                empresas.add(empresa);

            }
        } catch (Exception e) {

        }

        return empresas;
    }

    private Empresa retornaEmpresaSQL(String sql) {

        try {

            Conexao con = new Conexao();
            Statement statement = con.returnConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            Empresa empresa = null;
            Pessoa presidente = null;

            while(resultSet.next()) {

                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                int idPresidente = resultSet.getInt("idPresidente");

                presidente = new Pessoa(idPresidente, pessoaService.getPessoaById(idPresidente).getNome(), pessoaService.getPessoaById(idPresidente).getCpf());
                empresa = new Empresa(nome, cnpj, presidente);

            }

            resultSet.close();
            statement.close();
            con.returnConnection().close();

            return empresa;

        } catch(SQLException e) {
            System.out.println("Deu ruim");
            e.printStackTrace();
        }

        return null;

    }

}
