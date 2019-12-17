package businessRules.packing;

import businessRules.Order;

public class SlipFactory {
    public static Slip generateSlip(Order order, SlipType type) {
        return new Slip(order, type);
    }
}
