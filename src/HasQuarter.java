public class HasQuarter implements State {
    private HasQuarter() {}

    public static State getInstance() {
        return new HasQuarter();
    }

    @Override
    public State insertQuarter(int count) {
        return this;
    }

    @Override
    public State ejectQuarter(int count) {
        return NoQuarter.getInstance();
    }

    @Override
    public State turnCrank(int count) {
        if (count>1)
            return NoQuarter.getInstance();
        return SoldOut.getInstance();
    }

    @Override
    public State refill(int numGumBalls) {
        return NoQuarter.getInstance();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
