package ducks;

abstract public class Duck implements Quacker, Flyer {
    private Quacker quacker;

    public Duck(Quacker quacker) {
        this.quacker = quacker;
    }

    public void setQuacker(Quacker quacker) {
        this.quacker = quacker;
    }

    public void swim() {
        System.out.println("Splash");
    }

    @Override
    public void quack() {
        quacker.quack();
    }

    abstract public void display();
}
