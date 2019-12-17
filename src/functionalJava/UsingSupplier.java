package functionalJava;

import java.util.function.Supplier;

public class UsingSupplier {
    private static UsingSupplier inst = new UsingSupplier();

    private UsingSupplier() {
    }

    public static UsingSupplier getInstance() {
        return inst;
    }

    public void cry() {
        System.out.println("Buaaah!!!");
    }

    public static void main(String[] args) {
        Supplier<UsingSupplier> s = UsingSupplier::getInstance;

        s.get().cry();
    }
}
