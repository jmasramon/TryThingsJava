package businessRules.packing;

import businessRules.Order;

public class Slip {
    SlipType type;

    public Slip(Order order, SlipType type) {
        this.type = type;
        System.out.println("New " + type + " slip generated for order " + order);
    }
}
