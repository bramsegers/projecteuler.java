package P051_P100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

/*
 * By replacing each of the letters in the word CARE with 1, 2, 9, and 6 respectively, 
 * we form a square number: 1296 = 36^2. What is remarkable is that, by using the same 
 * digital substitutions, the anagram, RACE, also forms a square number: 9216 = 96^2. 
 * We shall call CARE (and RACE) a square anagram word pair and specify further that 
 * leading zeroes are not permitted, neither may a different letter have the same 
 * digital value as another letter.
 * 
 * Using P098.txt, find all the square anagram word pairs (a palindromic word is 
 * NOT considered to be an anagram of itself).
 * 
 * What is the largest square number formed by any member of such a pair?
 * NOTE: All anagrams formed must be contained in the given text file.
 */
public class P098 {

    public static int solve(String[] words) {
        int maxSum = 0;
        Map<String, List<String>> map = new HashMap<>();
        for (String s : words) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String t = new StringBuilder().append(ch).toString();
            List<String> list = map.get(t);
            if (list == null) {
                list = new ArrayList<>();
                map.put(t, list);
            }
            list.add(s);
        }
        for (String s : map.keySet()) {
            List<String> list = map.get(s);
            if (list.size() > 1) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        maxSum = Math.max(maxSum, getMaxNum(list.get(i), list.get(j)));
                    }
                }
            }
        }
        return maxSum;
    }

    private static int getMaxNum(String w1, String w2) {
        int maxNum = 0;
        int max = (int) Math.pow(10, w1.length());
        int min = max / 10;
        List<String> squares = new ArrayList<>();
        int s, i = 1;
        while ((s = i * i) < max) {
            i++;
            if (s >= min) {
                squares.add(String.valueOf(s));
            }
        }
        for (String sq : squares) {
            if (match(w1).equals(match(sq))) {
                String sq2 = w2;
                for (i = 0; i < w1.length(); i++) {
                    sq2 = sq2.replace(String.valueOf(w1.charAt(i)), String.valueOf(sq.charAt(i)));
                }
                if (squares.contains(sq2)) {
                    maxNum = Math.max(maxNum, Integer.parseInt(sq));
                    maxNum = Math.max(maxNum, Integer.parseInt(sq2));
                    System.out.println(w1 + " " + w2 + " " + sq + " " + sq2);
                }
            }
        }
        return maxNum;
    }

    private static String match(String s) {
        List<Character> list = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (!list.contains(ch)) {
                list.add(ch);
            }
        }
        String abc = "abcdefghijklmnopqrstuvwxyz";
        int i = 0;
        for (char ch : list) {
            s = s.replaceAll(String.valueOf(ch), String.valueOf(abc.charAt(i++)));
        }
        return s;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P098.txt");
        System.out.println(P098.solve(list.get(0).replaceAll("\"", "").split(",")));
    }

}
