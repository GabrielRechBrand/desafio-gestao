package main.java.br.com.desafiogestao.repository;

import main.java.br.com.desafiogestao.configuration.Conexao;
import main.java.br.com.desafiogestao.service.PessoaService;
import main.java.br.com.desafiogestao.model.Empresa;
import main.java.br.com.desafiogestao.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDbRepository {

    PessoaService pessoaService = new PessoaService();

    private void salvar(Empresa empresa) throws SQLException {

        Conexao con = new Conexao();
        String sql = "INSERT INTO empresas(nome, cnpj, idPresidente) VALUES(?, ?, ?);";
        PreparedStatement preparedStatement = con.returnConnection().prepareStatement(sql);
        preparedStatement.setString(1, empresa.getNome());
        preparedStatement.setString(2, empresa.getCnpj());
        preparedStatement.setInt(3, empresa.getPresidente() != null ? empresa.getPresidente().getId() : null);
        int res = preparedStatement.executeUpdate();
        if(res > 0) {
            System.out.println("A empresa " + empresa.getNome() + " foi cadastrada com sucesso");
        } else {
            System.out.println("Erro durante o cadastro");
        }
    }

    private void editar(Empresa empresa) throws SQLException {

        Conexao con = new Conexao();
        String sql = "UPDATE empresas SET nome = ?, idpresidente = ? WHERE idempresa = ?";
        PreparedStatement preparedStatement = con.returnConnection().prepareStatement(sql);
        preparedStatement.setString(1, empresa.getNome());
        if (empresa.getPresidente() != null) {
            preparedStatement.setInt(2, empresa.getPresidente().getId());
        } else if (empresa.getPresidente() == null) {
            preparedStatement.setNull(2, Types.NULL);
        }
        preparedStatement.setInt(3, empresa.getId());
        int res = preparedStatement.executeUpdate();
        if(res > 0) {
            System.out.println("A empresa foi editada");
        }
    }

    public void salvarEmpresa(Empresa empresa) {

        try {
            Empresa empresaExistente = getEmpresaById(empresa.getId());
            if(empresaExistente == null){
                salvar(empresa);
            } else {
                editar(empresa);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    public Empresa getEmpresaById(int id) {

        String sql = "SELECT idempresa, nome, cnpj, idpresidente FROM empresas WHERE idempresa='" + id + "'";
        Empresa empresa = retornaEmpresaSQL(sql);
        return empresa;

    }

    public void excluirEmpresa(int id) {

        Conexao con = new Conexao();
        Empresa empresa = getEmpresaById(id);

        try {
            String sql = "DELETE FROM empresas WHERE idempresa = ?;";
            PreparedStatement preparedStatement = con.returnConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Empresa> listarTodasEmpresas() {

        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT idempresa, nome, cnpj, idpresidente FROM empresas";

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
            e.printStackTrace();
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

                int idempresa = resultSet.getInt("idempresa");
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                int idPresidente = resultSet.getInt("idpresidente");

                if(pessoaService.getPessoaById(idPresidente) != null) {
                    presidente = new Pessoa(idPresidente, pessoaService.getPessoaById(idPresidente).getNome(), pessoaService.getPessoaById(idPresidente).getCpf());
                    empresa = new Empresa(idempresa, nome, cnpj, presidente);
                } else if(pessoaService.getPessoaById(idPresidente) == null) {
                    empresa = new Empresa(idempresa, nome, cnpj);
                }

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
