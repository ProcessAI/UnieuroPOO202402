package model;

public class Multa {
    private int id;
    private double valorBase;
    private double valorAtual;
    private double taxaDiaria;
    private int emprestimoId;

    private String descricao;
    private String nomeUsuario;

    // Construtor
    public Multa(int id, double valorBase, double valorAtual, double taxaDiaria) {
        this.id = id;
        this.valorBase = valorBase;
        this.valorAtual = valorAtual;
        this.taxaDiaria = taxaDiaria;
    }

    // Construtor adicional (caso você queira criar uma multa com descrição e valores)
    public Multa(String descricao, double valorBase, double valorAtual, double taxaDiaria, int emprestimoId) {
        this.descricao = descricao;
        this.valorBase = valorBase;
        this.valorAtual = valorAtual;
        this.taxaDiaria = taxaDiaria;
        this.emprestimoId = emprestimoId;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getTaxaDiaria() {
        return taxaDiaria;
    }

    public void setTaxaDiaria(double taxaDiaria) {
        this.taxaDiaria = taxaDiaria;
    }

    public int getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(int emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
