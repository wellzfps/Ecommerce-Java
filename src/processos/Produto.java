package processos;

public class Produto {
    private String nome;
    private String codigo;
    private double preco;
    private int quantidadeEstoque;
    private String fornecedor;

    // Construtor para inicializar os atributos do produto
    public Produto(String nome, String codigo, double preco, int quantidadeEstoque, String fornecedor) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedor = fornecedor;
    }

    // Métodos getters e setters para os atributos
    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    // Método para vender uma quantidade do produto
    public void venderProduto(int quantidade) {
        if (quantidade <= quantidadeEstoque) {
            quantidadeEstoque -= quantidade;
        } else {
            System.out.println("Estoque insuficiente para realizar a venda.");
        }
    }

    // Método para adicionar uma quantidade ao estoque do produto
    public void adicionarProduto(int quantidade) {
        quantidadeEstoque += quantidade;
    }
    @Override
    public String toString() {
        return "PRODUTO: " +
                "nome: '" + nome + '\'' +
                ", codigo: '" + codigo + '\'' +
                ", preco: " + preco +
                ", quantidadeEstoque: " + quantidadeEstoque +
                ", fornecedor: '" + fornecedor + '\'';
    }
}