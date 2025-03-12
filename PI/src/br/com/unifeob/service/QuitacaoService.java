package br.com.unifeob.service;

public class QuitacaoService {
    public void quitarDivida(Cliente cliente, double valor, Date data) {
        Quitacao quitacao = new Quitacao(valor, data);
        cliente.quitarDivida(quitacao);
        System.out.println("Dívida quitada com sucesso!");
    }
}
