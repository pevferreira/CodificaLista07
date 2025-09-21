import java.math.BigDecimal;

public class RetiradaNaLoja implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        return BigDecimal.ZERO; // Frete grátis para retirada
    }

    @Override
    public String toString() {
        return "Retirada na Loja";
    }
}