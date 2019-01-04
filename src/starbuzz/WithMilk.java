package starbuzz;

import starbuzz.Beverage;
import starbuzz.BeverageDecorator;
import starbuzz.CoffeePrices;

public class WithMilk extends BeverageDecorator {

    public WithMilk(Beverage decorated) {
        this.decorated = decorated;
    }

    @Override
    public float cost() {
        return decorated.cost() + CoffeePrices.MILK.price;
    }

    @Override
    public String getDescription() {
        return decorated.getDescription() + ", plus Milk";
    }
}
