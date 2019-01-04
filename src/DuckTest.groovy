class DuckTest extends GroovyTestCase {
    Duck aDuck;
    Quacker quacker;

    void testQuack() {
        aDuck.quack()

        aDuck = getRedheadDuck()
        aDuck.quack()
        aDuck.setQuacker(quacker)
        aDuck.quack()

        aDuck = getRubberDuck()
        aDuck.quack()
    }


    void testSwim() {
        aDuck.swim()

        aDuck = getRedheadDuck()
        aDuck.swim()

        aDuck = getRubberDuck()
        aDuck.swim()
    }

    void testDisplay() {
        aDuck.display()

        aDuck = getRedheadDuck()
        aDuck.display()

        aDuck = getRubberDuck()
        aDuck.display()
    }

    void testFly() {
        aDuck.fly();

        aDuck = getRedheadDuck();
        aDuck.fly();

    }

    @Override
    void setUp() {
        super.setUp()
        quacker = new Quack();
        aDuck = new MallardDuck(quacker);
    }

    private RubberDuck getRubberDuck() {
        new RubberDuck(new Squeack())
    }

    private ReheadDuck getRedheadDuck() {
        new ReheadDuck(new Squeack())
    }
}
