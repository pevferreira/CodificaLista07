import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE POLIMORFISMO - MEIOS DE TRANSPORTE ===\n");

        List<IMeioTransporte> transportes = new ArrayList<>();
        transportes.add(new Carro());
        transportes.add(new Bicicleta());
        transportes.add(new Trem());
        transportes.add(new Carro());
        transportes.add(new Bicicleta());

        // Demonstração de polimorfismo
        System.out.println("=== ACELERANDO TODOS OS TRANSPORTES ===\n");

        for (IMeioTransporte transporte : transportes) {
            System.out.println("--- " + transporte.getTipo() + " ---");

            try {
                // Cada transporte acelera de forma diferente
                if (transporte instanceof Carro) {
                    transporte.acelerar(50);
                    transporte.acelerar(80);
                } else if (transporte instanceof Bicicleta) {
                    transporte.acelerar(5);
                    transporte.acelerar(8);
                } else if (transporte instanceof Trem) {
                    transporte.acelerar(40);
                    transporte.acelerar(30);
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }

        // Demonstração de frenagem
        System.out.println("=== FREANDO TODOS OS TRANSPORTES ===\n");

        for (IMeioTransporte transporte : transportes) {
            System.out.println("--- " + transporte.getTipo() + " ---");

            try {
                // Cada transporte freia de forma diferente
                if (transporte instanceof Carro) {
                    transporte.frear(30);
                    transporte.frear(50);
                } else if (transporte instanceof Bicicleta) {
                    transporte.frear(5);
                    transporte.frear(10);
                } else if (transporte instanceof Trem) {
                    transporte.frear(20);
                    transporte.frear(40);
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        }

        // Testes de operações inválidas
        System.out.println("=== TESTES DE OPERAÇÕES INVÁLIDAS ===\n");

        IMeioTransporte[] testes = {
                new Carro(), new Bicicleta(), new Trem()
        };

        for (IMeioTransporte transporte : testes) {
            System.out.println("--- Testando " + transporte.getTipo() + " ---");

            // Teste 1: Acelerar com valor negativo
            try {
                transporte.acelerar(-10);
            } catch (IllegalArgumentException e) {
                System.out.println("✅ Capturado: " + e.getMessage());
            }

            // Teste 2: Frear com valor negativo
            try {
                transporte.frear(-5);
            } catch (IllegalArgumentException e) {
                System.out.println("✅ Capturado: " + e.getMessage());
            }

            // Teste 3: Exceder limites
            try {
                if (transporte instanceof Carro) {
                    transporte.acelerar(300);
                } else if (transporte instanceof Bicicleta) {
                    transporte.acelerar(50);
                } else if (transporte instanceof Trem) {
                    transporte.acelerar(150);
                }
            } catch (IllegalStateException e) {
                System.out.println("✅ Capturado: " + e.getMessage());
            }

            System.out.println();
        }

        // Status final de todos os transportes
        System.out.println("=== STATUS FINAL ===\n");
        for (IMeioTransporte transporte : transportes) {
            System.out.println(transporte);
        }
    }
}