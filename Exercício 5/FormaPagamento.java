import java.math.BigDecimal;

public abstract class FormaPagamento {
    protected String descricao;

    public FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public abstract void validarPagamento() throws PagamentoInvalidoException;

    public abstract void processarPagamento(BigDecimal valor) throws PagamentoInvalidoException;

    public String getDescricao() {
        return descricao;
    }

    protected void validarValor(BigDecimal valor) throws PagamentoInvalidoException {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor do pagamento deve ser positivo");
        }
    }
}