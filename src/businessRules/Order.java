package businessRules;


import businessRules.packing.Slip;

import java.awt.peer.ChoicePeer;
import java.util.ArrayList;
import java.util.function.Function;

public class Order {
    boolean                                     isProcessed;
    java.util.function.Function<Order, Order> processingStrategy;
    private ArrayList<Slip> slips = new ArrayList<>();

    public Order(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public Order(Function ...processingStrategies) {
        isProcessed = false;
        for (Function pStrategy : processingStrategies) {
            if (this.processingStrategy == null) {
                this.processingStrategy = pStrategy;
            } else {
                this.processingStrategy = this.processingStrategy.andThen(pStrategy);
            }
        }
    }

    public void addProcessingStrategy(Function newProcessingStrategy) {
        this.processingStrategy = this.processingStrategy.andThen(newProcessingStrategy);
    }

    public void setProcessed(boolean processed) {
        System.out.println("setting " + this + "to processed");
        isProcessed = processed;
    }

    public void setProcessingStrategy(Function<Order, Order> processingStrategy) {
        this.processingStrategy = processingStrategy;
    }

    public boolean process() {
        System.out.println("            Order " + this + " being processed");
        if (!isProcessed && this.processingStrategy != null) {
            this.processingStrategy.apply(this);
        }
        return isProcessed;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void addSlip(Slip slip) {
        slips.add(slip);
    }

    public ArrayList<Slip> getSlips() {
        return slips;
    }

    public boolean hasSlip() {
        return slips.size() > 0;
    }
}
