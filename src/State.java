public interface State {
    State insertQuarter(int count);

    State ejectQuarter(int count);

    State turnCrank(int count);

    State refill(int numGumBalls);

    String toString();
}
