import Entities.Estoque;
import Entities.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Estoque estoque = new Estoque();

        while (true) {
            // Exibe o menu de escolhas
            System.out.println("\nGerenciamento de Estoque");
            System.out.println("1: Cadastrar Produto");
            System.out.println("2: Realizar Venda");
            System.out.println("3: Listar Produtos");
            System.out.println("4: Listar Produtos com Estoque Baixo");
            System.out.println("5: Buscar Produto");
            System.out.println("6: Calcular Valor Total do Estoque");
            System.out.println("0: Sair");
            System.out.print("Informe oque deseja:  ");

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
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

    }

    // Método para cadastrar um novo produto verificando se o código ja foi cadastrado, se a quantidade e o preço é positivo
    private static void cadastrarProduto(Estoque estoque, Scanner scanner) {

        String nome = cadastrarNome(scanner);

        String codigo = cadastrarCodigo(estoque, scanner);

        double valor = solicitarPreco(scanner);

        int quantidadeEstoque = solicitarQuantidade(scanner);

        String fornecedor = cadastrarFornecedor(scanner);

        // Cria um novo produto e o guarda no estoque
        Produto produto = new Produto(nome, codigo, valor, quantidadeEstoque, fornecedor);
        estoque.cadastrarProduto(produto);
    }

    // Método para realizar uma venda validando se o valor informado é menor que a quantidade em estoque e maior que 0
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

    // Método para listar produtos com estoque abaixo de um valor mínimo
    private static void listarProdutosEstoqueBaixo(Estoque estoque, Scanner scanner) {
        int quantidadeMinima = solicitarQuantidade(scanner);

        estoque.listarProdutosEstoqueBaixo(quantidadeMinima);
    }

    // Método para buscar um produto pelo código ou nome
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

    // Método para cadastrar o nome do produto
    private static String cadastrarNome(Scanner scanner) {
        String nome;
        while (true) {
            System.out.print("Digite o nome do produto: ");
            nome = scanner.nextLine();
            if (nome.isEmpty()) {
                System.out.println("**** ERRO!: INFORME O NOME DO FORNECEDOR! ****");
            } else {
                break;
            }

        }
        return nome;
    }

    // Método para cadastrar o nome do produto
    private static String cadastrarFornecedor(Scanner scanner) {
        String fornecedor;
        while (true) {
            System.out.print("Digite o nome do fornecedor: ");
            fornecedor = scanner.nextLine();
            if (fornecedor.isEmpty()) {
                System.out.println("**** ERRO!: INFORME O NOME DO FORNECEDOR! ****");
            } else {
                break;
            }

        }
        return fornecedor;
    }

    // Método para buscar um produto pelo código ou nome
    private static String cadastrarCodigo(Estoque estoque, Scanner scanner) {
        String codigo;
        while (true) {
            System.out.print("Digite o código do produto: ");
            codigo = scanner.nextLine();
            if (codigo.isEmpty()) {
                System.out.println("ERRO!: INFORME O CODIGO DO PRODUTO!");
                continue;
            }
            Produto produtoBuscado = estoque.buscarProduto(codigo, codigo);
            if (produtoBuscado != null) {
                System.out.println("Codigo ja Cadastrado: " + produtoBuscado);
            } else {
                break;
            }
        }
        return codigo;
    }

    // Método para solicitar o preço do produto, garantindo que seja um valor positivo
    private static double solicitarPreco(Scanner scanner) {
        double valor;
        while (true) {
            System.out.print("Digite o preço do produto: ");
            valor = scanner.nextDouble();
            scanner.nextLine();

            if (valor > 0) {
                break;
            } else {
                System.out.println("O preço deve ser um valor positivo.");
            }
        }
        return valor;
    }

    // Método para solicitar a quantidade do produto, garantindo que seja um valor positivo inteiro
    private static int solicitarQuantidade(Scanner scanner) {
        int quantidade;
        while (true) {
            System.out.print("Digite a quantidade do produto: ");
            quantidade = scanner.nextInt();
            scanner.nextLine();

            if (quantidade > 0) {
                break;
            } else {
                System.out.println("O quantidade deve ser um valor positivo.");
            }
        }
        return quantidade;
    }

}