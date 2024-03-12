package processos;

public class Produto {
   public String nome;
   public String codigo;
   public double preco;
   public int quantidadeEstoque;
   public String fornecedor;

    // Construtor para inicializar os atributos do produto
    public Produto(String nome, String codigo, double preco, int quantidadeEstoque, String fornecedor) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Produto" +
                "nome=: '" + nome + '\'' +
                ", codigo: '" + codigo + '\'' +
                ", preco: " + preco +
                ", quantidadeEstoque: " + quantidadeEstoque +
                ", fornecedor: '" + fornecedor + '\'';
    }
}