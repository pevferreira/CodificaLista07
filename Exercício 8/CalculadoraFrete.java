import java.math.BigDecimal;

@FunctionalInterface
public interface CalculadoraFrete {
    BigDecimal calcular(Pedido pedido);

    // Métodos default para composição
    default CalculadoraFrete comDesconto(BigDecimal desconto) {
        return pedido -> {
            BigDecimal valor = calcular(pedido);
            return valor.subtract(desconto).max(BigDecimal.ZERO);
        };
    }

    default CalculadoraFrete comDescontoPercentual(double percentual) {
        return pedido -> {
            BigDecimal valor = calcular(pedido);
            BigDecimal desconto = valor.multiply(BigDecimal.valueOf(percentual / 100));
            return valor.subtract(desconto).max(BigDecimal.ZERO);
        };
    }
}