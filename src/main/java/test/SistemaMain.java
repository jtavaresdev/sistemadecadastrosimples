package test;


import domain.Formulario;
import domain.Usuarios;

import java.io.IOException;
import java.util.Scanner;

public class SistemaMain {
    public static void main(String[] args) {
        menuPrincipal();
        Usuarios.formatarUsuarios();
    }

    public static void menuPrincipal(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("1 - Cadastrar o usuário\n" +
                "2 - Listar todos usuários cadastrados\n" +
                "3 - Cadastrar nova pergunta no formulário\n" +
                "4 - Deletar pergunta do formulário\n" +
                "5 - Pesquisar usuário por nome ou idade ou email\n" +
                "==> ");
        int escolha = teclado.nextInt();
        switch (escolha){
            case 1:
                Formulario.lerFormulario();
                Usuarios.criacaoDePessoa();
                break;
            case 2:
                Usuarios.usuariosCadastrados();
                break;
            case 3:
                try {
                    Formulario.criarNovaPergunta();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                Formulario.excluirPergunta();
                break;
            case 5:
                Usuarios.pesquisarUsuariosNome();
                break;
            default:
                System.out.println("Escolha inválida.");
                break;
        }
    }

}
