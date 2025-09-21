import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario implements Identificavel<Long> {
    private final Long id;
    private final String nome;
    private final String cargo;
    private final BigDecimal salario;
    private final LocalDate dataAdmissao;

    public Funcionario(Long id, String nome, String cargo, BigDecimal salario, LocalDate dataAdmissao) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.cargo = Objects.requireNonNull(cargo, "Cargo não pode ser nulo");
        this.salario = Objects.requireNonNull(salario, "Salário não pode ser nulo");
        this.dataAdmissao = Objects.requireNonNull(dataAdmissao, "Data de admissão não pode ser nula");

        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo");
        }
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (cargo.trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo não pode ser vazio");
        }
        if (salario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Salário não pode ser negativo");
        }
        if (dataAdmissao.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de admissão não pode ser futura");
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Funcionario that = (Funcionario) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Funcionario[id=%d, nome=%s, cargo=%s, salario=R$%.2f]",
                id, nome, cargo, salario);
    }
}