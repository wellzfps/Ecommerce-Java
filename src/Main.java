import processos.Estoque;
import processos.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();

        while (true) {
            System.out.println("\n===== Sistema de Gerenciamento de Estoque =====");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Realizar Venda");
            System.out.println("3. Listar Produtos");
            System.out.println("4. Listar Produtos com Estoque Baixo");
            System.out.println("5. Buscar Produto");
            System.out.println("6. Calcular Valor Total do Estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    cadastrarProduto(estoque, scanner);
                    break;
                case 2:
                    realizarVenda(estoque, scanner);
                    break;
                case 3:
                    estoque.listarProdutos();
                    break;
                case 4:
                    listarProdutosEstoqueBaixo(estoque, scanner);
                    break;
                case 5:
                    buscarProduto(estoque, scanner);
                    break;
                case 6:
                    double valorTotalEstoque = estoque.calcularValorTotalEstoque();
                    System.out.println("Valor total do estoque: " + valorTotalEstoque);
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarProduto(Estoque estoque, Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();

        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();

        System.out.print("Digite a quantidade em estoque do produto: ");
        int quantidadeEstoque = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        System.out.print("Digite o fornecedor do produto: ");
        String fornecedor = scanner.nextLine();

        Produto novoProduto = new Produto(nome, codigo, preco, quantidadeEstoque, fornecedor);
        estoque.cadastrarProduto(novoProduto);
    }

    private static void realizarVenda(Estoque estoque, Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();

        System.out.print("Digite a quantidade a ser vendida: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        estoque.realizarVenda(codigo, quantidade);
    }

    private static void listarProdutosEstoqueBaixo(Estoque estoque, Scanner scanner) {
        System.out.print("Digite a quantidade mínima em estoque: ");
        int quantidadeMinima = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        estoque.listarProdutosEstoqueBaixo(quantidadeMinima);
    }

    private static void buscarProduto(Estoque estoque, Scanner scanner) {
        System.out.print("Digite o código ou nome do produto: ");
        String codigoOuNome = scanner.nextLine();

        Produto produtoBuscado = estoque.buscarProduto(codigoOuNome, codigoOuNome);
        if (produtoBuscado != null) {
            System.out.println("Produto encontrado: " + produtoBuscado.nome);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}