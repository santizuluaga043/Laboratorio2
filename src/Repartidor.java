public class Repartidor {
    private final String id;
    private final String nombre;
    private final double calificacion;
    private final int entregas;

    public Repartidor(String id, String nombre,
                      double calificacion, int entregas) {
        this.id = id;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.entregas = entregas;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public int getEntregas() {
        return entregas;
    }

    @Override
    public String toString() {
        return nombre + " - " + calificacion
                + " estrellas - " + entregas + " entregas";
    }
}

