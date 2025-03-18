import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import service.ClienteService;
import service.DividaService;

public class App {
    //private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static Connection connection;
    private ClienteService clienteService;
    private DividaService dividaService;

    public App() {
        clienteService = new ClienteService();
        dividaService = new DividaService();
        
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
                        clienteService.cadastrarCliente(scanner);                       
                    }
                    case 2 ->  {
                        clienteService.consultarCliente(scanner);
                    }
                    case 3 -> {
                        dividaService.adicionarDivida(scanner);
                    }
                    case 4 -> {
                        dividaService.quitarDivida(scanner);
                    }
                    case 5 -> {
                        clienteService.excluirCliente(scanner);
                    }
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

    public static void main(String[] args) {
        new App();    
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
