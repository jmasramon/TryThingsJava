package performance;


import gumball.SoldOut;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.BiConsumer;

public class Formatting {


    public static void main(String[] args) {
        System.out.println("Main working");

        List<String> list = Arrays.asList("a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c","a", "b", "c");
        System.out.println("Original list = " + list);

        StringBuilder sb = new StringBuilder();
        StringBuilder sbb = sb.append("hola");
        System.out.println("Initial sb string" + sb.toString());
        System.out.println("appended sb string" + sbb.toString());;

        for(int i = 0; i<10000000; i++) {
            testStrConcat(list);
            testStrBuilder(list);
        }

        System.out.println("Finished");
    }

    private static void testStrConcat(List<String> list) {
        formatListWithStrings(list, Formatting::parseListElemConcat);
    }

    private static void testStrBuilder(List<String> list) {
        formatListWithSBuilder(list, Formatting::parseListElemBuilder);
    }

    private static void formatListWithStrings(List<String> list, Function<String, String> parseListElem) {
        StringBuilder strB = new StringBuilder(121);

        for (String element: list) {
            strB.append(parseListElem.apply(element)) ;
        }

//        System.out.println("final size: " + strB.length());

//        return removeSeparator(strB.toString());
//        return strB.toString();
    }

    private static void formatListWithSBuilder(List<String> list, BiConsumer<StringBuilder, String> parseListElem) {
        StringBuilder strB = new StringBuilder(121);

        for (String element: list) {
            parseListElem.accept(strB, element);
        }

//        System.out.println("res with SBuilder: " + strB.toString());

//        return removeSeparator(strB.toString());
    }


    private static String parseListElemConcat(String elem) {
        return "(" + elem + "), ";
    }

    private static void parseListElemBuilder(StringBuilder strB, String elem) {
        strB
            .append("(")
            .append(elem )
            .append("), ");
}

    private static String removeSeparator(String res) {
        return res.substring(0, res.length() - 2);
    }

}
