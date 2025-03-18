package model;

// Arquivo: Produto.java
public class Produto {
    private int id;
    private String nome;
    private double valor;

    public Produto() {
        
    }

    // Construtor que aceita os parâmetros (int id, String nome, double valor)
    public Produto(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    // Construtor que aceita os parâmetros (String nome, double valor)
    public Produto(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}