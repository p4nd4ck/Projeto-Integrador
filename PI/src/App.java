import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Cliente;
import model.Produto;
import service.ClienteService;

public class App {
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Carregar o driver JDBC do MySQL
            //Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_dividas?useSSL=false", "root", "root");
        } 
        /*catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC do MySQL: " + e.getMessage());
            return;
        } */
        catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return;
        }

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
                    case 1 -> {
                        try {
                            ClienteService.cadastrarCliente(scanner);
                        } catch (SQLException e) {
                            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
                        }
                    }
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

   

    private static void consultarCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        try {
            String sql = "SELECT * FROM clientes WHERE nome = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("id");
                System.out.println("Cliente encontrado:");
                System.out.println("ID: " + clienteId);
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Contato: " + rs.getString("contato"));
                System.out.println("Endereço: " + rs.getString("endereco"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("RG: " + rs.getString("rg"));
                System.out.println("Data de Nascimento: " + rs.getString("data_nascimento"));
                System.out.println("Dívida: " + rs.getDouble("divida"));
                System.out.println("Saldo com a loja: " + rs.getDouble("saldo_com_loja"));
                
                System.out.println("Produtos comprados:");
                String sqlProdutos = "SELECT * FROM produtos WHERE cliente_id = ?";
                PreparedStatement stmtProdutos = connection.prepareStatement(sqlProdutos);
                stmtProdutos.setInt(1, clienteId);
                ResultSet rsProdutos = stmtProdutos.executeQuery();
                while (rsProdutos.next()) {
                    System.out.println("Produto: " + rsProdutos.getString("nome") + " - Valor: " + rsProdutos.getDouble("valor"));
                }
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar cliente: " + e.getMessage());
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
            try {
                String sql = "INSERT INTO dividas (cliente_id, nome_produto, valor_produto, data) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setInt(1, cliente.getId());
                stmt.setString(2, nomeProduto);
                stmt.setDouble(3, valorProduto);
                stmt.setString(4, data);
                stmt.executeUpdate();
                System.out.println("Dívida adicionada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao adicionar dívida: " + e.getMessage());
            }
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
            try {
                String sql = "UPDATE dividas SET quitada = 1, data_quitacao = ? WHERE cliente_id = ? AND valor = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, dataQuitacao);
                stmt.setInt(2, cliente.getId());
                stmt.setDouble(3, valor);
                stmt.executeUpdate();
                System.out.println("Dívida quitada com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao quitar dívida: " + e.getMessage());
            }
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
            try {
                String sql = "DELETE FROM clientes WHERE nome = ?";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.executeUpdate();
                System.out.println("Cliente excluído com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao excluir cliente: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void extratoCliente(Scanner scanner) {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        try {
            String sql = "SELECT * FROM clientes WHERE nome = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("id");
                System.out.println("Extrato do Cliente:");
                System.out.println("ID: " + clienteId);
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Dívida Total: " + rs.getDouble("divida"));
                System.out.println("Saldo com a loja: " + rs.getDouble("saldo_com_loja"));

                System.out.println("Produtos e Datas da Dívida:");
                String sqlProdutos = "SELECT * FROM dividas WHERE cliente_id = ?";
                PreparedStatement stmtProdutos = connection.prepareStatement(sqlProdutos);
                stmtProdutos.setInt(1, clienteId);
                ResultSet rsProdutos = stmtProdutos.executeQuery();
                while (rsProdutos.next()) {
                    System.out.println("Produto: " + rsProdutos.getString("nome_produto") + " - Valor: " + rsProdutos.getDouble("valor_produto") + " - Data: " + rsProdutos.getString("data"));
                }

                System.out.println("Valores Quitados:");
                String sqlQuitados = "SELECT * FROM dividas WHERE cliente_id = ? AND quitada = 1";
                PreparedStatement stmtQuitados = connection.prepareStatement(sqlQuitados);
                stmtQuitados.setInt(1, clienteId);
                ResultSet rsQuitados = stmtQuitados.executeQuery();
                while (rsQuitados.next()) {
                    System.out.println("Valor Quitado: " + rsQuitados.getDouble("valor_produto") + " - Data de Quitação: " + rsQuitados.getString("data_quitacao"));
                }
            } else {
                System.out.println("Cliente não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar extrato do cliente: " + e.getMessage());
        }
    }
}
