package ducks;

public class ReheadDuck extends Duck {
    public ReheadDuck(Quacker quacker) {
        super(quacker);
    }

    @Override
    public void display() {
        System.out.println("My head is red");
    }
}
