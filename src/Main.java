import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Repositorios genericos
        Repositorio<Cliente> clientes = new Repositorio<>();
        Repositorio<Producto> menu = new Repositorio<>();
        Repositorio<Repartidor> repartidores = new Repositorio<>();
        Repositorio<Pedido> pedidos = new Repositorio<>();

        // Clientes sin duplicados
        Set<Cliente> clientesUnicos = new HashSet<>();

        Cliente ana = new Cliente("1001", "Ana");
        Cliente anaWhatsApp = new Cliente("1001", "Ana WhatsApp");
        Cliente pedro = new Cliente("1002", "Pedro");

        clientesUnicos.add(ana);
        clientesUnicos.add(anaWhatsApp);
        clientesUnicos.add(pedro);

        clientes.guardar(ana.getDocumento(), ana);
        clientes.guardar(anaWhatsApp.getDocumento(), anaWhatsApp);
        clientes.guardar(pedro.getDocumento(), pedro);

        System.out.println("Clientes en repositorio: " + clientes.cantidad());

        System.out.println("Clientes unicos: "
                + clientesUnicos.size());

        System.out.println("Buscar cliente: "
                + clientes.buscar("1001")
                .map(Cliente::getNombre)
                .orElse("No existe"));

        // Productos
        Producto hamburguesa =
                new Plato("P1", "Hamburguesa", 25000);

        Producto pizza =
                new Plato("P2", "Pizza", 30000);

        Producto ensalada =
                new Plato("P3", "Ensalada", 18000);

        Producto gaseosa =
                new Bebida("B1", "Gaseosa", 6000);

        Producto combo = new Combo(
                "C1", "Combo hamburguesa", 29000,
                Arrays.asList(hamburguesa, gaseosa)
        );

        for (Producto producto : Arrays.asList(
                hamburguesa, pizza, ensalada, gaseosa, combo)) {
            menu.guardar(producto.getId(), producto);
        }

        // Repartidores
        Repartidor r1 =
                new Repartidor("R1", "Carlos", 4.9, 120);

        Repartidor r2 =
                new Repartidor("R2", "Luis", 4.9, 90);

        Repartidor r3 =
                new Repartidor("R3", "Marta", 4.7, 150);

        for (Repartidor r : Arrays.asList(r1, r2, r3)) {
            repartidores.guardar(r.getId(), r);
        }

        // Pedidos
        LocalDate hoy = LocalDate.now();

        Pedido p1 = new Pedido("O1", ana, "Centro", hoy);
        p1.agregarProducto(hamburguesa, 3);
        p1.agregarProducto(gaseosa, 2);
        p1.setPromocion(new DescuentoPorcentaje(10));
        p1.suscribir(new Notificacion("Cocina"));
        p1.suscribir(new Notificacion("Cliente Ana"));
        p1.suscribir(new Notificacion("Repartidor Carlos"));

        p1.cambiarEstado("EN PREPARACION");

        Pedido p2 = new Pedido("O2", ana, "Norte", hoy);
        p2.agregarProducto(pizza, 4);
        p2.agregarProducto(hamburguesa, 1);

        Pedido p3 = new Pedido("O3", pedro, "Centro", hoy);
        p3.agregarProducto(ensalada, 2);
        p3.agregarProducto(pizza, 1);

        for (Pedido pedido : Arrays.asList(p1, p2, p3)) {
            pedidos.guardar(pedido.getId(), pedido);
        }

        // Menu ordenado
        System.out.println("\nMENU ORDENADO:");
        Consultas.menuOrdenado(menu.listar())
                .forEach(System.out::println);

        // Repartidores ordenados
        System.out.println("\nREPARTIDORES ORDENADOS:");
        Consultas.repartidoresOrdenados(repartidores.listar())
                .forEach(System.out::println);

        // Facturacion por sede
        System.out.println("\nFACTURACION POR SEDE:");
        System.out.println(
                Consultas.facturacionPorSede(pedidos.listar())
        );

        // Pedido mas caro de Ana
        System.out.println("\nPEDIDO MAS CARO DE ANA:");
        System.out.println(
                Consultas.pedidoMasCaroDeCliente(
                                pedidos.listar(), "1001")
                        .map(Pedido::getId)
                        .orElse("Ninguno")
        );

        // Tres platos mas vendidos
        System.out.println("\nTOP 3 PLATOS:");
        System.out.println(
                Consultas.tresPlatosMasVendidos(
                        pedidos.listar(), hoy.minusDays(6), hoy)
        );

        // Wildcard
        System.out.println("\nSUMA DE PRECIOS:");
        System.out.println(
                Consultas.sumarPrecios(
                        Arrays.asList(
                                (Plato) hamburguesa,
                                (Plato) pizza
                        )
                )
        );

        // Total con descuento
        System.out.println("\nTOTAL PEDIDO O1:");
        System.out.println(p1.total());

        // Busqueda inexistente
        System.out.println("\nPEDIDO NO ENCONTRADO:");
        System.out.println(
                pedidos.buscar("O999")
                        .map(Pedido::getId)
                        .orElse("No existe")
        );
    }
}


