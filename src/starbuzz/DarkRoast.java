package starbuzz;

public class DarkRoast extends Beverage {
    float basicCost;

    public DarkRoast() {
        this.basicCost = CoffeePrices.DARK_ROAST.price;
        description = "Dark Roast";
    }

    @Override
    public float cost() {
        return super.cost() + basicCost;
    }
}
