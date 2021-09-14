package main.java.br.com.desafiogestao.service;

import main.java.br.com.desafiogestao.repository.PessoaDbRepository;
import main.java.br.com.desafiogestao.repository.PessoaRepository;
import main.java.br.com.desafiogestao.model.Pessoa;

import java.util.List;

public class PessoaService {

//    private final PessoaMemoryRepository pessoaRepository = new PessoaMemoryRepository();
//    private final PessoaDbRepository pessoaRepository = new PessoaDbRepository();
      private final PessoaRepository pessoaRepository = new PessoaDbRepository();

    public void salvarPessoa(Pessoa pessoa) {

        pessoaRepository.salvarPessoa(pessoa);

    }

    public Pessoa getPessoaById(int id) {

        return pessoaRepository.getPessoaById(id);

    }

    public void excluirPessoa(int id) {

        pessoaRepository.excluirPessoa(id);

    }

    public List<Pessoa> listarTodasPessoas() {

        return pessoaRepository.listarTodasPessoas();

    }


}
