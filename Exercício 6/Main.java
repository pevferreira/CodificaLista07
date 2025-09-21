import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CARRINHO DE COMPRAS IMUTÁVEL ===\n");

        Produto notebook = new Produto("P001", "Notebook Dell",
                new Dinheiro(3500.00, Moeda.BRL));
        Produto mouse = new Produto("P002", "Mouse Logitech",
                new Dinheiro(150.00, Moeda.BRL));
        Produto teclado = new Produto("P003", "Teclado Mecânico",
                new Dinheiro(300.00, Moeda.BRL));
        Produto monitor = new Produto("P004", "Monitor 24\"",
                new Dinheiro(900.00, Moeda.BRL));

        System.out.println("=== PRODUTOS DISPONÍVEIS ===");
        System.out.println(notebook);
        System.out.println(mouse);
        System.out.println(teclado);
        System.out.println(monitor);
        System.out.println();

        System.out.println("=== FLUXO DO CARRINHO ===\n");

        Carrinho carrinho = new Carrinho();
        System.out.println("1. Carrinho criado (vazio):");
        System.out.println(carrinho);
        System.out.println();

        carrinho = carrinho.adicionarItem(notebook, 1);
        carrinho = carrinho.adicionarItem(mouse, 2);
        System.out.println("2. Adicionados notebook e 2 mouses:");
        System.out.println(carrinho);
        System.out.println();

        carrinho = carrinho.alterarQuantidade(mouse, 3);
        System.out.println("3. Alterada quantidade do mouse para 3:");
        System.out.println(carrinho);
        System.out.println();

        carrinho = carrinho.adicionarItem(teclado, 1);
        carrinho = carrinho.adicionarItem(monitor, 1);
        System.out.println("4. Adicionados teclado e monitor:");
        System.out.println(carrinho);
        System.out.println();

        carrinho = carrinho.aplicarCupom(15.0);
        System.out.println("5. Aplicado cupom de 15%:");
        System.out.println(carrinho);
        System.out.println();

        try {
            carrinho = carrinho.aplicarCupom(35.0);
        } catch (IllegalArgumentException e) {
            System.out.println("6. Tentativa de desconto inválido (35%):");
            System.out.println("❌ " + e.getMessage());
            System.out.println();
        }

        carrinho = carrinho.removerItem(teclado);
        System.out.println("7. Removido teclado:");
        System.out.println(carrinho);
        System.out.println();

        carrinho = carrinho.removerCupom();
        System.out.println("8. Cupom removido:");
        System.out.println(carrinho);
        System.out.println();

        System.out.println("=== TESTES DE VALIDAÇÃO ===\n");

        try {
            new ItemCarrinho(notebook, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturada quantidade zero: " + e.getMessage());
        }

        try {
            new ItemCarrinho(notebook, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturada quantidade negativa: " + e.getMessage());
        }

        try {
            new Dinheiro(-100.00, Moeda.BRL);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado valor negativo: " + e.getMessage());
        }

        try {
            new Carrinho(Collections.emptyList(), -5.0);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado desconto negativo: " + e.getMessage());
        }

        try {
            new Carrinho(Collections.emptyList(), 35.0);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Capturado desconto acima de 30%: " + e.getMessage());
        }

        System.out.println("\n=== DEMONSTRAÇÃO DE IMUTABILIDADE ===\n");

        Carrinho carrinhoOriginal = new Carrinho();
        Carrinho carrinhoModificado = carrinhoOriginal.adicionarItem(notebook, 1);

        System.out.println("Carrinho original (deve estar vazio):");
        System.out.println("Itens: " + carrinhoOriginal.getItens().size());
        System.out.println("Carrinho modificado (deve ter 1 item):");
        System.out.println("Itens: " + carrinhoModificado.getItens().size());
        System.out.println("✅ Objetos são independentes (imutabilidade preservada)");
    }
}