package businessRules;

import jline.internal.Nullable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;

public class OrderProcessingApp implements Runnable {
    Queue<Order> pendingOrdersQueue;
    boolean stop;
    CompletableFuture<String> finished;

    public OrderProcessingApp(@Nullable Queue<Order> pendingOrdersQueue) {
        if (pendingOrdersQueue != null) {
            this.pendingOrdersQueue = pendingOrdersQueue;
        } else {
            this.pendingOrdersQueue = new LinkedList<>();
        }
        stop = false;
    }

    // should create and return the completable future
    public CompletableFuture<String> start(CompletableFuture<String> finished) {
        this.finished = finished;
        System.out.println("starting order processing app");
        Thread thread = new Thread(this);
        thread.start();
        return finished;
    }

    public void stop() {
        System.out.println("stopping order processing app");
        stop = true;
    }

    public void addOrder(Order newOrder) {
        pendingOrdersQueue.add(newOrder);
    }

    private void startProcessingOrders() {
        int loopCount = 0;
        System.out.println("    starting order processing");
        while(!stop && loopCount<10) {
            System.out.println("            " + pendingOrdersQueue.size() + " orders pending");
            processAllPendingOrders();
            System.out.println("    Cleaning processed orders");
            removeFullyProcessedOrders();
            loopCount++;
        }
        System.out.println("    processing terminated");
        finished.complete("I am done");
    }

    private void removeFullyProcessedOrders() {
        for (Iterator<Order> iterator = pendingOrdersQueue.iterator(); iterator.hasNext(); ) {
            System.out.println("                pending order found. Chhecking if finished");
            Order currentOrder = iterator.next();
            if (currentOrder.isProcessed()) {
                System.out.println("                    removing " + currentOrder + " from the queue");
                iterator.remove();
            }
        }
    }

    private void processAllPendingOrders() {
        for (Order order: pendingOrdersQueue) {
            System.out.println("                    pending order found. Processing");
            order.process();
        }
    }

    @Override
    public void run() {
        startProcessingOrders();
    }
}
