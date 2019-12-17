package businessRules.processingStrategies.payments;

import businessRules.Order;
import businessRules.processingStrategies.Processable;

public class Membership implements Processable {
    static public Order process(Order order){
        System.out.println("******** Generating membership for order " + order + " *******");
        return order;
    }
}
