package model;

import java.time.LocalDate;

public class Divida {
    private int id;
    private LocalDate data;
    private Cliente cliente;
    private Produto produto;

    public Divida() {
    }

    public Divida(int id, LocalDate data, Cliente cliente, Produto produto) {
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
                ", data=" + data +
                ", cliente=" + cliente.getNome() +
                ", produto=" + produto.getNome() +
                ", valor=" + produto.getValor() +
                '}';
    }
}
