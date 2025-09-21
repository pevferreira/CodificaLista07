import java.math.BigDecimal;

public class Pix extends FormaPagamento {
    private String chavePix;
    private String tipoChave; // CPF, email, telefone, chave aleatória

    public Pix(String chavePix, String tipoChave) {
        super("PIX");
        this.chavePix = chavePix;
        this.tipoChave = tipoChave;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        if (chavePix == null || chavePix.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Chave PIX é obrigatória");
        }

        if (tipoChave == null || !isTipoChaveValido(tipoChave)) {
            throw new PagamentoInvalidoException("Tipo de chave PIX inválido. Use: CPF, email, telefone ou aleatoria");
        }

        // Validações específicas por tipo de chave
        switch (tipoChave.toLowerCase()) {
            case "cpf":
                if (!chavePix.matches("\\d{11}")) {
                    throw new PagamentoInvalidoException("CPF deve conter 11 dígitos");
                }
                break;
            case "email":
                if (!chavePix.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                    throw new PagamentoInvalidoException("Email inválido");
                }
                break;
            case "telefone":
                if (!chavePix.matches("^\\+?[\\d\\s-]{10,15}$")) {
                    throw new PagamentoInvalidoException("Telefone inválido");
                }
                break;
            case "aleatoria":
                if (chavePix.length() < 20) {
                    throw new PagamentoInvalidoException("Chave aleatória deve ter pelo menos 20 caracteres");
                }
                break;
        }

        System.out.println("✅ Validação do PIX aprovada");
    }

    @Override
    public void processarPagamento(BigDecimal valor) throws PagamentoInvalidoException {
        validarPagamento();
        validarValor(valor);

        System.out.printf("📱 Processando pagamento de R$ %,.2f via PIX\n", valor);
        System.out.printf("   Chave: %s (%s)\n", mascararChavePix(), tipoChave);
        System.out.println("✅ Pagamento PIX processado instantaneamente!");
    }

    private boolean isTipoChaveValido(String tipo) {
        return tipo != null &&
                (tipo.equalsIgnoreCase("cpf") ||
                        tipo.equalsIgnoreCase("email") ||
                        tipo.equalsIgnoreCase("telefone") ||
                        tipo.equalsIgnoreCase("aleatoria"));
    }

    private String mascararChavePix() {
        if (chavePix.length() <= 4)
            return chavePix;

        switch (tipoChave.toLowerCase()) {
            case "cpf":
                return "***." + chavePix.substring(3, 6) + ".***-**";
            case "email":
                int atIndex = chavePix.indexOf('@');
                if (atIndex > 0) {
                    return chavePix.substring(0, 2) + "***" + chavePix.substring(atIndex);
                }
                break;
            case "telefone":
                return "+** " + chavePix.substring(chavePix.length() - 4);
        }

        return "****" + chavePix.substring(chavePix.length() - 4);
    }
}