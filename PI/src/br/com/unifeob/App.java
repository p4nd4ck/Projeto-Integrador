package br.com.unifeob;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.unifeob.model.Cliente;
import br.com.unifeob.model.Produto;

public class App {
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static int contadorId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Sistema de Gestão de Dívidas ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Cliente");
            System.out.println("3. Adicionar Dívida");
            System.out.println("4. Quitar Dívida");
            System.out.println("5. Excluir Cliente");
            System.out.println("6. Extrato do Cliente");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1 -> cadastrarCliente(scanner);
                    case 2 -> consultarCliente(scanner);
                    case 3 -> adicionarDivida(scanner);
                    case 4 -> quitarDivida(scanner);
                    case 5 -> excluirCliente(scanner);
                    case 6 -> extratoCliente(scanner);
                    case 7 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 7);
        scanner.close();
    }

    private static void cadastrarCliente(Scanner scanner) {
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

        clientes.add(new Cliente(contadorId++, nome, contato, endereco, cpf, rg, dataNascimento));
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void consultarCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = encontrarClientePorNome(nome);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Contato: " + cliente.getContato());
            System.out.println("Endereço: " + cliente.getEndereco());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("RG: " + cliente.getRg());
            System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
            System.out.println("Dívida: " + cliente.getDivida());
            System.out.println("Saldo com a loja: " + cliente.getSaldoComLoja());

            System.out.println("Produtos comprados:");
            ArrayList<Produto> produtos = cliente.getProdutos();
            for (Produto produto : produtos) {
                System.out.println("Produto: " + produto.getNome() + " - Valor: " + produto.getValor());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static Cliente encontrarClientePorNome(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    private static void adicionarDivida(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = encontrarClientePorNome(nome);
        if (cliente != null) {
            System.out.print("Nome do Produto: ");
            String nomeProduto = scanner.nextLine();
            System.out.print("Valor do Produto: ");
            double valorProduto = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data da Dívida: ");
            String data = scanner.nextLine();
            Produto produto = new Produto(nomeProduto, valorProduto);
            cliente.adicionarDivida(produto, data);
            System.out.println("Dívida adicionada com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void quitarDivida(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = encontrarClientePorNome(nome);
        if (cliente != null) {
            System.out.print("Valor para Quitar Dívida: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Data da Quitação: ");
            String dataQuitacao = scanner.nextLine();
            cliente.quitarDivida(valor, dataQuitacao);
            System.out.println("Dívida quitada com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void excluirCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = encontrarClientePorNome(nome);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void extratoCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = encontrarClientePorNome(nome);
        if (cliente != null) {
            System.out.println("Extrato do Cliente:");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Dívida Total: " + cliente.getDivida());
            System.out.println("Saldo com a loja: " + cliente.getSaldoComLoja());

            System.out.println("Produtos e Datas da Dívida:");
            for (int i = 0; i < cliente.getProdutos().size(); i++) {
                Produto produto = cliente.getProdutos().get(i);
                String data = cliente.getDatasDivida().get(i);
                System.out.println("Produto: " + produto.getNome() + " - Valor: " + produto.getValor() + " - Data: " + data);
            }

            System.out.println("Valores Quitados:");
            for (int i = 0; i < cliente.getValoresQuitados().size(); i++) {
                double valorQuitado = cliente.getValoresQuitados().get(i);
                String dataQuitacao = cliente.getDatasQuitacao().get(i);
                System.out.println("Valor Quitado: " + valorQuitado + " - Data de Quitação: " + dataQuitacao);
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
