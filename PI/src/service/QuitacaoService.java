package service;

import java.util.Date;

import model.Cliente;
import model.Quitacao;
public class QuitacaoService {
    public void quitarDivida(Cliente cliente, double valor, Date data) {
        Quitacao quitacao = new Quitacao(valor, data);
        cliente.quitarDivida(quitacao);
        System.out.println("Dívida quitada com sucesso!");
    }
}
