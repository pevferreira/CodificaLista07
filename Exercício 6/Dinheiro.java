import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Dinheiro {
    private final BigDecimal valor;
    private final Moeda moeda;

    public Dinheiro(BigDecimal valor, Moeda moeda) {
        this.valor = Objects.requireNonNull(valor, "Valor não pode ser nulo")
                .setScale(2, RoundingMode.HALF_EVEN);
        this.moeda = Objects.requireNonNull(moeda, "Moeda não pode ser nula");

        if (this.valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }
    }

    public Dinheiro(double valor, Moeda moeda) {
        this(BigDecimal.valueOf(valor), moeda);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public Dinheiro adicionar(Dinheiro outro) {
        validarMoedaCompativel(outro);
        return new Dinheiro(this.valor.add(outro.valor), this.moeda);
    }

    public Dinheiro subtrair(Dinheiro outro) {
        validarMoedaCompativel(outro);
        BigDecimal novoValor = this.valor.subtract(outro.valor);
        if (novoValor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Resultado não pode ser negativo");
        }
        return new Dinheiro(novoValor, this.moeda);
    }

    public Dinheiro multiplicar(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        return new Dinheiro(this.valor.multiply(BigDecimal.valueOf(quantidade)), this.moeda);
    }

    public Dinheiro aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual deve estar entre 0 e 100");
        }
        BigDecimal fatorDesconto = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(percentual).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_EVEN));
        return new Dinheiro(this.valor.multiply(fatorDesconto), this.moeda);
    }

    private void validarMoedaCompativel(Dinheiro outro) {
        if (!this.moeda.equals(outro.moeda)) {
            throw new IllegalArgumentException("Moedas incompatíveis: " + this.moeda + " != " + outro.moeda);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Dinheiro dinheiro = (Dinheiro) o;
        return valor.compareTo(dinheiro.valor) == 0 && moeda == dinheiro.moeda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, moeda);
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", moeda.getSimbolo(), valor);
    }
}