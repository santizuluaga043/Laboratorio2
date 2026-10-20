import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Consultas {

    private Consultas() {
    }

    // Wildcard: recibe platos, bebidas, combos u otros productos.
    public static double sumarPrecios(
            List<? extends Producto> productos) {

        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    // Ordenar por precio descendente y luego nombre.
    public static List<Producto> menuOrdenado(
            Collection<? extends Producto> menu) {

        return menu.stream()
                .sorted(
                        Comparator.comparingDouble(Producto::getPrecio)
                                .reversed()
                                .thenComparing(Producto::getNombre)
                )
                .collect(Collectors.toList());
    }

    // Ordenar repartidores por calificacion y entregas.
    public static List<Repartidor> repartidoresOrdenados(
            Collection<Repartidor> repartidores) {

        return repartidores.stream()
                .sorted(
                        Comparator.comparingDouble(
                                        Repartidor::getCalificacion)
                                .reversed()
                                .thenComparing(
                                        Comparator.comparingInt(
                                                        Repartidor::getEntregas)
                                                .reversed()
                                )
                )
                .collect(Collectors.toList());
    }

    // Sumar la facturacion de cada sede.
    public static Map<String, Double> facturacionPorSede(
            Collection<Pedido> pedidos) {

        return pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getSede,
                        Collectors.summingDouble(Pedido::total)
                ));
    }

    // Buscar el pedido mas caro de un cliente.
    public static Optional<Pedido> pedidoMasCaroDeCliente(
            Collection<Pedido> pedidos, String documento) {

        return pedidos.stream()
                .filter(p -> p.getCliente()
                        .getDocumento().equals(documento))
                .max(Comparator.comparingDouble(Pedido::total));
    }

    // Obtener los 3 platos mas vendidos en un periodo.
    public static List<Map.Entry<Plato, Integer>>
    tresPlatosMasVendidos(Collection<Pedido> pedidos,
                          LocalDate inicio, LocalDate fin) {

        Map<Plato, Integer> ventas = new HashMap<>();

        pedidos.stream()
                .filter(p -> !p.getFecha().isBefore(inicio)
                        && !p.getFecha().isAfter(fin))
                .forEach(p ->
                        p.getCantidades().forEach((producto, cantidad) -> {
                            if (producto instanceof Plato) {
                                ventas.merge(
                                        (Plato) producto,
                                        cantidad,
                                        Integer::sum
                                );
                            }
                        })
                );

        return ventas.entrySet().stream()
                .sorted(
                        Map.Entry.<Plato, Integer>comparingByValue()
                                .reversed()
                )
                .limit(3)
                .collect(Collectors.toList());
    }
}

