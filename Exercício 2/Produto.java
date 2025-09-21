public class Produto {

    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        setNome(nome);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome.trim();
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void aplicarDesconto(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 50) {
            throw new IllegalArgumentException(
                    String.format("Desconto inválido: %.1f%%. Deve estar entre 0 e 50.", porcentagem));
        }

        double desconto = this.preco * (porcentagem / 100);
        this.preco -= desconto;

        System.out.printf("✅ Desconto de %.1f%% aplicado! Novo preço: R$ %.2f\n",
                porcentagem, this.preco);
    }

    @Override
    public String toString() {
        return String.format("Produto: %s | Preço: R$ %.2f | Estoque: %d unidades",
                nome, preco, quantidadeEmEstoque);
    }
}