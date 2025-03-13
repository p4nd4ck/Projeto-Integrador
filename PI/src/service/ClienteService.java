package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteService {
     public static void cadastrarCliente(Scanner scanner) throws SQLException {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Número para Contato: ");
        String contato = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("Data de Nascimento: ");
        String dataNascimento = scanner.nextLine();
        try (Connection connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password")) {

        try {
            String sql = "INSERT INTO clientes (nome, contato, endereco, cpf, rg, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, contato);
            stmt.setString(3, endereco);
            stmt.setString(4, cpf);
            stmt.setString(5, rg);
            stmt.setString(6, dataNascimento);
            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
        }
    }

}
