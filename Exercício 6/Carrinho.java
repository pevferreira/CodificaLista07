import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Carrinho {
    private final List<ItemCarrinho> itens;
    private final double percentualDesconto;

    public Carrinho() {
        this.itens = new ArrayList<>();
        this.percentualDesconto = 0.0;
    }

    private Carrinho(List<ItemCarrinho> itens, double percentualDesconto) {
        this.itens = Collections.unmodifiableList(new ArrayList<>(itens));
        this.percentualDesconto = percentualDesconto;

        if (percentualDesconto < 0 || percentualDesconto > 30.0) {
            throw new IllegalArgumentException("Desconto deve estar entre 0% e 30%");
        }
    }

    public Carrinho adicionarItem(Produto produto, int quantidade) {
        ItemCarrinho novoItem = new ItemCarrinho(produto, quantidade);
        List<ItemCarrinho> novosItens = new ArrayList<>(this.itens);

        // Remove item existente se já estiver no carrinho
        novosItens.removeIf(item -> item.getProduto().equals(produto));
        novosItens.add(novoItem);

        return new Carrinho(novosItens, this.percentualDesconto);
    }

    public Carrinho removerItem(Produto produto) {
        List<ItemCarrinho> novosItens = new ArrayList<>(this.itens);
        novosItens.removeIf(item -> item.getProduto().equals(produto));
        return new Carrinho(novosItens, this.percentualDesconto);
    }

    public Carrinho alterarQuantidade(Produto produto, int novaQuantidade) {
        List<ItemCarrinho> novosItens = new ArrayList<>();

        for (ItemCarrinho item : this.itens) {
            if (item.getProduto().equals(produto)) {
                novosItens.add(item.comQuantidade(novaQuantidade));
            } else {
                novosItens.add(item);
            }
        }

        return new Carrinho(novosItens, this.percentualDesconto);
    }

    public Carrinho aplicarCupom(double percentualDesconto) {
        return new Carrinho(this.itens, percentualDesconto);
    }

    public Carrinho removerCupom() {
        return new Carrinho(this.itens, 0.0);
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    public Dinheiro getSubtotal() {
        Dinheiro subtotal = new Dinheiro(BigDecimal.ZERO, Moeda.BRL);
        for (ItemCarrinho item : itens) {
            subtotal = subtotal.adicionar(item.getSubtotal());
        }
        return subtotal;
    }

    public Dinheiro getDesconto() {
        if (percentualDesconto == 0.0) {
            return new Dinheiro(BigDecimal.ZERO, Moeda.BRL);
        }
        return getSubtotal().aplicarDesconto(percentualDesconto);
    }

    public Dinheiro getTotal() {
        return getSubtotal().subtrair(getDesconto());
    }

    public Optional<ItemCarrinho> getItem(Produto produto) {
        return itens.stream()
                .filter(item -> item.getProduto().equals(produto))
                .findFirst();
    }

    public int getQuantidadeTotal() {
        return itens.stream()
                .mapToInt(ItemCarrinho::getQuantidade)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("🛒 CARRINHO DE COMPRAS\n");
        sb.append("====================\n");

        for (ItemCarrinho item : itens) {
            sb.append(item).append("\n");
        }

        sb.append("====================\n");
        sb.append(String.format("Subtotal: %s\n", getSubtotal()));

        if (percentualDesconto > 0) {
            sb.append(String.format("Desconto (%.1f%%): -%s\n", percentualDesconto, getDesconto()));
        }

        sb.append(String.format("TOTAL: %s\n", getTotal()));
        sb.append(String.format("Itens: %d | Quantidade total: %d",
                itens.size(), getQuantidadeTotal()));

        return sb.toString();
    }
}