import java.math.BigDecimal;

public class Funcionario {
    protected String nome;
    protected BigDecimal salario;

    public Funcionario(String nome, BigDecimal salario) {
        setNome(nome);
        setSalario(salario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome.trim();
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário deve ser positivo");
        }
        this.salario = salario;
    }

    public BigDecimal calcularBonus() {
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return String.format("%s | Salário: R$ %,.2f | Bônus: R$ %,.2f",
                nome, salario, calcularBonus());
    }
}