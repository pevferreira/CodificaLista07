import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE REPOSITÓRIO GENÉRICO ===\n");

        // Teste com Produtos (ID String)
        System.out.println("=== TESTE COM PRODUTOS (String ID) ===\n");

        IRepository<Produto, String> produtoRepo = new InMemoryRepository<>();

        // Criando produtos
        Produto notebook = new Produto("P001", "Notebook Dell", new BigDecimal("3500.00"));
        Produto mouse = new Produto("P002", "Mouse Logitech", new BigDecimal("150.00"));
        Produto teclado = new Produto("P003", "Teclado Mecânico", new BigDecimal("300.00"));

        // Salvando produtos
        produtoRepo.salvar(notebook);
        produtoRepo.salvar(mouse);
        produtoRepo.salvar(teclado);

        System.out.println("Produtos salvos: " + produtoRepo.contar());

        // Buscando por ID
        Optional<Produto> produtoEncontrado = produtoRepo.buscarPorId("P002");
        produtoEncontrado.ifPresent(p -> System.out.println("Produto encontrado: " + p));

        // Listando todos
        List<Produto> todosProdutos = produtoRepo.listarTodos();
        System.out.println("\nTodos os produtos:");
        todosProdutos.forEach(System.out::println);

        // Testando existência
        System.out.println("\nExiste P001? " + produtoRepo.existe("P001"));
        System.out.println("Existe P999? " + produtoRepo.existe("P999"));

        // Removendo
        try {
            produtoRepo.remover("P002");
            System.out.println("\nMouse removido com sucesso");
            System.out.println("Produtos restantes: " + produtoRepo.contar());
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }

        // Tentativa de remover ID inexistente
        try {
            produtoRepo.remover("P999");
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("\n✅ Capturada exceção ao remover ID inexistente: " + e.getMessage());
        }

        // Teste com Funcionários (ID Long)
        System.out.println("\n\n=== TESTE COM FUNCIONÁRIOS (Long ID) ===\n");

        IRepository<Funcionario, Long> funcionarioRepo = new InMemoryRepository<>();

        // Criando funcionários
        Funcionario joao = new Funcionario(1L, "João Silva", "Desenvolvedor",
                new BigDecimal("5000.00"), LocalDate.of(2020, 1, 15));
        Funcionario maria = new Funcionario(2L, "Maria Santos", "Gerente",
                new BigDecimal("8000.00"), LocalDate.of(2018, 5, 20));
        Funcionario pedro = new Funcionario(3L, "Pedro Costa", "Analista",
                new BigDecimal("4000.00"), LocalDate.of(2021, 3, 10));

        // Salvando funcionários
        funcionarioRepo.salvar(joao);
        funcionarioRepo.salvar(maria);
        funcionarioRepo.salvar(pedro);

        System.out.println("Funcionários salvos: " + funcionarioRepo.contar());

        // Buscando por ID
        Optional<Funcionario> funcionarioEncontrado = funcionarioRepo.buscarPorId(2L);
        funcionarioEncontrado.ifPresent(f -> System.out.println("Funcionário encontrado: " + f));

        // Listando todos
        List<Funcionario> todosFuncionarios = funcionarioRepo.listarTodos();
        System.out.println("\nTodos os funcionários:");
        todosFuncionarios.forEach(System.out::println);

        // Testando imutabilidade da lista
        System.out.println("\n=== TESTE DE IMUTABILIDADE ===");
        List<Funcionario> lista = funcionarioRepo.listarTodos();
        try {
            lista.add(new Funcionario(4L, "Teste", "Cargo",
                    new BigDecimal("1000.00"), LocalDate.now()));
            System.out.println("Lista não é imutável!");
        } catch (UnsupportedOperationException e) {
            System.out.println("✅ Lista é imutável (como esperado)");
        }

        // Testando métodos adicionais
        InMemoryRepository<Funcionario, Long> repoAvancado = (InMemoryRepository<Funcionario, Long>) funcionarioRepo;

        System.out.println("\nBuscar múltiplos IDs:");
        List<Funcionario> funcionariosSelecionados = repoAvancado.buscarTodosPorIds(List.of(1L, 3L));
        funcionariosSelecionados.forEach(System.out::println);

        // Limpando repositório
        repoAvancado.limpar();
        System.out.println("\nRepositório limpo. Total: " + repoAvancado.contar());

        System.out.println("\n=== TODOS OS TESTES CONCLUÍDOS ===");
    }
}