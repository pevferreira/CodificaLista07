import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE BÔNUS DE FUNCIONÁRIOS ===\n");

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            funcionarios.add(new Gerente("Carlos Silva", new BigDecimal("10000.00")));
            funcionarios.add(new Desenvolvedor("Ana Santos", new BigDecimal("5000.00")));
            funcionarios.add(new Gerente("Maria Oliveira", new BigDecimal("12000.00")));
            funcionarios.add(new Desenvolvedor("João Pereira", new BigDecimal("4500.00")));
            funcionarios.add(new Desenvolvedor("Paula Costa", new BigDecimal("6000.00")));

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro ao criar funcionário: " + e.getMessage());
        }

        System.out.println("BÔNUS DOS FUNCIONÁRIOS:\n");

        for (Funcionario func : funcionarios) {
            System.out.println(func);
        }

        System.out.println("\n=== ESTATÍSTICAS ===");

        BigDecimal totalBonus = BigDecimal.ZERO;
        BigDecimal totalSalarios = BigDecimal.ZERO;

        for (Funcionario func : funcionarios) {
            totalBonus = totalBonus.add(func.calcularBonus());
            totalSalarios = totalSalarios.add(func.getSalario());
        }

        System.out.printf("Total em salários: R$ %,.2f\n", totalSalarios);
        System.out.printf("Total em bônus: R$ %,.2f\n", totalBonus);
        System.out.printf("Total geral: R$ %,.2f\n", totalSalarios.add(totalBonus));

        System.out.println("\n=== DEMONSTRAÇÃO DE POLIMORFISMO ===");

        for (Funcionario func : funcionarios) {
            String tipo = func instanceof Gerente ? "Gerente" : "Desenvolvedor";
            BigDecimal percentual = func.calcularBonus()
                    .divide(func.getSalario(), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"));

            System.out.printf("%s (%s): %.0f%% de bônus\n",
                    func.getNome(), tipo, percentual);
        }
    }
}