import java.util.Objects;

public final class ItemCarrinho {
    private final Produto produto;
    private final int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        this.produto = Objects.requireNonNull(produto, "Produto não pode ser nulo");

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Dinheiro getSubtotal() {
        return produto.getPreco().multiplicar(quantidade);
    }

    public ItemCarrinho comQuantidade(int novaQuantidade) {
        return new ItemCarrinho(produto, novaQuantidade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ItemCarrinho that = (ItemCarrinho) o;
        return produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto);
    }

    @Override
    public String toString() {
        return String.format("%d x %s = %s", quantidade, produto.getNome(), getSubtotal());
    }
}