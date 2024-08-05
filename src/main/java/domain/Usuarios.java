package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuarios {

    public static void criacaoDePessoa() {
        File file = new File("pessoascadastradas");
        Scanner teclado = new Scanner(System.in);
        System.out.print("\nNome: ");
        String nomeTest = teclado.nextLine();
        System.out.print("Email: ");
        String emailTest = teclado.nextLine();
        System.out.print("Idade: ");
        int idade = teclado.nextInt();
        System.out.print("Altura: ");
        double altura = teclado.nextDouble();
        boolean emailExistencia = validarEmail(emailTest);


        Pessoa pessoa = new Pessoa(nomeTest, emailTest, idade, altura);

        if (nomeTest.length() < 10) {
            System.out.println("Nome com menos de 10 caracteres");
            throw new RuntimeException("Erro na validação do nome.");
        } else if (!emailTest.contains("@")) {
            System.out.println("Email não contém @");
            throw new RuntimeException("Erro na validação do email");
        } else if (emailExistencia) {
            System.out.println("Email ja existente no banco de dados");
            throw new RuntimeException("Email ja utilizado.");
        } else if (idade < 18) {
            System.out.println("Menor de idade");
            throw new RuntimeException("Erro na validação da idade");
        } else {
            try {
                pessoa.criarArquivo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static boolean validarEmail(String emailTest){
        File file = new File("pessoascadastradas");
        for (File listFile : Objects.requireNonNull(file.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(listFile))) {
                String line;
                while((line = reader.readLine())!= null){
                    if (line.equals(emailTest.trim())){
                        return true;
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void usuariosCadastrados() {
        File fr = new File("pessoascadastradas/");
        File[] files = fr.listFiles();
        int count = 0;
        assert files != null;
        for (File file : files) {
            count++;
            File file1 = new File(file.toURI());
            try (FileReader fileReader = new FileReader(file1);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String id = String.valueOf(count);
                // para printar tudo utilizar while instanciando um String antes. Olhar nas aulas de IO.
                String string = id + " - " + bufferedReader.readLine();
                System.out.println(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void pesquisarUsuariosNome() {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o parametro de busca:");
        String regex = teclado.nextLine().toUpperCase();
        Pattern pattern = Pattern.compile(regex);
        File file = new File("pessoascadastradas/");
        File[] files = file.listFiles();
        for (File file1 : files) {
            try (BufferedReader bbr = new BufferedReader(new FileReader(file1))) {
                String line;
                while ((line = bbr.readLine()) != null) {
                    String texto = line.toUpperCase();
                    Matcher matcher = pattern.matcher(texto);
                    while (matcher.find()) {
                        System.out.println(line);
                        System.out.println("Diretório: " + file1.getPath());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void formatarUsuarios() {
        File file = new File("pessoascadastradas/");
        int digito = 1;
        int length = file.listFiles().length;
        for (File listFile : file.listFiles()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(listFile))) {
                String nome = reader.readLine();
                String id = String.valueOf(length);
                String nomeArquvio = id + "-" + nome.trim().replace(" ", "").toUpperCase();
                File file1 = new File("/home/joao/projects/sistemadecastro/pessoascadastradas/" + nomeArquvio);
                boolean b = listFile.renameTo(file1);
                length--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
