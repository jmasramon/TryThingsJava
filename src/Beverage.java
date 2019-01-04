abstract public class Beverage {
    String description;

    protected float basicCost;
    protected float milkCost;
    protected float soyCost;

    public float getSoyCost() {
        return soyCost;
    }

    public float getMilkCost() {
        return milkCost;
    }

    private boolean hasMilk() {
        return milkCost != 0.0f;
    }

    private boolean hasSoy() {
        return soyCost != 0.0f;
    }

    public String getDescription() {
        return description;
    }

    public float cost() {
        float condimentsCost = 0.0f;
        if (hasMilk()) {
            condimentsCost += getMilkCost();
        }
        if (hasSoy()) {
            condimentsCost += getSoyCost();
        }
        return condimentsCost;
    };

}
