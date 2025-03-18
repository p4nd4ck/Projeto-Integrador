import java.util.InputMismatchException;
import java.util.Scanner;

import service.ClienteService;
import service.DividaService;
import service.ProdutoService;
import service.QuitacaoService;

public class App {
    private ClienteService clienteService;
    private DividaService dividaService;
    private ProdutoService produtoService;
    private QuitacaoService quitacaoService;

    public App() {
        clienteService = new ClienteService();
        dividaService = new DividaService();
        produtoService = new ProdutoService();
        quitacaoService = new QuitacaoService();
        
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Sistema de Gestão de Dívidas ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Cliente");
            System.out.println("3. Consultar Produtos");
            System.out.println("4. Adicionar Compra");
            System.out.println("5. Quitar Dívida");
            System.out.println("6. Excluir Cliente");
            System.out.println("7. Extrato do Cliente");
            System.out.println("8. Sair");
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
                        produtoService.consultarProdutos();
                    }
                    case 4 -> {
                        dividaService.adicionarDivida(scanner);
                    }
                    case 5 -> {
                        quitacaoService.quitarDivida(scanner);
                    }
                    case 6 -> {
                        clienteService.excluirCliente(scanner);
                    }
                    case 7 -> {
                        clienteService.consultarExtratoCliente(scanner);
                    }
                    case 8 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 8);
        scanner.close();
    }

    public static void main(String[] args) {
        new App();    
    }
}
