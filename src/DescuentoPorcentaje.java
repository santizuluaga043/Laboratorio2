public class DescuentoPorcentaje implements EstrategiaPromocion {
    private final double porcentaje;

    public DescuentoPorcentaje(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("Porcentaje invalido");
        }
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicar(double subtotal) {
        return subtotal * (1 - porcentaje / 100);
    }
}

