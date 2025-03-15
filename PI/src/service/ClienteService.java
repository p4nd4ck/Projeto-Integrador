package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.ClienteDAO;
import model.Cliente;


public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();

    // static {
    //     try {
    //         // Carregar o driver JDBC do MySQL
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_dividas", "root", "root");
    //     } catch (ClassNotFoundException | SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public void cadastrarCliente(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Contato: ");
        String contato = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("Data de Nascimento: ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Cliente cliente = new Cliente(0, nome, contato, endereco, cpf, rg, dataNascimento);
        clienteDAO.cadastrarCliente(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public void consultarCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();

        Cliente cliente = clienteDAO.consultarCliente(nome);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("Cliente encontrado:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Contato: " + cliente.getContato());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("RG: " + cliente.getRg());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
            System.out.println("Dívida: " + cliente.getDivida());
            System.out.println("Saldo com a loja: " + cliente.getSaldoComLoja());
        } else {
            System.out.println("Cliente não encontrado.");
        }

    }
}
