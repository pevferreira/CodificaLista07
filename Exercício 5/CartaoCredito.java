import java.math.BigDecimal;

public class CartaoCredito extends FormaPagamento {
    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;
    private String cvv;

    public CartaoCredito(String numeroCartao, String nomeTitular, String dataValidade, String cvv) {
        super("Cartão de Crédito");
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        if (numeroCartao == null || numeroCartao.replaceAll("\\s", "").length() != 16) {
            throw new PagamentoInvalidoException("Número do cartão deve ter 16 dígitos");
        }

        if (nomeTitular == null || nomeTitular.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Nome do titular é obrigatório");
        }

        if (dataValidade == null || !dataValidade.matches("\\d{2}/\\d{2}")) {
            throw new PagamentoInvalidoException("Data de validade deve estar no formato MM/AA");
        }

        if (cvv == null || !cvv.matches("\\d{3,4}")) {
            throw new PagamentoInvalidoException("CVV deve ter 3 ou 4 dígitos");
        }

        System.out.println("✅ Validação do cartão de crédito aprovada");
    }

    @Override
    public void processarPagamento(BigDecimal valor) throws PagamentoInvalidoException {
        validarPagamento();
        validarValor(valor);

        System.out.printf("💳 Processando pagamento de R$ %,.2f via Cartão de Crédito\n", valor);
        System.out.printf("   Número: %s\n", mascararNumeroCartao());
        System.out.printf("   Titular: %s\n", nomeTitular);
        System.out.println("✅ Pagamento com cartão processado com sucesso!");
    }

    private String mascararNumeroCartao() {
        if (numeroCartao.length() <= 4)
            return numeroCartao;
        return "**** **** **** " + numeroCartao.substring(numeroCartao.length() - 4);
    }
}