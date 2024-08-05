package domain;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Stream;
import java.io.PrintStream;

public class Formulario {

    public static void lerFormulario() {
        File file = new File("formulario.txt");
        // fica mais facil de ler assim ou não? i dont know, you tell me
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String string;
            while ((string = br.readLine()) != null) {
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void criarNovaPergunta() throws IOException {
        limparLinhaBlank();
        Scanner teclado = new Scanner(System.in);
        File file = new File("formulario.txt");

        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {
            int numPergunta = contagemPerguntas();
            numPergunta += 1;

            System.out.print("Digite a nova pergunta: ");
            String novaPergunta = teclado.nextLine();
            bw.write(numPergunta + " - " + novaPergunta);
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro na criação de nova pergunta.");
        } finally {
            formatarID();
        }

    }

    public static int contagemPerguntas() throws FileNotFoundException {
        File file = new File("formulario.txt");
        LineNumberReader linhaContagem = new LineNumberReader(new InputStreamReader(new FileInputStream("formulario.txt")));
        String nextLine = null;
        int blankLinas = 0;

        try {
            while ((((nextLine = linhaContagem.readLine())) != null)) {
                if (nextLine.isBlank() || nextLine.isEmpty()) {
                    blankLinas++;
                }
                if (nextLine == null) {
                    break;
                }
            }
            return linhaContagem.getLineNumber() - blankLinas;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void excluirPergunta() {
        File file = new File("formulario.txt");
        File filetemp = new File("formulariotemporario.txt");
        Scanner teclado = new Scanner(System.in);
        lerFormulario();
        System.out.print("Escolha o indice de perguntas a serem excluidas: ");
        int escolha = teclado.nextInt();
        String linhaEscolhida = String.valueOf(escolha);

        if (escolha <= 4) {
            System.out.println("Pergunta escolhida não pode ser excluida.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file)); BufferedWriter bw = new BufferedWriter(new FileWriter(filetemp))) {
                String linhaAtual;
                while ((linhaAtual = br.readLine()) != null) {
                    String[] idLinha = linhaAtual.split(" ");
                    if (idLinha[0].equals(linhaEscolhida)) continue;
                    bw.write(linhaAtual + System.lineSeparator());
                    bw.flush();
                }
                boolean sucesso = filetemp.renameTo(file);
            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                formatarID();
            }
        }
    }

    public static void limparLinhaBlank() {
        File file = new File("formulario.txt");
        File fileTemp = new File("formulariotemp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTemp, true));
             BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
             BufferedReader reader = new BufferedReader(new FileReader(fileTemp))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                } else {
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
            }
            boolean sucesso = fileTemp.renameTo(file);
            fileTemp.delete();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void formatarID() {
        limparLinhaBlank();
        File file = new File("formulario.txt");
        File fileTemp = new File("formulariotemp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemp, true))) {
            String line;
            int count = 0;
            int ultimo = 5;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                count++;
                if (count <= 4) {
                    writer.write(line);
                    writer.newLine();
                    writer.flush();
                } else {
                    String i = String.valueOf(ultimo);
                    String string = line.replaceFirst("\\d", i);
                    writer.write(string);
                    writer.newLine();
                    writer.flush();
                    ultimo++;
                }
                boolean b = fileTemp.renameTo(file);
                boolean delete = fileTemp.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
