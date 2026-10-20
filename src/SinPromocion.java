public class SinPromocion implements EstrategiaPromocion {

    @Override
    public double aplicar(double subtotal) {
        return subtotal;
    }
}

