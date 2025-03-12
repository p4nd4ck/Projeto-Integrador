package br.com.unifeob.service;

public class DividaService {
    public void adicionarDivida(Cliente cliente, double valor, String descricao, Date data) {
        Divida divida = new Divida(valor, descricao, data);
        cliente.adicionarDivida(divida);
        System.out.println("Dívida adicionada com sucesso!");
    }
}
