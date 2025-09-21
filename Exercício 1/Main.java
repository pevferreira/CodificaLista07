public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DA CLASSE PRODUTO ===\n");

        System.out.println("1. Criando produto válido:");
        try {
            Produto produto1 = new Produto("Notebook", 2500.00, 10);
            System.out.println(" " + produto1);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n2. Tentativa com nome vazio:");
        try {
            Produto produto2 = new Produto("", 100.00, 5);
            System.out.println(" " + produto2);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n3. Tentativa com preço negativo:");
        try {
            Produto produto3 = new Produto("Mouse", -50.00, 8);
            System.out.println(" " + produto3);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n4. Tentativa com estoque negativo:");
        try {
            Produto produto4 = new Produto("Teclado", 150.00, -3);
            System.out.println(" " + produto4);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n5. Alterações válidas:");
        try {
            Produto produto5 = new Produto("Monitor", 800.00, 15);
            System.out.println("Original: " + produto5);

            produto5.setPreco(750.00);
            produto5.setQuantidadeEmEstoque(12);
            produto5.setNome("Monitor Gamer");

            System.out.println("Alterado: " + produto5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n6. Tentativa de alteração inválida:");
        try {
            Produto produto6 = new Produto("Headset", 300.00, 20);
            System.out.println("Antes: " + produto6);

            produto6.setPreco(-100.00); // Isso deve lançar exceção

            System.out.println("Depois: " + produto6);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao alterar: " + e.getMessage());
        }

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }
}