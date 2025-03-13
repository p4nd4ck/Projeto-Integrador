package service;

import java.util.Date;

import model.Cliente;
import model.Divida;
public class DividaService {
    public void adicionarDivida(Cliente cliente, double valor, String descricao, Date data) {
        Divida divida = new Divida(valor, descricao, data);
        cliente.adicionarDivida(divida);
        System.out.println("Dívida adicionada com sucesso!");
    }
}
