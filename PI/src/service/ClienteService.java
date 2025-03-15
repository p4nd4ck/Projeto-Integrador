package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.ClienteDAO;
import model.Cliente;


public class ClienteService {
    private static ClienteDAO clienteDAO = new ClienteDAO();

    // static {
    //     try {
    //         // Carregar o driver JDBC do MySQL
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_dividas", "root", "root");
    //     } catch (ClassNotFoundException | SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void cadastrarCliente(Scanner scanner) throws SQLException {
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
}
