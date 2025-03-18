package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.ClienteDAO;
import model.Cliente;
import model.Divida;
import model.Quitacao;


public class ClienteService {
    private ClienteDAO clienteDAO = new ClienteDAO();

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
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Contato: " + cliente.getContato());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("RG: " + cliente.getRg());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void excluirCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        boolean deletado = clienteDAO.excluirCliente(nome);
        if (deletado)
            System.out.println("Cliente excluído com sucesso!");
    }

    public void consultarExtratoCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = clienteDAO.gerarRelatorioCliente(nome);

        if (cliente == null) {
            System.out.println("Cliente " + nome + " não encontrado.");
            return;
        }

        System.out.println("===== RELATÓRIO DO CLIENTE =====");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Contato: " + cliente.getContato());
        System.out.println("Endereço: " + cliente.getEndereco());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("RG: " + cliente.getRg());
        System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
        System.out.println("--------------------------------");

        // Débito ou crédito
        System.out.println("\n>>> SITUAÇÃO FINANCEIRA:");
        if (cliente.getDividaOuSaldo() < 0) {
            System.out.println("Débito: R$" + String.format("%.2f", Math.abs(cliente.getDividaOuSaldo())));
        } else if (cliente.getDividaOuSaldo() > 0) {
            System.out.println("Crédito: R$" + String.format("%.2f", cliente.getDividaOuSaldo()));
        } else {
            System.out.println("Situação Financeira Regularizada (Sem dívidas ou créditos pendentes).");
        }
        System.out.println("--------------------------------");

        // Exibir Dívidas
        System.out.println(">>> DÍVIDAS:");
        if (cliente.getDividas().isEmpty()) {
            System.out.println("Nenhuma dívida encontrada.");
        } else {
            for (Divida divida : cliente.getDividas()) {
                System.out.println("ID Dívida: " + divida.getId());
                System.out.println("Produto: " + divida.getProduto().getNome());
                System.out.println("\"Valor: R$" + String.format("%.2f", divida.getProduto().getValor()));
                System.out.println("Data: " + divida.getData());
                System.out.println("--------------------------------");
            }
        }

        // Exibir Quitaçōes
        System.out.println(">>> QUITAÇÕES:");
        if (cliente.getQuitacoes().isEmpty()) {
            System.out.println("Nenhuma quitação encontrada.");
        } else {
            for (Quitacao quitacao : cliente.getQuitacoes()) {
                System.out.println("ID Quitação: " + quitacao.getId());
                System.out.println("Valor: R$" + String.format("%.2f", quitacao.getValor()));
                System.out.println("Data: " + quitacao.getData());
                System.out.println("--------------------------------");
            }
        }
    }
}
