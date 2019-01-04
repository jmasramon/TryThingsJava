package ducks;

import ducks.Duck;
import ducks.Quacker;

public class MallardDuck extends Duck {

    public MallardDuck(Quacker quacker) {
        super(quacker);
    }

    @Override
    public void display() {
        System.out.println("I am blue and red");
    }
}
