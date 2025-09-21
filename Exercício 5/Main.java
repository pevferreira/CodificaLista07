import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PAGAMENTOS ===\n");

        // Criando diferentes formas de pagamento
        List<FormaPagamento> pagamentos = new ArrayList<>();

        try {
            pagamentos.add(new CartaoCredito("1234567812345678", "João Silva", "12/25", "123"));
            pagamentos.add(new Boleto("12345678901234567890123456789012345678901234",
                    LocalDate.now().plusDays(5)));
            pagamentos.add(new Pix("joao.silva@email.com", "email"));
            pagamentos.add(new Pix("12345678901", "cpf"));
            pagamentos.add(new Pix("+5511999999999", "telefone"));

        } catch (Exception e) {
            System.out.println("Erro ao criar formas de pagamento: " + e.getMessage());
        }

        // Demonstração de polimorfismo
        System.out.println("=== PROCESSANDO PAGAMENTOS ===\n");

        BigDecimal[] valores = {
                new BigDecimal("150.50"),
                new BigDecimal("299.99"),
                new BigDecimal("75.00"),
                new BigDecimal("500.00"),
                new BigDecimal("89.90")
        };

        for (int i = 0; i < pagamentos.size(); i++) {
            FormaPagamento pagamento = pagamentos.get(i);
            BigDecimal valor = valores[i];

            System.out.println("--- " + pagamento.getDescricao() + " ---");

            try {
                pagamento.processarPagamento(valor);
                System.out.println("✅ Pagamento realizado com sucesso!\n");

            } catch (PagamentoInvalidoException e) {
                System.out.println("Erro no pagamento: " + e.getMessage() + "\n");
            }
        }

        // Testes de validações com erro
        System.out.println("=== TESTES DE VALIDAÇÃO COM ERRO ===\n");

        try {
            FormaPagamento cartaoInvalido = new CartaoCredito("123", "João", "12/25", "123");
            cartaoInvalido.processarPagamento(new BigDecimal("100.00"));
        } catch (Exception e) {
            System.out.println("Cartão inválido: " + e.getMessage());
        }

        try {
            FormaPagamento boletoInvalido = new Boleto("123", LocalDate.now().minusDays(1));
            boletoInvalido.processarPagamento(new BigDecimal("100.00"));
        } catch (Exception e) {
            System.out.println("Boleto inválido: " + e.getMessage());
        }

        try {
            FormaPagamento pixInvalido = new Pix("invalido", "tipo-invalido");
            pixInvalido.processarPagamento(new BigDecimal("100.00"));
        } catch (Exception e) {
            System.out.println("PIX inválido: " + e.getMessage());
        }

        try {
            FormaPagamento pixEmailInvalido = new Pix("email-invalido", "email");
            pixEmailInvalido.processarPagamento(new BigDecimal("100.00"));
        } catch (Exception e) {
            System.out.println("PIX email inválido: " + e.getMessage());
        }

        // Teste de valor inválido
        try {
            FormaPagamento cartao = new CartaoCredito("1234567812345678", "João", "12/25", "123");
            cartao.processarPagamento(new BigDecimal("-100.00"));
        } catch (Exception e) {
            System.out.println("Valor inválido: " + e.getMessage());
        }
    }
}