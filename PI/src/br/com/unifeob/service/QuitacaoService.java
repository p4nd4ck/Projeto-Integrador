package br.com.unifeob.service;

<<<<<<< HEAD
import br.com.unifeob.model.Cliente;
import br.com.unifeob.model.Quitacao;
import java.util.Date;

=======
>>>>>>> 6d385a0 (Model & Service Add)
public class QuitacaoService {
    public void quitarDivida(Cliente cliente, double valor, Date data) {
        Quitacao quitacao = new Quitacao(valor, data);
        cliente.quitarDivida(quitacao);
        System.out.println("Dívida quitada com sucesso!");
    }
}
