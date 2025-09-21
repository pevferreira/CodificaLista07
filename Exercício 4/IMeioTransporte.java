public interface IMeioTransporte {
    void acelerar(int incremento);

    void frear(int decremento);

    int getVelocidadeAtual();

    String getTipo();
}