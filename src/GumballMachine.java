
public class GumballMachine {
    State state = null;
    int count = 0;

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NoQuarter.getInstance();
        } else {
            state = SoldOut.getInstance();
        }
    }
    public GumballMachine(int count, State state) {
        this.count = count;
        this.state = state;
    }

    public void insertQuarter() {
        state = state.insertQuarter(count);
    }

    public void ejectQuarter() {
        state = state.ejectQuarter(count);
    }

    public void turnCrank() {
        State initial = state;
        state = state.turnCrank(count);


        if (initial instanceof HasQuarter) {
            dispense();
        }
    }

    private void dispense() {
        count = count - 1;
    }

    public void refill(int numGumBalls) {
        this.count = numGumBalls;
        state = state.refill(count);
    }

    public String toString() {
//        StringBuffer result = new StringBuffer();
//        result.append("\nMighty Gumball, Inc.");
//        result.append("\nJava-enabled Standing Gumball Model #2004\n");
//        result.append("Inventory: " + count + " gumball");
//        if (count != 1) {
//            result.append("s");
//        }
//        result.append("\nMachine is ");
//        if (state == SOLD_OUT) {
//            result.append("sold out");
//        } else if (state == NO_QUARTER) {
//            result.append("waiting for quarter");
//        } else if (state == HAS_QUARTER) {
//            result.append("waiting for turn of crank");
//        } else if (state == SOLD) {
//            result.append("delivering a gumball");
//        }
//        result.append("\n");
//        return result.toString();
        return "";
    }
}

