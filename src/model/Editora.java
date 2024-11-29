package model;

public class Editora {

    private int ideditora; 
    private String nome;   

    public Editora() {
    }
  
    public Editora(String nome) {
        this.nome = nome;
    }

    public Editora(int ideditora, String nome) {
        this.ideditora = ideditora;
        this.nome = nome;
    }

    public int getIdeditora() {
        return ideditora;
    }

    public void setIdeditora(int ideditora) {
        this.ideditora = ideditora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
