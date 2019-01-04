package gumball

class GumballMachineTest extends GroovyTestCase {
    def machine;

    void testInsertQuarter() {
        machine = new GumballMachine(1)
        assertEquals(NoQuarter, machine.state.getClass())
        machine.insertQuarter()
        assertEquals(HasQuarter, machine.state.getClass())

        machine = new GumballMachine(0)
        assertEquals(SoldOut, machine.state.getClass())
        machine.insertQuarter()
        assertEquals(SoldOut, machine.state.getClass())

        machine = new GumballMachine(1, Sold.getInstance())
        assertEquals(Sold, machine.state.getClass())
        machine.insertQuarter()
        assertEquals(Sold, machine.state.getClass())

        machine = new GumballMachine(1, HasQuarter.getInstance())
        assertEquals(HasQuarter, machine.state.getClass())
        machine.insertQuarter()
        assertEquals(HasQuarter, machine.state.getClass())

    }

    void testEjectQuarter() {
        machine = new GumballMachine(1, HasQuarter.getInstance())
        assertEquals(HasQuarter, machine.state.getClass())
        machine.ejectQuarter()
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, NoQuarter.getInstance())
        assertEquals(NoQuarter, machine.state.getClass())
        machine.ejectQuarter()
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, Sold.getInstance())
        assertEquals(Sold, machine.state.getClass())
        machine.ejectQuarter()
        assertEquals(Sold, machine.state.getClass())

        machine = new GumballMachine(1, SoldOut.getInstance())
        assertEquals(SoldOut, machine.state.getClass())
        machine.ejectQuarter()
        assertEquals(SoldOut, machine.state.getClass())

    }

    void testTurnCrank() {

        machine = new GumballMachine(2, HasQuarter.getInstance())
        assertEquals(HasQuarter, machine.state.getClass())
        machine.turnCrank()
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, HasQuarter.getInstance())
        machine.turnCrank()
        assertEquals(SoldOut, machine.state.getClass())

        machine = new GumballMachine(1, NoQuarter.getInstance())
        assertEquals(NoQuarter, machine.state.getClass())
        machine.turnCrank()
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, Sold.getInstance())
        assertEquals(Sold, machine.state.getClass())
        machine.turnCrank()
        assertEquals(Sold, machine.state.getClass())

        machine = new GumballMachine(1, SoldOut.getInstance())
        assertEquals(SoldOut, machine.state.getClass())
        machine.turnCrank()
        assertEquals(SoldOut, machine.state.getClass())

    }

    void testRefill() {

        machine = new GumballMachine(2, HasQuarter.getInstance())
        assertEquals(HasQuarter, machine.state.getClass())
        machine.refill(2)
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, HasQuarter.getInstance())
        machine.refill(2)
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, NoQuarter.getInstance())
        assertEquals(NoQuarter, machine.state.getClass())
        machine.refill(2)
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, Sold.getInstance())
        assertEquals(Sold, machine.state.getClass())
        machine.refill(2)
        assertEquals(NoQuarter, machine.state.getClass())

        machine = new GumballMachine(1, SoldOut.getInstance())
        assertEquals(SoldOut, machine.state.getClass())
        machine.refill(2)
        assertEquals(NoQuarter, machine.state.getClass())

    }
}
