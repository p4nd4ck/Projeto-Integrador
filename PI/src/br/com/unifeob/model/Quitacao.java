package br.com.unifeob.model;

<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> 6d385a0 (Model & Service Add)
public class Quitacao {
    private double valor;
    private Date data;

    public Quitacao(double valor, Date data) {
        this.valor = valor;
        this.data = data;
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