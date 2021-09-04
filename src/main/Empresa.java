package main;

public class Empresa {

    private int id;
    private String nome;
    private String cnpj;
    private Pessoa presidente;

    public Empresa(String nome, String cnpj, Pessoa presidente) {

        this.nome = nome;
        this.cnpj = cnpj;
        this.presidente = presidente;

    }

    public Empresa(int id, String nome, String cnpj, Pessoa presidente) {

        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.presidente = presidente;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Pessoa getPresidente() {
        return presidente;
    }

    public void setPresidente(Pessoa presidente) {
        this.presidente = presidente;
    }
}
