package service;

import java.util.List;

import dao.ProdutoDAO;
import model.Produto;

public class ProdutoService {
    ProdutoDAO produtoDAO = new ProdutoDAO();

    public void consultarProdutos() {
        List<Produto> produtos = produtoDAO.consultarProdutos();

        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome() + ", Valor: " + produto.getValor());
        }
    }
}
