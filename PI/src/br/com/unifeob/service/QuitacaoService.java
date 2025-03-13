package br.com.unifeob.service;

import br.com.unifeob.model.Cliente;
import br.com.unifeob.model.Quitacao;
import java.util.Date;
public class QuitacaoService {
    public void quitarDivida(Cliente cliente, double valor, Date data) {
        Quitacao quitacao = new Quitacao(valor, data);
        cliente.quitarDivida(quitacao);
        System.out.println("Dívida quitada com sucesso!");
    }
}
