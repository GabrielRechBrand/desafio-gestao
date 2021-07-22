package main;

import javax.swing.*;
import main.PessoaMemoryRepository.*;
import java.util.Arrays;

import static main.PessoaMemoryRepository.listaTodasPessoas;



public class Main {

    static boolean running = true;

    public static void main(String[] args) {

        PessoaService pessoaService = new PessoaService();
        PessoaMemoryRepository pessoaMemoryRepository = new PessoaMemoryRepository();

        while (running = true) {

            String[] botoes={"Nova Pessoa","Visualizar Pessoa","Editar Pessoa","Excluir Pessoa", "Listar Todos", "Encerrar"};

            int intInput = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Tela inicial", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoes, "default");

            switch (intInput) {

                case 0:

                    String nomeDaPessoa = JOptionPane.showInputDialog("Digite o nome completo da pessoa a ser criada.");
                    String idStringDaPessoa = JOptionPane.showInputDialog("Crie um ID para essa pessoa");
                    Integer idDaPessoa = Integer.parseInt(idStringDaPessoa);
                    String cpfDaPessoa = JOptionPane.showInputDialog("Digite o CPF da pessoa, sem sinais e pontuações.");

                    int caracteresCPF = 0;
                    for(int i = 0; i < cpfDaPessoa.length(); i++) {
                        if(cpfDaPessoa.charAt(i) != ' ')
                            caracteresCPF++;

                    }

                    if (caracteresCPF == 11) {
                        Pessoa pessoa = new Pessoa(idDaPessoa, nomeDaPessoa, cpfDaPessoa);
                        pessoaService.salvarPessoa(pessoa);

                    } else {
                        System.out.println("Você digitou um CPF inválido, reinicie o cadastro.");
                    }

                    break;
                case 1:

                    String idString = JOptionPane.showInputDialog("Insira o ID da pessoa a ser procurada");
                    int id = Integer.parseInt(idString);
                    pessoaService.getPessoaById(id);
                    break;

                case 2:

                    String idString2 = JOptionPane.showInputDialog("Insira o ID da pessoa a ser editada");
                    int id2 = Integer.parseInt(idString2);
                    pessoaMemoryRepository.editarPessoa(id2);
                    break;

                case 3:

                    String idString3 = JOptionPane.showInputDialog("Insira o ID da pessoa a ser editada");
                    int id3 = Integer.parseInt(idString3);
                    pessoaMemoryRepository.excluirPessoa(id3);
                    break;

                case 4:

                    pessoaService.listarTodasPessoas();
                    break;

                case 5:

                    running = false;
                    break;

            }
        }
    }
}
