package businessRules.processingStrategies.payments;

import businessRules.Order;
import businessRules.packing.SlipFactory;
import businessRules.packing.SlipType;
import businessRules.processingStrategies.Processable;

public class PhysicalProduct implements Processable {
    static public Order process(Order order){
        if (!order.hasSlip()) {
            order.addSlip(SlipFactory.generateSlip(order, SlipType.SHIPPING));
        } else {
            System.out.println("Alert, slip already existed. Product was already processed");
        }
        order.setProcessed(true);
        return order;
    }
}
