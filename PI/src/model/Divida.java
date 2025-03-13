package model;

import java.util.Date;

public class Divida {
    private double valor;
    private String descricao;
    private Date data;

    public Divida() {
        // Construtor padrão
    }

    public Divida(double valor, String descricao, Date data) {
        this.valor = valor;
        this.descricao = descricao;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Divida{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                '}';
    }
}
