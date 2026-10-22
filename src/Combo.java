import java.util.List;
import java.util.ArrayList;

public class Combo extends Producto {

    private final List<Producto> componentes;

    public Combo(String id, String nombre, double precio,
                 List<Producto> componentes) {

        super(id, nombre, precio);

        this.componentes = new ArrayList<>(componentes);
    }

    public List<Producto> getComponentes() {
        return new ArrayList<>(componentes);
    }
}


