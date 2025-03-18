package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.DividaDAO;
import dao.ProdutoDAO;
import dao.QuitacaoDAO;
import model.Cliente;
import model.Divida;
import model.Produto;
import model.Quitacao;
public class DividaService {
    private DividaDAO dividaDAO = new DividaDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private QuitacaoDAO quitacaoDAO = new QuitacaoDAO();

    // public void adicionarDivida(Cliente cliente, double valor, String descricao, Date data) {
    //     Divida divida = new Divida(valor, descricao, data);
    //     cliente.adicionarDivida(divida);
    //     System.out.println("Dívida adicionada com sucesso!");
    // }

    public void adicionarDivida(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Nome do Produto: ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Valor do Produto: ");
        double valorProduto = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
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

        Divida divida = new Divida(0, valorProduto, data, cliente, produto);
        dividaDAO.adicionarDivida(divida);
        System.out.println("Dívida adicionada com sucesso!");
    }

    public void quitarDivida(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Valor para Quitar Dívida: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Data da Quitação: ");
        LocalDate dataQuitacao = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        Cliente cliente = clienteDAO.consultarCliente(nome);
        if (cliente == null) {
            System.out.println("Cliente não encontrato.");
            return;
        }
        
        Divida divida = new Divida(0, valor, dataQuitacao, cliente, null);
        dividaDAO.quitarDivida(divida);
        
        Quitacao quitacao = new Quitacao(0, valor, dataQuitacao, cliente);
        quitacaoDAO.adicionarQuitacao(quitacao);

        System.out.println("Dívida quitada com sucesso!");
    }
}
