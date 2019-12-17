package performance;

import sun.jvm.hotspot.utilities.Assert;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.min;
import static java.lang.Math.max;
//import static java.util.stream.Collectors;

public class ScrabbleCalculator {

    private static final char []                 Alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final int []                  SC       = { 1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
    private static final Map<Character, Integer> SCMap    = IntStream.range(0, Alphabet.length).boxed().collect(Collectors.toMap(i -> Alphabet[i], i -> SC[i]));
    private static final int []                  DI       = { 9,2,2,1,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
    private static final Map<Character, Integer> DIMap    = IntStream.range(0, Alphabet.length).boxed().collect(Collectors.toMap(i -> Alphabet[i], i -> DI[i]));

    /* We do not really need the maps if we can index the arrays with the letters directly: by normalizing them to 0 based by
    * removing the integer value of a to all of the letters */

    public static void main(String[] args) {
        System.out.println("Scrabble calculator works");

        Assert.that(SC.length == DI.length, "NO iguals. " + SC.length + " != " + DI.length);
        Assert.that(SC.length == Alphabet.length, "NO iguals a alphabet. ");
        Assert.that(SCMap.size() == Alphabet.length, "Map no igual a alphabet. ");
        Assert.that(DIMap.size() == Alphabet.length, "Map no igual a alphabet. ");

        Assert.that(ignore("abcbbbcb"), "Ignore not working");
        Assert.that(!ignore("abcbcb"), "Ignore not working");

        Assert.that(score("abcbbbcb") == 0, "Ignore not working");
        Assert.that(score("abcbcb") == 13, "Ignore not working");

        System.out.print("lettersHistogram: ");
        System.out.println("holalola".chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        int i = 1000000;
        while (i-- > 0) {
            score("abcbbbcb");
            score("abcbcb");
        }

        System.out.println("Finished!!");

    }

    private static final boolean ignore(String word) {
        Map<Character, Integer> letterCardinality = letterCardinality(word);
//        System.out.println(word + " letter cardinality = " + letterCardinality.toString());

        return ignore(letterCardinality);
    }

    private static boolean ignore(Map<Character,Integer> letterCardinality) {
        int score = 0;

        for(Character c: letterCardinality.keySet()) {
            score +=  max(letterCardinality.get(c) - DIMap.get(c), 0);
//            System.out.println("char " + c.toString() + ": SC="+SCMap.get(c)+" DI=" +  DIMap.get(c));
        }
        boolean res = score > 2;
//        System.out.println("Score="+score+". Ignore = " + res);

        return res;
    }

    private static Map<Character, Integer> letterCardinality(String word) {
//        List<Character> list = toCharList(word);
//        System.out.println(word+" char list="+list.toString());

        return toCharCountMap(toCharArray(word));
    }

    private static List<Character> toCharList(String word) {
        List<Character> list = new ArrayList<>(word.length());

        for(char c : word.toCharArray()) {
            list.add(c);
        }

        return list;
    }

    private static char [] toCharArray(String word) {
         return  word.toCharArray();
    }

    private static final Map<Character, Integer> toCharCountMap(List<Character> charList) {
        Map<Character, Integer> map = new HashMap<>(charList.size(), 1);

        for(Character c: charList) {
            if (map.get(c) != null) {
                map.replace(c, map.get(c) + 1);
            } else map.put(c, 1);
        }
        return map;
    }

    private static final Map<Character, Integer> toCharCountMap(char [] charList) {
        Map<Character, Integer> map = new HashMap<>(charList.length, 1);
//        System.out.println("Map initialized to capacity=" + charList.length+ " and loadfactor=1");

        for(Character c: charList) {
            if (map.get(c) != null) {
                map.replace(c, map.get(c) + 1);
            } else map.put(c, 1);
        }
//        System.out.println("Map final size=" + map.size());

        return map;
    }

    private static final int score(String word) {
        int score = 0;
        Map<Character, Integer> letterCardinality = letterCardinality(word);

        if (!ignore(letterCardinality)) {
            score = score(letterCardinality);
        }
//        System.out.println(word + " score = " + score);

        return score;
    }

    private static final int score(Map<Character, Integer> letterCardinality) {
        int score = 0;

        for(Character c: letterCardinality.keySet()) {
            score += SCMap.get(c) * min(letterCardinality.get(c), DIMap.get(c));
        }

        return score;
    }
}
