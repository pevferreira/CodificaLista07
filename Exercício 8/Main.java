import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== PADRÃO STRATEGY - CÁLCULO DE FRETE ===\n");

        // Criando pedido
        Pedido pedido = new Pedido("12345678", new BigDecimal("250.00"), 2.5);
        System.out.println(pedido);
        System.out.println();

        // Demonstração de troca de estratégia em tempo de execução
        System.out.println("=== TROCA DE ESTRATÉGIAS EM TEMPO DE EXECUÇÃO ===\n");

        // 1. SEDEX (padrão)
        System.out.println("1. SEDEX (padrão):");
        BigDecimal freteSedex = pedido.calcularFrete();
        System.out.printf("Frete: R$ %.2f\n", freteSedex);
        System.out.println();

        // 2. PAC
        pedido.setCalculadoraFrete(new Pac());
        System.out.println("2. PAC:");
        BigDecimal fretePac = pedido.calcularFrete();
        System.out.printf("Frete: R$ %.2f\n", fretePac);
        System.out.println();

        // 3. Retirada na Loja
        pedido.setCalculadoraFrete(new RetiradaNaLoja());
        System.out.println("3. Retirada na Loja:");
        BigDecimal freteRetirada = pedido.calcularFrete();
        System.out.printf("Frete: R$ %.2f\n", freteRetirada);
        System.out.println();

        // 4. Estratégia com lambda - Frete grátis acima de R$ 300
        pedido.setCalculadoraFrete(
                new Sedex().comDesconto(new BigDecimal("5.00")) // Desconto fixo primeiro
        );
        System.out.println("4. SEDEX com desconto de R$ 5,00:");
        System.out.printf("Frete: R$ %.2f\n", pedido.calcularFrete());
        System.out.println();

        // 5. Composição de estratégias
        CalculadoraFrete estrategiaComposta = new Sedex()
                .comDesconto(new BigDecimal("3.00"))
                .comDescontoPercentual(10.0);

        pedido.setCalculadoraFrete(estrategiaComposta);
        System.out.println("5. SEDEX com desconto de R$ 3,00 + 10%:");
        System.out.printf("Frete: R$ %.2f\n", pedido.calcularFrete());
        System.out.println();

        // 6. Frete grátis acima de R$ 200 (lambda)
        pedido.setCalculadoraFrete(
                EstrategiasPromocionais.freteGratisAcimaDe(new BigDecimal("200.00")));
        System.out.println("6. Frete grátis acima de R$ 200,00:");
        System.out.printf("Frete: R$ %.2f\n", pedido.calcularFrete());
        System.out.println();

        // 7. Testando com valor menor (não ganha frete grátis)
        pedido.setValorTotal(new BigDecimal("150.00"));
        System.out.println("7. Valor do pedido reduzido para R$ 150,00:");
        System.out.printf("Frete: R$ %.2f\n", pedido.calcularFrete());
        System.out.println();

        // 8. Desconto progressivo
        pedido.setValorTotal(new BigDecimal("450.00"));
        pedido.setCalculadoraFrete(EstrategiasPromocionais.descontoProgressivo());
        System.out.println("8. Desconto progressivo (R$ 450,00):");
        System.out.printf("Frete: R$ %.2f\n", pedido.calcularFrete());
        System.out.println();

        // 9. Frete grátis por região (lambda)
        pedido.setCep("80000000"); // Região Sul
        pedido.setCalculadoraFrete(
                EstrategiasPromocionais.freteGratisRegiao("89") // Sul e Sudeste
        );
        System.out.println("9. Frete grátis para região Sul (CEP 80000-000):");
        System.out.printf("Frete: R$ %.2f\n", pedido.calcularFrete());
        System.out.println();

        // Testes de validação
        System.out.println("=== TESTES DE VALIDAÇÃO ===\n");

        try {
            new Pedido("123", new BigDecimal("100.00"), 1.0);
        } catch (CepInvalidoException e) {
            System.out.println("✅ CEP inválido capturado: " + e.getMessage());
        }

        try {
            new Pedido("12345678", new BigDecimal("-100.00"), 1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Valor negativo capturado: " + e.getMessage());
        }

        try {
            new Pedido("12345678", new BigDecimal("100.00"), -1.0);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Peso negativo capturado: " + e.getMessage());
        }

        System.out.println("\n=== DEMONSTRAÇÃO CONCLUÍDA ===");
    }
}