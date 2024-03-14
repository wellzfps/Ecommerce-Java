package Entities;

import java.util.ArrayList;

public class Estoque {
    private final ArrayList<Produto> produtos;

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    // Definindo a quantidade mínima como constante de classe
    private static final int quantidadeMinimaEmEstoque = 100;

    // Construtor que inicializa a lista de produtos
    public Estoque() {
        produtos = new ArrayList<>();
    }

    // Método para cadastrar um novo produto no estoque
    public void cadastrarProduto(Produto produto) {
        // Verifica se o produto já está cadastrado pelo código
        if (buscarProduto(produto.getCodigo(), produto.getNome()) == null) {
            produtos.add(produto);
            System.out.println("\nProduto foi cadastrado com sucesso.");
        } else {
            System.out.println("\nProduto já cadastrado!");
        }
    }

    // Método para realizar uma venda e atualizar o estoque
    public void realizarVenda(String codigoOuNome, int quantidade) {

        Produto produto = buscarProduto(codigoOuNome, "");
        if (produto != null) {
            // Verifica se há estoque suficiente para a venda
            if (produto.getQuantidadeEstoque() >= quantidade) {
                produto.venderProduto(quantidade);
                System.out.println("\nVenda realizada com sucesso.");
                // Verifica se o estoque ficou abaixo do mínimo
                if (produto.getQuantidadeEstoque() <= quantidadeMinimaEmEstoque) {
                    System.out.println("Produto com estoque baixo!");
                }
            } else {
                System.out.println("\nEstoque insuficiente para realizar a venda.");
            }
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }

    // para listar todos os produtos no estoque
    public void listarProdutos() {
        if (getProdutos().isEmpty()) {
            System.out.println("\nNao a produtos cadastrado");
        } else {
            System.out.println("\nLista de Produtos:");
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
            listarProdutosEstoqueBaixo();
        }
    }

    // Método para listar produtos com estoque abaixo de um valor definido
    public void listarProdutosEstoqueBaixo(int quantidadeMinima) {
        // Variável para controlar se pelo menos um produto com estoque baixo foi encontrado
        boolean encontrouProdutoBaixo = false;
        System.out.println("Produtos com estoque abaixo de " + quantidadeMinima + ":");
        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() < quantidadeMinima) {
                System.out.println(produto);
                encontrouProdutoBaixo = true; // Indica que pelo menos um produto com estoque baixo foi encontrado
            }
        }
        // Se nenhum produto com estoque baixo foi encontrado, exibe uma mensagem indicando isso
        if (!encontrouProdutoBaixo) {
            System.out.println("\nNenhum produto com estoque baixo.");
        }
    }

    // Método para listar produtos com estoque abaixo da quantidade mínima
    public void listarProdutosEstoqueBaixo() {
        // Variável para controlar se pelo menos um produto com estoque baixo foi encontrado
        boolean encontrouProdutoBaixo = false;

        // verificar quais estão com estoque abaixo da quantidade mínima
        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() < quantidadeMinimaEmEstoque) {
                if (!encontrouProdutoBaixo) {
                    System.out.println("\nPRODUTOS COM ESTOQUE A BAIXO DE " + quantidadeMinimaEmEstoque);
                    encontrouProdutoBaixo = true; // Indica que pelo menos um produto com estoque baixo foi encontrado
                }
                System.out.println(produto);
            }
        }

        // Se nenhum produto com estoque baixo foi encontrado, exibe uma mensagem indicando isso
        if (!encontrouProdutoBaixo) {
            System.out.println("\nNenhum produto com estoque baixo.");
        }
    }

    // Método para buscar um produto pelo código ou nome
    public Produto buscarProduto(String codigo, String nome) {
        for (Produto produto : produtos) {
            if (produto.getCodigo().equals(codigo) || produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }

    // Método para calcular o valor total do estoque
    public double calcularValorTotalEstoque() {
        double valorTotal = 0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco() * produto.getQuantidadeEstoque();
        }
        return valorTotal;
    }
}