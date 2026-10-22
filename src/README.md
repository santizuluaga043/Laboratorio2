# Laboratorio 2 
### Santiago Zuluaga

## Funciones implementadas

- Registro de clientes evitando duplicados mediante su número de documento.
- Búsqueda de clientes, productos y pedidos por identificador.
- Administración de platos, bebidas y combos.
- Registro de pedidos con productos y cantidades.
- Ordenamiento del menú por precio descendente y nombre.
- Ordenamiento de repartidores por calificación y número de entregas.
- Consulta de los tres platos más vendidos durante la semana.
- Cálculo de facturación por sede.
- Identificación del pedido más costoso de un cliente.
- Aplicación de promociones sin modificar la clase Pedido.
- Notificaciones automáticas a cocina, cliente y repartidor cuando cambia el estado de un pedido.
- Manejo de búsquedas sin resultados mediante Optional.

Colecciones usadas

- HashMap: se utiliza en Repositorio<T> para almacenar clientes, productos, repartidores y pedidos con identificadores.
- HashSet: se utiliza en Main para demostrar que dos clientes con el mismo documento son considerados iguales, aunque provengan de diferentes canales.
- LinkedHashMap: se utiliza en Pedido para almacenar los productos y sus cantidades.
- ArrayList: se utiliza en Pedido para almacenar los observadores que reciben notificaciones.

En Cliente se sobrescribieron los métodos equals() y hashCode() para comparar clientes según su documento. Además, el repositorio utiliza el documento como clave única para evitar almacenar varios registros con el mismo identificador.

En la clase Consultas se utiliza la interfaz Comparator y el método compare() para ordenar elementos según diferentes criterios. El menú se ordena primero por precio de mayor a menor y, cuando dos productos tienen el mismo precio, por nombre alfabéticamente. Los repartidores se ordenan primero por calificación de mayor a menor y, en caso de empate, por número de entregas de mayor a menor. Para realizar estos ordenamientos se utiliza Collections.sort().

Se implementó la clase genérica Repositorio<T>, que permite almacenar diferentes tipos de objetos sin repetir la misma lógica de almacenamiento y búsqueda. También se utilizan wildcards mediante ? extends Producto en la clase Consultas. Esto permite realizar cálculos y ordenamientos sobre colecciones de productos y sus subtipos, como platos, bebidas y combos.

### 4. Programación funcional

En las clases Consultas y Pedido se utilizan Streams, expresiones lambda y referencias a métodos.

Las operaciones implementadas fueron:

- filter(): filtrar pedidos por cliente o fecha.
- sorted(): ordenar productos y repartidores.
- mapToDouble(): transformar datos numéricos para realizar cálculos. 
- forEach(): recorre los pedidos para procesar la información de ventas.
- max(): encontrar el pedido más costoso de un cliente.
- limit(): obtener los tres platos más vendidos.
- collect(): convertir resultados en listas.

Estas operaciones permiten consultar, agrupar y resumir información.

Se utiliza la interfaz EstrategiaPromocion, implementada por las clases SinPromocion que conserva el subtotal original y DescuentoPorcentaje que aplica un descuento porcentual. La clase Pedido calcula el total llamando al método aplicar() de la promoción seleccionada.

Se utiliza la interfaz ObservadorPedido, implementada por la clase Notificacion para que Cada pedido pueda registrar interesados con suscribir(). Cuando se ejecuta cambiarEstado() se notifica automáticamente a los observadores que se registraron.

## Estructura del proyecto

El proyecto tiene las siguientes clases e interfaces:

- Producto.java: clase abstracta que define los atributos comunes de los productos.
- Plato.java: representa los platos del menú.
- Bebida.java: representa las bebidas.
- Combo.java: representa los combos compuestos por productos.
- Cliente.java: representa los clientes y establece su igualdad por documento.
- Repartidor.java: almacena los datos de los repartidores.
- Repositorio.java: implementa el almacenamiento y la búsqueda mediante genéricos y HashMap.
- Pedido.java: gestiona los pedidos, sus productos, promociones y cambios de estado.
- Consultas.java: contiene los ordenamientos y análisis de información.
- EstrategiaPromocion.java: interfaz para las promociones.
- SinPromocion.java: implementación sin descuento.
- DescuentoPorcentaje.java: implementación de descuento porcentual.
- ObservadorPedido.java: interfaz para recibir notificaciones.
- Notificacion.java: implementación de los avisos a interesados.
- Main.java: crea los datos de ejemplo, ejecuta las consultas y demuestra el funcionamiento del sistema.