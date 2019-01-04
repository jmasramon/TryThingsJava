package starbuzz;

import starbuzz.Beverage;
import starbuzz.BeverageDecorator;
import starbuzz.CoffeePrices;

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
        return "starbuzz.Mid " + decorated.getDescription();
    }
}
