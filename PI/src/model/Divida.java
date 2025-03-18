package model;

import java.time.LocalDate;

public class Divida {
    private int id;
    private double valor;
    private LocalDate data;
    private Cliente cliente;
    private Produto produto;

    public Divida() {
        // Construtor padrão
    }

    public Divida(int id, double valor, LocalDate data, Cliente cliente, Produto produto) {
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Divida{" +
                "valor=" + valor +
                ", data=" + data +
                '}';
    }
}
