package gumball;

public class SoldOut implements State {
    private SoldOut() {}

    public static State getInstance() {
        return new SoldOut();
    }

    @Override
    public State insertQuarter(int count) {
        return this;
    }

    @Override
    public State ejectQuarter(int count) {
        return this;
    }

    @Override
    public State turnCrank(int count) {
        return this;
    }

    @Override
    public State refill(int numGumBalls) {
        return NoQuarter.getInstance();
    }
}
