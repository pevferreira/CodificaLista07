import java.math.BigDecimal;

public class Pac implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        // Lógica fictícia: R$ 10 + R$ 1.5 por kg
        BigDecimal base = new BigDecimal("10.00");
        BigDecimal porPeso = BigDecimal.valueOf(pedido.getPeso() * 1.5);
        return base.add(porPeso);
    }

    @Override
    public String toString() {
        return "PAC";
    }
}