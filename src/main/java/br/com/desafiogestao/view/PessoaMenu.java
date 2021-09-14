package main.java.br.com.desafiogestao.view;

import main.java.br.com.desafiogestao.model.Pessoa;
import main.java.br.com.desafiogestao.service.PessoaService;
import org.postgresql.util.ServerErrorMessage;

import javax.swing.*;
import java.util.List;

public class PessoaMenu {

    static boolean running = true;
    private static PessoaService pessoaService = new PessoaService();

    public static void mostrarMenu() {

        String[] botoes = {"Nova Pessoa", "Visualizar Pessoa", "Editar Pessoa", "Excluir Pessoa", "Listar Todos", "Voltar"};

        loop:
        while (running = true) {

            int input = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Tela pessoas", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoes, "default");

            switch (input) {
                case 0:
                    criarNovaPessoa();
                    break;
                case 1:
                    getPessoa();
                    break;
                case 2:
                    editarPessoa();
                    break;
                case 3:
                    excluirPessoa();
                    break;
                case 4:
                    listarTodasPessoas();
                    break;
                case 5:
                    break loop;
            }
        }
    }


    public static void criarNovaPessoa() {

        try {

            String nomeDaPessoa = JOptionPane.showInputDialog("Digite o nome completo da pessoa a ser criada.");
            String cpfDaPessoa = JOptionPane.showInputDialog("Digite o CPF da pessoa, sem sinais e pontuações.");

            int caracteresCPF = 0;
            for(int i = 0; i < cpfDaPessoa.length(); i++) {
                if(cpfDaPessoa.charAt(i) != ' ')
                    caracteresCPF++;
            }

            if (caracteresCPF == 11 && cpfDaPessoa.matches("[0-9]+")) {
                Pessoa pessoa = new Pessoa(nomeDaPessoa, cpfDaPessoa);
                pessoaService.salvarPessoa(pessoa);

            } else {
                System.out.println("Você digitou um CPF inválido, reinicie o cadastro.");
            }

        } catch (NumberFormatException e) {

            System.out.println("Você digitou um ID inválido");

        }

    }

    public static void getPessoa() {

        String idString = JOptionPane.showInputDialog("Insira o ID da pessoa a ser procurada");
        int id = Integer.parseInt(idString);
        Pessoa pessoa = pessoaService.getPessoaById(id);
        try {
            System.out.println("O ID ''" + pessoa.getId() + "'' refere-se à pessoa " + pessoa.getNome() + ", seu CPF é: " + pessoa.getCpf());

        }
        catch(NullPointerException e) {
            System.out.println("Essa pessoa não existe, tente novamente.");
        }
    }

    public static void editarPessoa() {
        String idString = JOptionPane.showInputDialog("Insira o ID da pessoa a ser editada");
        int id = Integer.parseInt(idString);
        Pessoa pessoaParaEditar = pessoaService.getPessoaById(id);
        try {
            int n = JOptionPane.showInternalConfirmDialog(null, "Você quer editar a pessoa: " + pessoaParaEditar.getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            String[] botoesParaEditar = {"Nome", "CPF"};
            switch (n) {
                case 0:
                    int editInput = JOptionPane.showOptionDialog(null, "O que você deseja editar?", "Tela edição", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoesParaEditar, "default");
                    switch(editInput) {
                        case 0:
                            String nomeNovo = JOptionPane.showInputDialog("Digite o novo nome.");
                            pessoaParaEditar.setNome(nomeNovo);
                            break;
                        case 1:
                            String cpfNovo = JOptionPane.showInputDialog("Digite o novo CPF.");
                            pessoaParaEditar.setCpf(cpfNovo);
                            break;
                    }
                    pessoaService.salvarPessoa(pessoaParaEditar);
                    break;

                case 1:
                    break;
            }


        }

        catch (NullPointerException e) { //Falta o double catch aqui ainda

            System.out.println("Esse ID não existe, nenhuma pessoa encontrada.");

        }
    }

    public static void excluirPessoa() {
        String idString = JOptionPane.showInputDialog("Insira o ID da pessoa a ser excluída");
        int id = Integer.parseInt(idString);
        Pessoa pessoa = pessoaService.getPessoaById(id);

        ServerErrorMessage serverErrorMessage = new ServerErrorMessage("Essa pessoa está vinculada à uma empresa, verifique suas relações");

        try {
            pessoaService.excluirPessoa(id);

        }
        catch (NullPointerException e) {
            System.out.println("Essa pessoa não existe, impossível excluir.");
            e.printStackTrace();
        }
    }

    public static void listarTodasPessoas() {

        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();

        for(Pessoa pessoa : pessoas) {
            System.out.println("Nome: " + pessoa.getNome() + " | ID: " + pessoa.getId());
        }

    }

}
