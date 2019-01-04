package starbuzz;

import starbuzz.Beverage;
import starbuzz.CoffeePrices;

public class HouseBlend extends Beverage {
    float basicCost;

    public HouseBlend() {
        this.basicCost = CoffeePrices.HOUSE_BLEND.price;
        description = "House Blend";
    }

    @Override
    public float cost() {
        return super.cost() + basicCost;
    }}
