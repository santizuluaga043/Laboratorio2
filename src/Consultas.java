import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Consultas {

    private Consultas() {
    }

    // Sumar precios de cualquier tipo de producto
    public static double sumarPrecios(
            List<? extends Producto> productos) {

        return productos.stream()
                .map(Producto::getPrecio)
                .reduce(0.0, (a, b) -> a + b);
    }

    // Ordenar menu por precio descendente y luego nombre
    public static List<Producto> menuOrdenado(
            Collection<? extends Producto> menu) {

        List<Producto> lista = new ArrayList<>(menu);

        Collections.sort(lista, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {

                int resultado = Double.compare(
                        p2.getPrecio(), p1.getPrecio());

                if (resultado == 0) {
                    return p1.getNombre().compareTo(p2.getNombre());
                }

                return resultado;
            }
        });

        return lista;
    }

    // Ordenar repartidores por calificacion y entregas
    public static List<Repartidor> repartidoresOrdenados(
            Collection<Repartidor> repartidores) {

        List<Repartidor> lista = new ArrayList<>(repartidores);

        Collections.sort(lista, new Comparator<Repartidor>() {
            @Override
            public int compare(Repartidor r1, Repartidor r2) {

                int resultado = Double.compare(
                        r2.getCalificacion(), r1.getCalificacion());

                if (resultado == 0) {
                    return Integer.compare(
                            r2.getEntregas(), r1.getEntregas());
                }

                return resultado;
            }
        });

        return lista;
    }

    // Calcular facturacion por sede
    public static Map<String, Double> facturacionPorSede(
            Collection<Pedido> pedidos) {

        Map<String, Double> facturacion = new HashMap<>();

        for (Pedido pedido : pedidos) {

            String sede = pedido.getSede();
            double total = pedido.total();

            if (facturacion.containsKey(sede)) {

                double anterior = facturacion.get(sede);
                facturacion.put(sede, anterior + total);

            } else {

                facturacion.put(sede, total);
            }
        }

        return facturacion;
    }

    // Encontrar el pedido mas caro de un cliente
    public static Optional<Pedido> pedidoMasCaroDeCliente(
            Collection<Pedido> pedidos, String documento) {

        return pedidos.stream()
                .filter(p -> p.getCliente()
                        .getDocumento().equals(documento))
                .max(new Comparator<Pedido>() {
                    @Override
                    public int compare(Pedido p1, Pedido p2) {

                        return Double.compare(
                                p1.total(), p2.total());
                    }
                });
    }

    // Obtener los 3 platos mas vendidos de la semana
    public static List<Map.Entry<Plato, Integer>>
    tresPlatosMasVendidos(Collection<Pedido> pedidos,
                          LocalDate inicio, LocalDate fin) {

        Map<Plato, Integer> ventas = new HashMap<>();

        pedidos.stream()
                .filter(p -> !p.getFecha().isBefore(inicio)
                        && !p.getFecha().isAfter(fin))
                .forEach(p -> {

                    for (Map.Entry<Producto, Integer> entrada
                            : p.getCantidades().entrySet()) {

                        Producto producto = entrada.getKey();
                        int cantidad = entrada.getValue();

                        // Platos vendidos directamente
                        if (producto instanceof Plato) {

                            Plato plato = (Plato) producto;

                            if (ventas.containsKey(plato)) {

                                int anterior = ventas.get(plato);
                                ventas.put(plato, anterior + cantidad);

                            } else {

                                ventas.put(plato, cantidad);
                            }
                        }

                        // Platos incluidos en combos
                        if (producto instanceof Combo) {

                            Combo combo = (Combo) producto;

                            for (Producto componente
                                    : combo.getComponentes()) {

                                if (componente instanceof Plato) {

                                    Plato plato = (Plato) componente;

                                    if (ventas.containsKey(plato)) {

                                        int anterior = ventas.get(plato);
                                        ventas.put(plato,
                                                anterior + cantidad);

                                    } else {

                                        ventas.put(plato, cantidad);
                                    }
                                }
                            }
                        }
                    }
                });

        // Ordenar platos por cantidad vendida
        List<Map.Entry<Plato, Integer>> lista =
                new ArrayList<>(ventas.entrySet());

        Collections.sort(lista,
                new Comparator<Map.Entry<Plato, Integer>>() {
                    @Override
                    public int compare(
                            Map.Entry<Plato, Integer> e1,
                            Map.Entry<Plato, Integer> e2) {

                        return Integer.compare(
                                e2.getValue(), e1.getValue());
                    }
                });

        // Devolver los primeros 3
        return lista.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}



