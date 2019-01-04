package ducks;

public class RubberDuck extends Duck implements Quacker {

    public RubberDuck(Quacker quacker) {
        super(quacker);
    }

    @Override
    public void display() {
        System.out.println("I am all yellow");
    }

}
