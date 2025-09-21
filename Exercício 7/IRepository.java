import java.util.List;
import java.util.Optional;

public interface IRepository<T extends Identificavel<ID>, ID> {
    T salvar(T entidade);

    Optional<T> buscarPorId(ID id);

    List<T> listarTodos();

    void remover(ID id) throws EntidadeNaoEncontradaException;

    boolean existe(ID id);
}