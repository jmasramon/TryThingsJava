public interface Quacker {
    default void quack(){
        System.out.println("Quack");
    }
}
