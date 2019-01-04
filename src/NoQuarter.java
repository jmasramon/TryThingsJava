public class NoQuarter implements State {

    private NoQuarter() {}

    public static State getInstance() {
        return new NoQuarter();
    }

    @Override
    public State insertQuarter(int count) {
        return HasQuarter.getInstance();
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
        return this;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
