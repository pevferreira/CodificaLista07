import java.math.BigDecimal;

public class Sedex implements CalculadoraFrete {
    @Override
    public BigDecimal calcular(Pedido pedido) {
        // Lógica fictícia: R$ 15 + R$ 2 por kg
        BigDecimal base = new BigDecimal("15.00");
        BigDecimal porPeso = BigDecimal.valueOf(pedido.getPeso() * 2);
        return base.add(porPeso);
    }

    @Override
    public String toString() {
        return "SEDEX";
    }
}