package processos;

import java.util.ArrayList;

public class Estoque {
    private final ArrayList<Produto> produtos;
    private static final int QUANTIDADE_MINIMA = 100; // Definindo a quantidade mínima como constante de classe

    // Construtor que inicializa a lista de produtos
    public Estoque() {
        produtos = new ArrayList<>();
    }

    // Método para cadastrar um novo produto no estoque
    public void cadastrarProduto(Produto produto) {
        // Verifica se o produto já está cadastrado pelo código
        if (buscarProduto(produto.getCodigo(), "") == null) {
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
        if (produto != null) {
            // Verifica se há estoque suficiente para a venda
            if (produto.getQuantidadeEstoque() >= quantidade) {
                produto.venderProduto(quantidade);
                System.out.println("Venda realizada com sucesso.");
                if (produto.getQuantidadeEstoque() <= QUANTIDADE_MINIMA) { // Verifica se o estoque ficou abaixo do mínimo
                    System.out.println("Produto com estoque baixo!");
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
        for (Produto produto : produtos) {
            System.out.println("Nome: " + produto.getNome() + ", Código: " + produto.getCodigo() +
                    ", Preço: " + produto.getPreco() + ", Estoque: " + produto.getQuantidadeEstoque() +
                    ", Fornecedor: " + produto.getFornecedor());
        }
        listarProdutosEstoqueBaixo();
    }

    // Método para listar produtos com estoque abaixo de um valor definido
    public void listarProdutosEstoqueBaixo(int quantidadeMinima) {
        // Variável para controlar se pelo menos um produto com estoque baixo foi encontrado
        boolean encontrouProdutoBaixo = false;
        System.out.println("Produtos com estoque abaixo de " + quantidadeMinima + ":");
        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() < quantidadeMinima) {
                System.out.println("Nome: " + produto.getNome() + ", Código: " + produto.getCodigo() +
                        ", Estoque: " + produto.getQuantidadeEstoque());
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
            if (produto.getQuantidadeEstoque() < QUANTIDADE_MINIMA) {
                if (!encontrouProdutoBaixo) {
                    System.out.println("\nPRODUTOS COM ESTOQUE A BAIXO DE " + QUANTIDADE_MINIMA);
                    encontrouProdutoBaixo = true; // Indica que pelo menos um produto com estoque baixo foi encontrado
                }
                System.out.println("Nome: " + produto.getNome() + ", Código: " + produto.getCodigo() +
                        ", Preço: " + produto.getPreco() + ", Estoque: " + produto.getQuantidadeEstoque() +
                        ", Fornecedor: " + produto.getFornecedor());
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