import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorEstoque estoque = new GerenciadorEstoque();
        int opcao;
        
        System.out.println("=== SISTEMA DE CONTROLE DE ESTOQUE ===");
        
        do {
            System.out.println("\n1. Cadastrar novo produto");
            System.out.println("2. Incrementar quantidade");
            System.out.println("3. Decrementar quantidade");
            System.out.println("4. Listar produtos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    scanner.nextLine();
                    String nome = scanner.nextLine();
                    
                    System.out.print("Preço: R$");
                    double preco = scanner.nextDouble();
                    
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    
                    if (estoque.cadastrarProduto(nome, preco, quantidade)) {
                        System.out.println("Produto cadastrado com sucesso!");
                    }
                    break;
                    
                case 2:
                    System.out.print("ID do produto: ");
                    int idIncremento = scanner.nextInt();
                    
                    System.out.print("Quantidade para incrementar: ");
                    int qtdIncremento = scanner.nextInt();
                    
                    if (estoque.incrementarQuantidade(idIncremento, qtdIncremento)) {
                        System.out.println("Quantidade incrementada com sucesso!");
                    }
                    break;
                    
                case 3:
                    System.out.print("ID do produto: ");
                    int idDecremento = scanner.nextInt();
                    
                    System.out.print("Quantidade para decrementar: ");
                    int qtdDecremento = scanner.nextInt();
                    
                    if (estoque.decrementarQuantidade(idDecremento, qtdDecremento)) {
                        System.out.println("Quantidade decrementada com sucesso!");
                    }
                    break;
                    
                case 4:
                    estoque.listarProdutos();
                    break;
                    
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            
        } while (opcao != 0);
        
        estoque.fecharConexao();
        scanner.close();
    }
}