package model;

public class Livro {
    private Integer idlivro;
    private String nome;
    private int autor_idautor;
    private int editora_ideditora;
    private int categoria_idcategoria;
    private int reserva_idreserva;


    public Livro() {
    }
    public Livro(Integer idlivro, String nome, int autor_idautor, int editora_ideditora, int categoria_idcategoria, int reservaIdreserva) {
        this.idlivro = idlivro;
        this.nome = nome;
        this.autor_idautor = autor_idautor;
        this.editora_ideditora = editora_ideditora;
        this.categoria_idcategoria = categoria_idcategoria;
        reserva_idreserva = reservaIdreserva;
    }

    public int getIdlivro() {
        return idlivro;
    }

    public void setIdlivro(int idlivro) {
        this.idlivro = idlivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAutor_idautor() {
        return autor_idautor;
    }

    public void setAutor_idautor(int autor_idautor) {
        this.autor_idautor = autor_idautor;
    }

    public int getEditora_ideditora() {
        return editora_ideditora;
    }

    public void setEditora_ideditora(int editora_ideditora) {
        this.editora_ideditora = editora_ideditora;
    }

    public int getCategoria_idcategoria() {
        return categoria_idcategoria;
    }

    public void setCategoria_idcategoria(int categoria_idcategoria) {
        this.categoria_idcategoria = categoria_idcategoria;
    }

    public int getReserva_idreserva() { return reserva_idreserva; }

    public void setReserva_idreserva(int reserva_idreserva) {this.reserva_idreserva = reserva_idreserva;}
}