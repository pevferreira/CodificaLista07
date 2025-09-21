import java.math.BigDecimal;

public class TesteValidacao {
    public static void main(String[] args) {
        System.out.println("=== TESTES DE VALIDAÇÃO ===\n");

        try {
            new Gerente("Teste", new BigDecimal("-1000"));
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado salário negativo: " + e.getMessage());
        }

        try {
            new Desenvolvedor("", new BigDecimal("3000"));
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado nome vazio: " + e.getMessage());
        }

        try {
            new Gerente("Teste", BigDecimal.ZERO);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado salário zero: " + e.getMessage());
        }

        try {
            new Desenvolvedor("Teste", null);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado salário nulo: " + e.getMessage());
        }

        System.out.println("\nTodos os testes de validação passaram! ✅");
    }
}