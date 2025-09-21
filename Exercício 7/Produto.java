import java.math.BigDecimal;

public class Produto implements Identificavel<String> {
    private final String id;
    private final String nome;
    private final BigDecimal preco;

    public Produto(String id, String nome, BigDecimal preco) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.preco = Objects.requireNonNull(preco, "Preço não pode ser nulo");

        if (id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID não pode ser vazio");
        }
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (preco.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Produto[id=%s, nome=%s, preco=R$%.2f]", id, nome, preco);
    }
}