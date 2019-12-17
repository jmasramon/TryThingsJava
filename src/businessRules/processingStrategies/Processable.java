package businessRules.processingStrategies;

import businessRules.Order;

public interface Processable {
    static Order process(Order order) {
        return order;
    };
}
