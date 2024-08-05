package domain;


import java.io.*;
import java.util.Locale;

public class Pessoa {
    private String nome;
    private String email;
    private int idade;
    private double altura;

    public Pessoa() {
    }

    public Pessoa(String nome, String email, int idade, double altura) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
        System.out.println(this.nome);
        System.out.println(this.email);
        System.out.println(this.idade);
        System.out.println(this.altura);
    }

    public int idCriar() {
        File fr = new File("pessoascadastradas");
        File[] files = fr.listFiles();
        if (files.length == 0) {
            return 1;
        } else {
            return files.length + 1;
        }
    }

    public void criarArquivo() throws IOException {

        String idParse = String.valueOf(idCriar());
        String nomeArquivo = idParse + "-" + this.nome.trim().replace(" ", "").toUpperCase(Locale.ROOT);

        File arquivo = new File("/home/joao/projects/sistemadecastro/pessoascadastradas/" + nomeArquivo + ".txt");
        try (FileWriter escrita = new FileWriter(arquivo)) {
            escrita.write(this.nome + "\n");
            escrita.write(this.email + "\n");
            escrita.write(this.idade + "\n");
            escrita.write(this.altura + "\n");
            escrita.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Falha na criação do arquivo");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

}
