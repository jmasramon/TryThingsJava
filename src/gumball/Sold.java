package gumball;

public class Sold implements State {
    private Sold() {}

    public static State getInstance() {
        return new Sold();
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
