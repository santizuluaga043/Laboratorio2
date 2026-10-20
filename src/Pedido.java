import java.time.LocalDate;
import java.util.*;

public class Pedido {
    private final String id;
    private final Cliente cliente;
    private final String sede;
    private final LocalDate fecha;

    private final Map<Producto, Integer> cantidades =
            new LinkedHashMap<>();

    private String estado = "RECIBIDO";
    private double descuento = 0;

    public Pedido(String id, Cliente cliente,
                  String sede, LocalDate fecha) {
        this.id = id;
        this.cliente = cliente;
        this.sede = sede;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getSede() {
        return sede;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Map<Producto, Integer> getCantidades() {
        return Collections.unmodifiableMap(cantidades);
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad invalida");
        }

        cantidades.merge(producto, cantidad, Integer::sum);
    }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("Descuento invalido");
        }

        descuento = porcentaje;
    }

    public double subtotal() {
        return cantidades.entrySet().stream()
                .mapToDouble(e ->
                        e.getKey().getPrecio() * e.getValue())
                .sum();
    }

    public double total() {
        return subtotal() * (1 - descuento / 100);
    }

    public void cambiarEstado(String nuevoEstado) {
        estado = nuevoEstado;
        System.out.println("Pedido " + id
                + " cambio a: " + estado);
    }
}


