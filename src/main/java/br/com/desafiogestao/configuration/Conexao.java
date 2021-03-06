package main.java.br.com.desafiogestao.configuration;

import java.sql.*;

public class Conexao {

    private String url;
    private String usuario;
    private String senha;
    private Connection con;


    public Conexao() {

        url = "jdbc:postgresql://localhost:5432/desafio-gestao";
        usuario = "postgres";
        senha = "postgres";

        try {

            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, senha);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int executaSQL(String sql) {
        try {
            Statement stm = con.createStatement();
            int res = stm.executeUpdate(sql);
            con.close();
            return res;
        } catch (SQLException e) {
            return 0;
        }
    }

    public Connection returnConnection() {

        return this.con;

    }

}
