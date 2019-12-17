package businessRules.processingStrategies.payments;

import businessRules.Order;
import businessRules.packing.SlipFactory;
import businessRules.packing.SlipType;
import businessRules.processingStrategies.Processable;

public class Book implements Processable {
    static public Order process(Order order){
        order.addSlip(SlipFactory.generateSlip(order, SlipType.ROYALTY));
        order.setProcessed(true);
        return order;
    }
}
