package br.com.unifeob.service;

import br.com.unifeob.model.Cliente;
import br.com.unifeob.model.Divida;
import java.util.Date;
public class DividaService {
    public void adicionarDivida(Cliente cliente, double valor, String descricao, Date data) {
        Divida divida = new Divida(valor, descricao, data);
        cliente.adicionarDivida(divida);
        System.out.println("Dívida adicionada com sucesso!");
    }
}
