package P101_P150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * In the game of darts, when a player is able to finish on their current score 
 * it is called a "checkout" and the highest checkout is 170: T20 T20 D25.
 * 
 * There are exactly eleven distinct ways to checkout on a score of 6:
 * D3
 * D1	D2	 
 * S2	D2	 
 * D2	D1	 
 * S4	D1	 
 * S1   S1   D2
 * S1	T1   D1
 * S1	S3   D1
 * D1	D1   D1
 * D1	S2   D1
 * S2	S2   D1
 * Note that D1 D2 is considered different to D2 D1 as they finish on different 
 * doubles. However, the combination S1 T1 D1 is considered the same as T1 S1 D1.
 * In addition we shall not include misses in considering combinations.
 * For example, D3 is the same as 0 D3 and 0 0 D3.
 * 
 * Incredibly there are 42336 distinct ways of checking out in total.
 * How many distinct ways can a player checkout with a score less than 100?
 */
public class P109 {

    private static String s =
            "S1,S2,S3,S4,S5,S6,S7,S8,S9,S10,S11,S12,S13,S14,S15,S16,S17,S18,S19,S20,SB"
            + ",D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12,D13,D14,D15,D16,D17,D18,D19,D20,DB"
            + ",T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20";
    private static Map<String, Integer> scores = new HashMap<>();
    private static Set<String> set;

    public static int solve(int num) {
        int sum = 0;
        for (String t : s.split(",")) {
            int score = t.endsWith("B") ? 25 : Integer.parseInt(t.substring(1));
            scores.put(t, score * (t.startsWith("S") ? 1 : t.startsWith("D") ? 2 : 3));
        }
        for (int i = 1; i < num; i++) {
            set = new HashSet<>();
            checkout(1, i, "");
            checkout(2, i, "");
            checkout(3, i, "");
            sum += set.size();
            System.out.println("Checkout " + i + ": " + set.size() + " ways: " + set);
        }
        return sum;
    }

    private static void checkout(int turns, int scoreLeft, String s) {
        if (turns == 1) {
            for (String sc : scores.keySet()) {
                if (sc.startsWith("D") && scores.get(sc) == scoreLeft) {
                    s += sc;
                    String[] t = s.split("-");
                    set.add((t.length == 3 && t[0].compareTo(t[1]) > 0)
                            ? t[1] + "-" + t[0] + "-" + t[2]
                            : s);
                }
            }
        } else {
            for (String sc : scores.keySet()) {
                if (scoreLeft - scores.get(sc) > 1) {
                    checkout(turns - 1, scoreLeft - scores.get(sc), s + sc + "-");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P109.solve(100));
    }
}
