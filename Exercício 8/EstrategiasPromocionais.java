import java.math.BigDecimal;

public class EstrategiasPromocionais {
    // Frete grátis para compras acima de certo valor
    public static CalculadoraFrete freteGratisAcimaDe(BigDecimal valorMinimo) {
        return pedido -> {
            if (pedido.getValorTotal().compareTo(valorMinimo) >= 0) {
                return BigDecimal.ZERO;
            }
            // Usa a estratégia padrão do pedido se não atingir o mínimo
            return pedido.getCalculadoraFrete().calcular(pedido);
        };
    }

    // Frete fixo com desconto progressivo
    public static CalculadoraFrete descontoProgressivo() {
        return pedido -> {
            BigDecimal freteBase = new Sedex().calcular(pedido);
            BigDecimal valorPedido = pedido.getValorTotal();

            // Desconto de 1% a cada R$ 100, até 50%
            double percentualDesconto = Math.min(
                    valorPedido.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_DOWN).doubleValue(),
                    50.0);

            BigDecimal desconto = freteBase.multiply(
                    BigDecimal.valueOf(percentualDesconto / 100));

            return freteBase.subtract(desconto).max(BigDecimal.ZERO);
        };
    }

    // Frete grátis para determinadas regiões (ex: Sul e Sudeste)
    public static CalculadoraFrete freteGratisRegiao(String regioes) {
        return pedido -> {
            String cep = pedido.getCep();
            if (cep != null && regioes.contains(cep.substring(0, 1))) {
                return BigDecimal.ZERO;
            }
            return pedido.getCalculadoraFrete().calcular(pedido);
        };
    }
}