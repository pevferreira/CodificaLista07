public class Bicicleta implements IMeioTransporte {
    private int velocidadeAtual;
    private static final int VELOCIDADE_MAXIMA = 40;

    public Bicicleta() {
        this.velocidadeAtual = 0;
    }

    @Override
    public void acelerar(int incremento) {
        if (incremento <= 0) {
            throw new IllegalArgumentException("Incremento deve ser positivo");
        }

        if (incremento > 10) {
            throw new IllegalStateException("Bicicleta não pode acelerar mais que 10 km/h de uma vez");
        }

        int novaVelocidade = velocidadeAtual + incremento;
        if (novaVelocidade > VELOCIDADE_MAXIMA) {
            throw new IllegalStateException(
                    String.format("Bicicleta não pode acelerar além de %d km/h", VELOCIDADE_MAXIMA));
        }

        velocidadeAtual = novaVelocidade;
        System.out.printf("🚴 Bicicleta acelerou para %d km/h (+%d km/h)\n", velocidadeAtual, incremento);
    }

    @Override
    public void frear(int decremento) {
        if (decremento <= 0) {
            throw new IllegalArgumentException("Decremento deve ser positivo");
        }

        if (decremento > 15) {
            throw new IllegalStateException("Bicicleta não pode frear mais que 15 km/h de uma vez");
        }

        int novaVelocidade = velocidadeAtual - decremento;
        if (novaVelocidade < 0) {
            velocidadeAtual = 0;
            System.out.println("🚴 Bicicleta parou completamente");
        } else {
            velocidadeAtual = novaVelocidade;
            System.out.printf("🚴 Bicicleta reduziu para %d km/h (-%d km/h)\n", velocidadeAtual, decremento);
        }
    }

    @Override
    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    @Override
    public String getTipo() {
        return "Bicicleta";
    }

    @Override
    public String toString() {
        return String.format("🚴 Bicicleta | Velocidade: %d km/h | Limite: %d km/h",
                velocidadeAtual, VELOCIDADE_MAXIMA);
    }
}