package main;

import com.sun.security.auth.NTUserPrincipal;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaMenu {

    static boolean running = true;
    private static PessoaService pessoaService = new PessoaService();
    private static EmpresaService empresaService = new EmpresaService();

    public static void mostrarMenu() {

        String[] botoes = {"Nova Empresa", "Visualizar Empresa", "Editar Empresa", "Excluir Empresa", "Listar Empresas", "Informar Presidente", "Demitir Presidente"};

        loop: while(running = true) {

            int input = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Tela inicial", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoes, "default");

            switch (input) {

                case 0:
                    criarNovaEmpresa();
                    break;
                case 1:
                    getEmpresa();
                    break;
                case 2:
                    editarEmpresa();
                    break;
                case 3:
                    excluirEmpresa();
                    break;
                case 4:
                    listarTodasEmpresas();
                    break;
                case 5:
                    informarPresidente();
                    break;
                case 6:
                    demitirPresidente();
                    break;

            }

        }

    }

    public static void criarNovaEmpresa() {

        try {

            String nomeDaEmpresa = JOptionPane.showInputDialog("Qual é o nome da empresa que você deseja criar?");
            String cnpjDaEmpresa = JOptionPane.showInputDialog("Qual é o CNPJ da empresa?");
            String idStringDoPresidente = JOptionPane.showInputDialog("Qual é o ID do presidente?");
            Integer idDoPresidente = Integer.parseInt(idStringDoPresidente);
            Pessoa pessoa = pessoaService.getPessoaById(idDoPresidente);

            int caracteresCNPJ = 0;
            for(int i = 0; i < cnpjDaEmpresa.length(); i++) {
                if(cnpjDaEmpresa.charAt(i) != ' ')
                    caracteresCNPJ++;
            }

            if(caracteresCNPJ == 14 && cnpjDaEmpresa.matches("[0-9]+")) {
                Empresa empresa = new Empresa(nomeDaEmpresa, cnpjDaEmpresa, pessoa);
                empresaService.salvarEmpresa(empresa);
            } else {
                System.out.println("Você digitou um CNPJ inválido.");
            }

        }

        catch(NumberFormatException e) {

            System.out.println("Você digitou algo inválido");

        }
    }

    public static void getEmpresa() {

        String idString = JOptionPane.showInputDialog("Insira o ID da empresa a ser procurada.");
        int id = Integer.parseInt(idString);
        Empresa empresa = empresaService.getEmpresaById(id);
        try {
            System.out.println("O ID '" + id + "' refere-se à empresa: " + empresa.getNome());
        } catch (NullPointerException e) {
            System.out.println("Esse ID não se refere à nenhuma empresa.");
        }

    }

    public static void editarEmpresa() {

        String idString = JOptionPane.showInputDialog("Insira o ID da empresa a ser editada.");
        int id = Integer.parseInt(idString);
        Empresa empresa = empresaService.getEmpresaById(id);
        try {
            int n = JOptionPane.showInternalConfirmDialog(null, "Você quer editar a empresa: " + empresa.getNome() + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
            String[] botoesParaEditar = {"Nome", "Presidente"};
            switch(n) {
                case 0:
                    int editInput = JOptionPane.showOptionDialog(null, "O que você deseja editar?", "Tela edição", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoesParaEditar, "default");
                    switch (editInput) {
                        case 0:
                            String nome = JOptionPane.showInputDialog("Digite o novo nome.");
                            empresa.setNome(nome);
                            break;
                        case 1:
                            String stringIdPresidente = JOptionPane.showInputDialog("Digite o ID do novo presidente.");
                            int idPresidente = Integer.parseInt(stringIdPresidente);
                            empresa.setPresidente(pessoaService.getPessoaById(idPresidente));
                            break;
                    }
                    empresaService.salvarEmpresa(empresa);
                    break;

                case 1:
                    break;

            }

        } catch(NullPointerException e ) {
            System.out.println("ID inválido, por favor tente novamente.");
        }

    }

    public static void excluirEmpresa() {

        String idString = JOptionPane.showInputDialog("Digite o ID da empresa que você deseja excluir.");
        int id = Integer.parseInt(idString);
        Empresa empresa = empresaService.getEmpresaById(id);

        try {

            System.out.println("Você excluiu a empresa: " + empresa.getNome());
            empresaService.excluirEmpresa(empresa.getId());

        } catch (NullPointerException e) {

            System.out.println("Essa empresa não existe, por favor tente novamente.");

        }

    }

    public static void listarTodasEmpresas() {

        List<Empresa> empresas = empresaService.listarTodasEmpresas();

        for(Empresa empresa : empresas) {

            System.out.println("Nome da Empresa: " + empresa.getNome() + " | ID: " + empresa.getId());

        }

    }

    public static void informarPresidente() {

    }

    public static void demitirPresidente() {

    }

}

