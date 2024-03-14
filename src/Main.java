import processos.Estoque;
import processos.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();

        while (true) {
            // Exibe o menu de escolhas
            System.out.println("\nSistema de Gerenciamento de Estoque");
            System.out.println("1: Cadastrar Produto");
            System.out.println("2: Realizar Venda");
            System.out.println("3: Listar Produtos");
            System.out.println("4: Listar Produtos com Estoque Baixo");
            System.out.println("5: Buscar Produto");
            System.out.println("6: Calcular Valor Total do Estoque");
            System.out.println("0: Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();
            // Executa a opção escolhida pelo usuário
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

    // Função para cadastrar um novo produto
    private static void cadastrarProduto(Estoque estoque, Scanner scanner) {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        String codigo = cadastrarCodigo(estoque,scanner);

        double preco = solicitarPreco(scanner);

        int quantidadeEstoque = solicitarQuantidade(scanner);

        System.out.print("Digite o fornecedor do produto: ");
        String fornecedor = scanner.nextLine();

        // Cria um novo produto e o cadastra no estoque
        Produto novoProduto = new Produto(nome, codigo, preco, quantidadeEstoque, fornecedor);
        estoque.cadastrarProduto(novoProduto);
    }

    // Função para realizar uma venda
    private static void realizarVenda(Estoque estoque, Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        String codigo = scanner.nextLine();

        Produto produto = estoque.buscarProduto(codigo, "");
        if (produto != null) {
            int quantidade = solicitarQuantidade(scanner);

            if (produto.getQuantidadeEstoque() >= quantidade) {
                estoque.realizarVenda(codigo, quantidade);
            } else {
                System.out.println("Estoque insuficiente para realizar a venda.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Função para listar produtos com estoque abaixo de um valor mínimo
    private static void listarProdutosEstoqueBaixo(Estoque estoque, Scanner scanner) {
        int quantidadeMinima = solicitarQuantidade(scanner);

        estoque.listarProdutosEstoqueBaixo(quantidadeMinima);
    }

    // Função para buscar um produto pelo código ou nome
    private static void buscarProduto(Estoque estoque, Scanner scanner) {
        System.out.print("Digite o código ou nome do produto: ");
        String codigoOuNome = scanner.nextLine();

        Produto produtoBuscado = estoque.buscarProduto(codigoOuNome, codigoOuNome);
        if (produtoBuscado != null) {
            System.out.println("Produto encontrado: " + produtoBuscado);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Função para buscar um produto pelo código ou nome
    private static String cadastrarCodigo(Estoque estoque, Scanner scanner) {
        String codigo;
        while (true) {
            System.out.print("Digite o código do produto: ");
            codigo = scanner.nextLine();

            Produto produtoBuscado = estoque.buscarProduto(codigo, codigo);
            if (produtoBuscado != null) {
                System.out.println("Codigo ja Cadastrado: " + produtoBuscado);
            }else{
                break;
            }
        }
        return codigo;
    }

    // Função para solicitar o preço do produto, garantindo que seja um valor positivo
    private static double solicitarPreco(Scanner scanner) {
        double preco;
        while (true) {
            System.out.print("Digite o preço do produto: ");
            preco = scanner.nextDouble();
            scanner.nextLine();

            if (preco < 0) {
                System.out.println("O preço deve ser um valor positivo.");
            } else {
                break;
            }
        }
        return preco;
    }

    // Função para solicitar a quantidade do produto, garantindo que seja um valor positivo inteiro
    private static int solicitarQuantidade(Scanner scanner) {
        int quantidade;
        while (true) {
            System.out.print("Digite a quantidade do produto: ");
            quantidade = scanner.nextInt();
            scanner.nextLine();

            if (quantidade < 0) {
                System.out.println("A quantidade deve ser um valor positivo inteiro.");
            } else {
                break;
            }
        }
        return quantidade;
    }

}