package processos;

import java.util.ArrayList;

public class Estoque {
    private final ArrayList<Produto> produtos;

    // Construtor que inicializa a lista de produtos
    public Estoque() {
        produtos = new ArrayList<>();
    }

    // Método para cadastrar um novo produto no estoque
    public void cadastrarProduto(Produto produto) {
        // Verifica se o produto já está cadastrado pelo código
        if (buscarProduto(produto.codigo, "") == null) {
            produtos.add(produto);
            System.out.println("Produto cadastrado com sucesso.");
        } else {
            System.out.println("Produto já cadastrado.");
        }
    }

    // Método para realizar uma venda e atualizar o estoque
    public void realizarVenda(String codigo, int quantidade) {
        // Busca o produto pelo código
        Produto produto = buscarProduto(codigo, "");
        //constante para quantidade minima
        final int quantidadeMinima = 100;
        if (produto != null) {
            // Verifica se há estoque suficiente para a venda
            if (produto.quantidadeEstoque >= quantidade) {
                produto.quantidadeEstoque -= quantidade;
                System.out.println("Venda realizada com sucesso.");
                if (produto.quantidadeEstoque <= quantidadeMinima) {
                    System.out.print("Prdouto com estoque baixo!");
                }
            } else {
                System.out.println("Estoque insuficiente para realizar a venda.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Método para listar todos os produtos no estoque
    public void listarProdutos() {
        System.out.println("Lista de Produtos:");
        final int quantidadeMinima = 100;
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.nome + ", Código: " + produto.codigo +
                    ", Preço: " + produto.preco + ", Estoque: " + produto.quantidadeEstoque +
                    ", Fornecedor: " + produto.fornecedor);
            if (produto.quantidadeEstoque <= quantidadeMinima) {
                System.out.print("Prdouto com estoque baixo!");
            }
        }
    }

    // Método para listar produtos com estoque abaixo de um valor definido
    public void listarProdutosEstoqueBaixo(int quantidadeMinima) {
        System.out.println("Produtos com estoque abaixo de " + quantidadeMinima + ":");
        for (Produto produto : produtos) {
            if (produto.quantidadeEstoque < quantidadeMinima) {
                System.out.println("Nome: " + produto.nome + ", Código: " + produto.codigo +
                        ", Estoque: " + produto.quantidadeEstoque);
            }
        }
    }

    // Método para buscar um produto pelo código ou nome
    public Produto buscarProduto(String codigo, String nome) {
        for (Produto produto : produtos) {
            if (produto.codigo.equals(codigo) || produto.nome.equals(nome)) {
                return produto;
            }
        }
        return null;
    }
    // Método para calcular o valor total do estoque
    public double calcularValorTotalEstoque() {
        double valorTotal = 0;
        for (Produto produto : produtos) {
            valorTotal += produto.preco * produto.quantidadeEstoque;
        }
        return valorTotal;
    }
}
