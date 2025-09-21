public class Carro implements IMeioTransporte {
    private int velocidadeAtual;
    private static final int VELOCIDADE_MAXIMA = 200;

    public Carro() {
        this.velocidadeAtual = 0;
    }

    @Override
    public void acelerar(int incremento) {
        if (incremento <= 0) {
            throw new IllegalArgumentException("Incremento deve ser positivo");
        }

        int novaVelocidade = velocidadeAtual + incremento;
        if (novaVelocidade > VELOCIDADE_MAXIMA) {
            throw new IllegalStateException(
                    String.format("Carro não pode acelerar além de %d km/h", VELOCIDADE_MAXIMA));
        }

        velocidadeAtual = novaVelocidade;
        System.out.printf("Carro acelerou para %d km/h (+%d km/h)\n", velocidadeAtual, incremento);
    }

    @Override
    public void frear(int decremento) {
        if (decremento <= 0) {
            throw new IllegalArgumentException("Decremento deve ser positivo");
        }

        int novaVelocidade = velocidadeAtual - decremento;
        if (novaVelocidade < 0) {
            throw new IllegalStateException("Carro não pode ter velocidade negativa");
        }

        velocidadeAtual = novaVelocidade;
        System.out.printf("Carro reduziu para %d km/h (-%d km/h)\n", velocidadeAtual, decremento);
    }

    @Override
    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }

    @Override
    public String toString() {
        return String.format("Carro | Velocidade: %d km/h | Limite: %d km/h",
                velocidadeAtual, VELOCIDADE_MAXIMA);
    }
}