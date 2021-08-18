package main;

import java.util.ArrayList;
import java.util.List;

public interface PessoaRepository {

    void salvarPessoa(Pessoa pessoa);
    Pessoa getPessoaById(Integer id);
    void excluirPessoa(int id);
    List<Pessoa> listarTodasPessoas();

}
