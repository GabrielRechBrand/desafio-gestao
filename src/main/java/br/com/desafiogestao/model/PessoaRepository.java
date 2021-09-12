package main.java.br.com.desafiogestao.model;

import main.java.br.com.desafiogestao.model.Pessoa;

import java.util.List;

public interface PessoaRepository {

    void salvarPessoa(Pessoa pessoa);
    Pessoa getPessoaById(Integer id);
    void excluirPessoa(int id);
    List<Pessoa> listarTodasPessoas();

}
