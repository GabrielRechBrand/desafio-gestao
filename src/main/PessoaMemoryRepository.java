package main;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Set;

public class PessoaMemoryRepository {

        public static HashMap<Integer, Pessoa> listaTodasPessoas = new HashMap<>();

        public void salvarPessoa(Pessoa pessoa) {

                listaTodasPessoas.put(pessoa.getId(), pessoa);

        }

        public void getPessoaById(Integer id) {

                try {
                        System.out.println("O ID ''" + listaTodasPessoas.get(id).getId() + "'' refere-se à pessoa " + listaTodasPessoas.get(id).getNome() + ", seu CPF é: " + listaTodasPessoas.get(id).getCpf());

                }
                catch(NullPointerException e) {
                        System.out.println("Essa pessoa não existe, tente novamente.");
                }


        }

        public void editarPessoa(int id) {

                try {
                        int n = JOptionPane.showInternalConfirmDialog(null, "Você quer editar a pessoa: " + listaTodasPessoas.get(id).getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                        String[] botoes = {"Nome", "CPF"};
                        switch (n) {
                                case 0:
                                        int editInput = JOptionPane.showOptionDialog(null, "O que você deseja editar?", "Tela edição", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoes, "default");
                                        switch(editInput) {
                                                case 0:
                                                        String nomeNovo = JOptionPane.showInputDialog("Digite o novo nome.");
                                                        listaTodasPessoas.get(id).setNome(nomeNovo);
                                                        break;
                                                case 1:
                                                        String cpfNovo = JOptionPane.showInputDialog("Digite o novo CPF.");
                                                        listaTodasPessoas.get(id).setCpf(cpfNovo);
                                                        break;
                                        }
                                        break;
                                case 1:
                                        break;
                        }


                }

                catch (NullPointerException e) { //Falta o double catch aqui ainda

                        System.out.println("Esse ID não existe, nenhuma pessoa encontrada.");

                }
        }

        public void excluirPessoa(int id) {

                System.out.println("Você excluiu " + listaTodasPessoas.get(id).getNome());
                listaTodasPessoas.remove(id);

        }

        public void listarTodasPessoas() {

                Set<Integer> keys = listaTodasPessoas.keySet();
                for(Integer i : keys) {
                        System.out.println(listaTodasPessoas.get(i).getNome() + " ID: " + listaTodasPessoas.get(i).getId());
                }

        }



}
