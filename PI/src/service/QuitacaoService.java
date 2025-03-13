package service;

import java.util.Date;

import model.Cliente;
import model.Quitacao;
public class QuitacaoService {
    public void quitarDivida(Cliente cliente, double valor, Date data) {
        Quitacao quitacao = new Quitacao();
        quitacao.setValor(valor);
        quitacao.setData(data);
        cliente.quitarDivida(quitacao);
        System.out.println("Dívida quitada com sucesso!");
    }
}
