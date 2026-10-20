
public abstract class Producto {
    protected final String id;
    protected final String nombre;
    protected final double precio;

    public Producto(String id, String nombre, double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("Precio negativo");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " ($" + precio + ")";
    }
}

