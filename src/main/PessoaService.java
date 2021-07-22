package main;

import static main.PessoaMemoryRepository.listaTodasPessoas;

public class PessoaService {

    PessoaMemoryRepository pessoaMemoryRepository = new PessoaMemoryRepository();

    public void salvarPessoa(Pessoa pessoa) {

        pessoaMemoryRepository.salvarPessoa(pessoa);

    }

    public void getPessoaById(int id) {

        pessoaMemoryRepository.getPessoaById(id);

    }

    public void excluirPessoa(int id) {

        pessoaMemoryRepository.excluirPessoa(id);

    }

    public void listarTodasPessoas() {

        pessoaMemoryRepository.listarTodasPessoas();

    }


}
