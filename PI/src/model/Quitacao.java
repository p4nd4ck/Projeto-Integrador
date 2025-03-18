package model;

import java.time.LocalDate;

public class Quitacao {
    private int id;
    private double valor;
    private LocalDate data;
    private Cliente cliente;

    public Quitacao(int id, double valor, LocalDate data, Cliente cliente) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
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
}
