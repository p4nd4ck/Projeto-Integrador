package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.ClienteDAO;
import dao.QuitacaoDAO;
import model.Cliente;
import model.Quitacao;

public class QuitacaoService {
    private QuitacaoDAO quitacaoDAO = new QuitacaoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

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
         
        Quitacao quitacao = new Quitacao(0, valor, dataQuitacao, cliente);
        quitacaoDAO.adicionarQuitacao(quitacao);

        System.out.println("Dívida quitada com sucesso!");
    }
}
