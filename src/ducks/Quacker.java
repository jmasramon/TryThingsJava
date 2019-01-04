package ducks;

public interface Quacker {
    default void quack(){
        System.out.println("ducks.Quack");
    }
}
