import java.math.BigDecimal;

public class Gerente extends Funcionario {
    public Gerente(String nome, BigDecimal salario) {
        super(nome, salario);
    }

    @Override
    public BigDecimal calcularBonus() {
        // 20% de bônus para gerentes
        return salario.multiply(new BigDecimal("0.20"));
    }

    @Override
    public String toString() {
        return "[Gerente] " + super.toString();
    }
}