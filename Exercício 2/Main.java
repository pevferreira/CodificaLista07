public class Main {
    public static void main(String[] args) {
        System.out.println("=== TESTES DE APLICAÇÃO DE DESCONTO ===\n");

        Produto notebook = new Produto("Notebook Gamer", 3500.00, 5);
        System.out.println("Produto criado: " + notebook);
        System.out.println();

        System.out.println("1. Aplicando desconto válido (10%):");
        try {
            System.out.println("Preço antes: R$ " + notebook.getPreco());
            notebook.aplicarDesconto(10.0);
            System.out.println("Preço após: R$ " + notebook.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n2. Aplicando desconto válido (25%):");
        try {
            System.out.println("Preço antes: R$ " + notebook.getPreco());
            notebook.aplicarDesconto(25.0);
            System.out.println("Preço após: R$ " + notebook.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n3. Aplicando desconto máximo (50%):");
        try {
            System.out.println("Preço antes: R$ " + notebook.getPreco());
            notebook.aplicarDesconto(50.0);
            System.out.println("Preço após: R$ " + notebook.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n4. Tentativa de desconto negativo (-5%):");
        try {
            System.out.println("Preço antes: R$ " + notebook.getPreco());
            notebook.aplicarDesconto(-5.0);
            System.out.println("Preço após: R$ " + notebook.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n5. Tentativa de desconto acima do limite (60%):");
        try {
            System.out.println("Preço antes: R$ " + notebook.getPreco());
            notebook.aplicarDesconto(60.0);
            System.out.println("Preço após: R$ " + notebook.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n6. Aplicando desconto zero (0%):");
        try {
            System.out.println("Preço antes: R$ " + notebook.getPreco());
            notebook.aplicarDesconto(0.0);
            System.out.println("Preço após: R$ " + notebook.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n7. Múltiplos descontos sequenciais:");
        try {
            Produto mouse = new Produto("Mouse RGB", 200.00, 15);
            System.out.println("Novo produto: " + mouse);

            mouse.aplicarDesconto(15.0);
            mouse.aplicarDesconto(10.0);

            System.out.println("Preço final: R$ " + mouse.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println(" " + e.getMessage());
        }

        System.out.println("\n=== FIM DOS TESTES DE DESCONTO ===");
    }
}