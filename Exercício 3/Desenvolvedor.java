import java.math.BigDecimal;

public class Desenvolvedor extends Funcionario {
    public Desenvolvedor(String nome, BigDecimal salario) {
        super(nome, salario);
    }

    @Override
    public BigDecimal calcularBonus() {
        // 10% de bônus para desenvolvedores
        return salario.multiply(new BigDecimal("0.10"));
    }

    @Override
    public String toString() {
        return "[Desenvolvedor] " + super.toString();
    }
}