package main.java.br.com.desafiogestao.view;

import main.java.br.com.desafiogestao.view.EmpresaMenu;
import main.java.br.com.desafiogestao.view.PessoaMenu;

import javax.swing.*;

public class Main {

    static boolean running = true;
    public static EmpresaMenu empresaMenu = new EmpresaMenu();
    public static PessoaMenu pessoaMenu = new PessoaMenu();

    public static void main(String[] args) {

        String[] botoes = {"Gerenciar Empresas", "Gerenciar Pessoas", "Encerrar"};

        loop: while(running) {

            int input = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Tela Inicial", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoes, "default");

            switch(input) {

                case 0:
                    EmpresaMenu.mostrarMenu();
                    break;
                case 1:
                    PessoaMenu.mostrarMenu();
                    break;
                case 2:
                    break loop;

            }

        }

    }

}
