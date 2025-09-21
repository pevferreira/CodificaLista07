public class Trem implements IMeioTransporte {
    private int velocidadeAtual;
    private static final int VELOCIDADE_MAXIMA = 120;
    private static final int VELOCIDADE_MINIMA = 20;

    public Trem() {
        this.velocidadeAtual = 0;
    }

    @Override
    public void acelerar(int incremento) {
        if (incremento <= 0) {
            throw new IllegalArgumentException("Incremento deve ser positivo");
        }

        if (incremento > 30) {
            throw new IllegalStateException("Trem não pode acelerar mais que 30 km/h de uma vez");
        }

        int novaVelocidade = velocidadeAtual + incremento;
        if (novaVelocidade > VELOCIDADE_MAXIMA) {
            throw new IllegalStateException(
                    String.format("Trem não pode acelerar além de %d km/h", VELOCIDADE_MAXIMA));
        }

        if (novaVelocidade > 0 && novaVelocidade < VELOCIDADE_MINIMA) {
            throw new IllegalStateException(
                    String.format("Trem não pode andar entre 1 e %d km/h", VELOCIDADE_MINIMA - 1));
        }

        velocidadeAtual = novaVelocidade;
        System.out.printf("🚆 Trem acelerou para %d km/h (+%d km/h)\n", velocidadeAtual, incremento);
    }

    @Override
    public void frear(int decremento) {
        if (decremento <= 0) {
            throw new IllegalArgumentException("Decremento deve ser positivo");
        }

        if (decremento > 25) {
            throw new IllegalStateException("Trem não pode frear mais que 25 km/h de uma vez");
        }

        int novaVelocidade = velocidadeAtual - decremento;

        if (novaVelocidade < 0) {
            velocidadeAtual = 0;
            System.out.println("🚆 Trem parou completamente");
        } else if (novaVelocidade > 0 && novaVelocidade < VELOCIDADE_MINIMA) {
            throw new IllegalStateException(
                    String.format("Trem não pode andar entre 1 e %d km/h", VELOCIDADE_MINIMA - 1));
        } else {
            velocidadeAtual = novaVelocidade;
            System.out.printf("🚆 Trem reduziu para %d km/h (-%d km/h)\n", velocidadeAtual, decremento);
        }
    }

    @Override
    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    @Override
    public String getTipo() {
        return "Trem";
    }

    @Override
    public String toString() {
        return String.format("🚆 Trem | Velocidade: %d km/h | Limite: %d km/h | Mínima: %d km/h",
                velocidadeAtual, VELOCIDADE_MAXIMA, VELOCIDADE_MINIMA);
    }
}