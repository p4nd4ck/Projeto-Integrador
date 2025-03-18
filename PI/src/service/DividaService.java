package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.DividaDAO;
import dao.ProdutoDAO;
import model.Cliente;
import model.Divida;
import model.Produto;

public class DividaService {

    private DividaDAO dividaDAO = new DividaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void adicionarDivida(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();

        System.out.print("Data da Dívida: ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = clienteDAO.consultarCliente(nome);
        if (cliente == null) {
            System.out.println("Cliente não encontrato.");
            return;
        }
        Produto produto = produtoDAO.consultarProduto(nomeProduto);
        if (produto == null) {
            System.out.println("Produto não encontrato.");
            return;
        }
        System.out.println("Valor do Produto: " + produto.getValor());

        Divida divida = new Divida(0, data, cliente, produto);
        dividaDAO.adicionarDivida(divida);
        System.out.println("Dívida adicionada com sucesso!");
    }
}
