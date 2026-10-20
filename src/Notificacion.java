public class Notificacion implements ObservadorPedido {
    private final String destinatario;

    public Notificacion(String destinatario) {
        this.destinatario = destinatario;
    }

    @Override
    public void actualizar(String idPedido, String estado) {
        System.out.println("Aviso a " + destinatario
                + ": pedido " + idPedido + " -> " + estado);
    }
}

