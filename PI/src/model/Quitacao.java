package model;

import java.util.Date;
public class Quitacao {


    private int id;
    private double valor;
    private Date data;
    private Cliente cliente;

    
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
