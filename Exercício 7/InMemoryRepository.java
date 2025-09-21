import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T extends Identificavel<ID>, ID> implements IRepository<T, ID> {
    private final Map<ID, T> storage = new ConcurrentHashMap<>();

    @Override
    public T salvar(T entidade) {
        Objects.requireNonNull(entidade, "Entidade não pode ser nula");
        Objects.requireNonNull(entidade.getId(), "ID da entidade não pode ser nulo");

        storage.put(entidade.getId(), entidade);
        return entidade;
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        Objects.requireNonNull(id, "ID não pode ser nulo");
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> listarTodos() {
        // Retorna cópia imutável para proteger o estado interno
        return Collections.unmodifiableList(new ArrayList<>(storage.values()));
    }

    @Override
    public void remover(ID id) throws EntidadeNaoEncontradaException {
        Objects.requireNonNull(id, "ID não pode ser nulo");

        if (!storage.containsKey(id)) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Entidade com ID %s não encontrada", id));
        }

        storage.remove(id);
    }

    @Override
    public boolean existe(ID id) {
        Objects.requireNonNull(id, "ID não pode ser nulo");
        return storage.containsKey(id);
    }

    public int contar() {
        return storage.size();
    }

    public void limpar() {
        storage.clear();
    }

    public List<T> buscarTodosPorIds(Collection<ID> ids) {
        Objects.requireNonNull(ids, "IDs não podem ser nulos");

        List<T> resultado = new ArrayList<>();
        for (ID id : ids) {
            buscarPorId(id).ifPresent(resultado::add);
        }
        return Collections.unmodifiableList(resultado);
    }
}