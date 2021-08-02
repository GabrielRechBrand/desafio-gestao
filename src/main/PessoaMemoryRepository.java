package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PessoaMemoryRepository {

        private static HashMap<Integer, Pessoa> listaTodasPessoas = new HashMap<>();

        public void salvarPessoa(Pessoa pessoa) {

                listaTodasPessoas.put(pessoa.getId(), pessoa);

        }
        
        public Pessoa getPessoaById(Integer id) {

                Pessoa pessoa = listaTodasPessoas.get(id);
                return pessoa;

        }       

        public void excluirPessoa(int id) {

                listaTodasPessoas.remove(id);

        }

        public List<Pessoa> listarTodasPessoas() {

                List<Pessoa> pessoas = new ArrayList<>();

                listaTodasPessoas.forEach((id, pessoa) -> {

                        pessoas.add(pessoa);

                });

                return pessoas;

        }



}
