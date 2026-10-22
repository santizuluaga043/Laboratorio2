import java.time.LocalDate;
import java.util.*;

public class Pedido {

    private final String id;
    private final Cliente cliente;
    private final String sede;
    private final LocalDate fecha;

    private final Map<Producto, Integer> cantidades =
            new LinkedHashMap<>();

    private final List<ObservadorPedido> observadores =
            new ArrayList<>();

    private EstrategiaPromocion promocion = new SinPromocion();

    private String estado = "RECIBIDO";

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
        return new LinkedHashMap<>(cantidades);
    }

    // Agregar productos al pedido
    public void agregarProducto(Producto producto, int cantidad) {

        if (cantidad <= 0) {
            throw new IllegalArgumentException("Cantidad invalida");
        }

        if (cantidades.containsKey(producto)) {

            int cantidadActual = cantidades.get(producto);

            cantidades.put(producto, cantidadActual + cantidad);

        } else {

            cantidades.put(producto, cantidad);
        }
    }

    // Asignar una promocion
    public void setPromocion(EstrategiaPromocion promocion) {

        if (promocion == null) {
            throw new IllegalArgumentException("Promocion invalida");
        }

        this.promocion = promocion;
    }

    // Calcular subtotal
    public double subtotal() {

        return cantidades.entrySet().stream()
                .map(e -> e.getKey().getPrecio() * e.getValue())
                .reduce(0.0, (a, b) -> a + b);
    }

    // Calcular total con promocion
    public double total() {
        return promocion.aplicar(subtotal());
    }

    // Registrar interesados en las notificaciones
    public void suscribir(ObservadorPedido observador) {
        observadores.add(observador);
    }

    // Cambiar estado y notificar
    public void cambiarEstado(String nuevoEstado) {

        estado = nuevoEstado;

        for (ObservadorPedido observador : observadores) {
            observador.actualizar(id, estado);
        }
    }
}


