package ducks;

public interface Flyer {
     default void fly() {
        System.out.println("Flying!!!");
    }
}
