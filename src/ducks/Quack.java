package ducks;

public class Quack implements Quacker{
    @Override
    public void quack() {
        System.out.println("ducks.Quack");
    }
}
