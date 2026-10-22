import java.util.*;

public class Repositorio<T> {

    private final Map<String, T> datos = new HashMap<>();

    // Guardar un elemento por identificador
    public void guardar(String id, T elemento) {

        if (!datos.containsKey(id)) {
            datos.put(id, elemento);
        }
    }

    // Buscar un elemento por identificador
    public Optional<T> buscar(String id) {

        return Optional.ofNullable(datos.get(id));
    }

    // Obtener todos los elementos
    public Collection<T> listar() {

        return datos.values();
    }

    // Obtener cantidad de elementos almacenados
    public int cantidad() {

        return datos.size();
    }
}


