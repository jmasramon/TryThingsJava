package starbuzz;

public class DarkRoastWithMilk extends Beverage {
    float basicCost;

    public DarkRoastWithMilk() {
        this.basicCost = CoffeePrices.DARK_ROAST.price;
        this.milkCost = CoffeePrices.MILK.price;
        description = "Dark Roast wiht milk";

    }

    @Override
    public float cost() {
        return super.cost() + basicCost;
    }
}
