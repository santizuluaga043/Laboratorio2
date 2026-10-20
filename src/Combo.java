import java.util.List;

public class Combo extends Producto {
    private final List<Producto> componentes;

    public Combo(String id, String nombre, double precio,
                 List<Producto> componentes) {
        super(id, nombre, precio);
        this.componentes = List.copyOf(componentes);
    }

    public List<Producto> getComponentes() {
        return componentes;
    }
}

