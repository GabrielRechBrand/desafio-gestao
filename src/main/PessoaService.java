package main;

import java.util.List;

public class PessoaService {

    private final PessoaMemoryRepository pessoaMemoryRepository = new PessoaMemoryRepository();

    public void salvarPessoa(Pessoa pessoa) {

        pessoaMemoryRepository.salvarPessoa(pessoa);

    }

    public Pessoa getPessoaById(int id) {

        return pessoaMemoryRepository.getPessoaById(id);

    }

    public void excluirPessoa(int id) {

        pessoaMemoryRepository.excluirPessoa(id);

    }

    public List<Pessoa> listarTodasPessoas() {

        return pessoaMemoryRepository.listarTodasPessoas();
    }


}
