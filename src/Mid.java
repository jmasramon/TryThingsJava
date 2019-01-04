public class Mid extends BeverageDecorator {
    public Mid(Beverage decorated) {
        this.decorated = decorated;
    }

    @Override
    public float cost() {
        return decorated.cost() * CoffeePrices.MID.price;
    }

    @Override
    public String getDescription() {
        return "Mid " + decorated.getDescription();
    }
}
