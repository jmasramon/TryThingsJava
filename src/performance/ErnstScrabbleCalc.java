package performance;

import java.util.*;

public class ErnstScrabbleCalc {
    private static final int   MAX_CHARS = 28;
    private static final int[] SC        = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    private static final int[] DI        = {9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};

    private static int[] createHisto(String word, int[] histo) {
        for (int ch = 0; ch < histo.length; ch++) {
            histo[ch] = 0;
        }

        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a'; // to use the char as 0 based index for the arrays
            if (ch >= 0 && ch < MAX_CHARS) {
                histo[ch]++;
            }
        }
        System.out.println("histo:" + Arrays.toString(histo));
        return histo;
    }

    private static int getBlanksUsed(int[] histo) {
        int count = 0;
        for (int ch = 0; ch < histo.length; ch++) {
            if (histo[ch] > 0) {
                count += Integer.max(histo[ch] - DI[ch], 0);
            }
        }

        return count;
    }

    private static int getScoreWithBlanks(int[] histo) {
        int score = 0;
        for (int ch = 0; ch < histo.length; ch++) {
            if (histo[ch] > 0) {
                score += SC[ch] * Integer.min(histo[ch], DI[ch]);
            }
        }

        return score;
    }


    private static Map<Integer, List<String>> bestWordsOfScrabbleErnst(Set<String> shakespeareWords, Set<String> scrabbleWords) {
        Map<Integer, List<String>> map   = new TreeMap<>();
        int[]                      histo = new int[MAX_CHARS];
        for (String word : shakespeareWords) {
            if (scrabbleWords.contains(word)) {
                createHisto(word, histo);
                if (getBlanksUsed(histo) <= 2) {
                    int score = getScoreWithBlanks(histo);
                    if (score > -24) {
                        map.computeIfAbsent(score, ArrayList::new).add(word);
                    }
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        String [] shakespeareWords = {"love", "hate", "angels", "question","antony", "cleopatra", "comedy"};
        String [] scrabbleWords = {"love", "hate", "question", "angels","antony", "cleopatra", "comedy"};

        Map<Integer, List<String>> bestWordsMap = bestWordsOfScrabbleErnst(new HashSet<String>(Arrays.asList(shakespeareWords)), new HashSet<String>(Arrays.asList(scrabbleWords)));

        for (Map.Entry<Integer, List<String>> entry : bestWordsMap.entrySet()) {
            Integer key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
    }
}