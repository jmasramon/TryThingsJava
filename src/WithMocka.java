public class WithMocka extends BeverageDecorator {

    public WithMocka(Beverage decorated) {
        this.decorated = decorated;
    }

    @Override
    public float cost() {
        return decorated.cost() + CoffeePrices.MOKA.price;
    }

    @Override
    public String getDescription() {
        return decorated.getDescription() + ", plus Mocka";
    }
}
