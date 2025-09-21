import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Boleto extends FormaPagamento {
    private String codigoBarras;
    private LocalDate dataVencimento;

    public Boleto(String codigoBarras, LocalDate dataVencimento) {
        super("Boleto Bancário");
        this.codigoBarras = codigoBarras;
        this.dataVencimento = dataVencimento;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        if (codigoBarras == null || !codigoBarras.matches("\\d{44,48}")) {
            throw new PagamentoInvalidoException("Código de barras inválido. Deve conter 44 a 48 dígitos");
        }

        if (dataVencimento == null || dataVencimento.isBefore(LocalDate.now())) {
            throw new PagamentoInvalidoException("Data de vencimento deve ser futura");
        }

        System.out.println("✅ Validação do boleto aprovada");
    }

    @Override
    public void processarPagamento(BigDecimal valor) throws PagamentoInvalidoException {
        validarPagamento();
        validarValor(valor);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.printf("🧾 Processando pagamento de R$ %,.2f via Boleto\n", valor);
        System.out.printf("   Código de barras: %s\n", codigoBarras);
        System.out.printf("   Vencimento: %s\n", dataVencimento.format(formatter));
        System.out.println("✅ Boleto gerado com sucesso! Pague em qualquer agência bancária.");
    }
}