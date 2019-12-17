package generics;


import java.util.Arrays;
import java.util.List;

public class UsingGenerics<E> {
    List<Integer> intList = Arrays.asList(42);
//    List<String>[] stringLists = new List<String>[1];

    public void aFunction(E aParam) {
        System.out.println(aParam);
    }

    public static void main(String[] args) {
        UsingGenerics<String> uGen = new UsingGenerics<>();
        uGen.aFunction("Hola");
    }
}
