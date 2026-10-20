import java.util.*;

public class Repositorio<T> {
    private final Map<String, T> datos = new HashMap<>();

    public void guardar(String id, T elemento) {
        datos.put(id, elemento);
    }

    public Optional<T> buscar(String id) {
        return Optional.ofNullable(datos.get(id));
    }

    public Collection<T> listar() {
        return datos.values();
    }

    public int cantidad() {
        return datos.size();
    }
}

