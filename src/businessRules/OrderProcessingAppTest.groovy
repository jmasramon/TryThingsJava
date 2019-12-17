package businessRules

import businessRules.processingStrategies.payments.Book
import businessRules.processingStrategies.payments.Membership
import businessRules.processingStrategies.payments.PhysicalProduct
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

import java.util.concurrent.CompletableFuture
import java.util.function.Function

@DisplayName("Business Rules")
class OrderProcessingAppTest extends GroovyTestCase {
    OrderProcessingApp opa;
    CompletableFuture<String> finished;

    @Override
    void setUp() {
        super.setUp()
        opa = new OrderProcessingApp(null)
    }

    @BeforeEach
    void beforeEach() {
        print("New test --------------------------------")
    }

    void testTheAppShouldWorkWithEmptyQueue() {
        finished = new CompletableFuture<String>()
        opa.start(finished)
//        opa.stop()
        String result = finished.get()
        assertTrue(finished.done)
        assertEquals("I am done", result)
    }

    void testTheAppShouldProcessQueuedOrders() {
        finished = new CompletableFuture<String>()
        Function<Order, Boolean> physicalProdProcess = PhysicalProduct.&process as Function
        Function<Order, Boolean> bookProcess = Book.&process as Function
        Order pendingOrder = new Order(physicalProdProcess, bookProcess)
        Function<Order, Boolean> membershipProcess = Membership.&process as Function
        pendingOrder.addProcessingStrategy(membershipProcess)
        opa.addOrder(pendingOrder)
        opa.addOrder(new Order(true))
        opa.addOrder(new Order(true))
        opa.start(finished)
        pendingOrder.setProcessed(true)
        String result = finished.get()
        assertTrue(finished.done)
        assertEquals("I am done", result)
    }

    void testPackingSlipCreation() {
        finished = new CompletableFuture<String>()
        Function<Order, Boolean> physicalProdProcess = PhysicalProduct.&process as Function
        Function<Order, Boolean> bookProcess = Book.&process as Function
        Order bookPendingOrder = new Order(physicalProdProcess, bookProcess)

        Order physicalPendingOrder = new Order(physicalProdProcess, physicalProdProcess)
        opa.addOrder(bookPendingOrder)
        opa.addOrder(physicalPendingOrder)
        opa.addOrder(new Order(true))
        opa.start(finished)
//        bookPendingOrder.setProcessed(true)
        String result = finished.get()
        assertTrue(finished.done)
        assertEquals("I am done", result)
        assertEquals(2, bookPendingOrder.getSlips().size())
        assertEquals(1, physicalPendingOrder.getSlips().size())
    }

    void testMembershipManagement(){
        finished = new CompletableFuture<String>()
        Function<Order, Boolean> physicalProdProcess = PhysicalProduct.&process as Function
        Function<Order, Boolean> bookProcess = Book.&process as Function
        Order pendingOrder = new Order(physicalProdProcess, bookProcess)
        Function<Order, Boolean> membershipProcess = Membership.&process as Function
        pendingOrder.addProcessingStrategy(membershipProcess)
        opa.addOrder(pendingOrder)
        opa.addOrder(new Order(true))
        opa.addOrder(new Order(true))
        opa.start(finished)
        pendingOrder.setProcessed(true)
        String result = finished.get()
        assertTrue(finished.done)
        assertEquals("I am done", result)

    }
}
