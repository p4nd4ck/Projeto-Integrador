package br.com.unifeob.service;

<<<<<<< HEAD
import br.com.unifeob.model.Cliente;
import br.com.unifeob.model.Divida;
import java.util.Date;

=======
>>>>>>> 6d385a0 (Model & Service Add)
public class DividaService {
    public void adicionarDivida(Cliente cliente, double valor, String descricao, Date data) {
        Divida divida = new Divida(valor, descricao, data);
        cliente.adicionarDivida(divida);
        System.out.println("Dívida adicionada com sucesso!");
    }
}
