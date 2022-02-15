package P051_P100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/*
 In the game Monopoly, a player starts on the GO square and adds the scores on 
 two 6-sided dice to determine the number of squares they advance in a clockwise 
 direction. Without any further rules we would expect to visit each square with 
 equal probability: 2.5%. However, landing on G2J, CC and CH changes this distribution.
 
 Statistically it can be shown that the three most popular squares, in order, 
 are JAIL (6.24%) = Square 10, E3 (3.18%) = Square 24, and GO (3.09%) = Square 00. 
 So these three most popular squares can be listed with the six-digit modal string: 102400.

 If, instead of using two 6-sided dice, two 4-sided dice are used, find the six-digit modal string.

 Info: http://nl.wikipedia.org/wiki/Markovketen
 */
public class P084 {

    private static List<Integer> cc;
    private static List<Integer> ch;

    public static String solve(int diceSides1, int diceSides2, int rep) {
        String[] squares = new String[]{
            "GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3",
            "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3",
            "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
            "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2"};
        cc = new ArrayList<>(Arrays.asList(0, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1));
        ch = new ArrayList<>(Arrays.asList(0, 10, 11, 24, 39, 5, 107, 108, 109, 110, -1, -1, -1, -1, -1, -1));
        Collections.shuffle(cc);
        Collections.shuffle(ch);

        int loc = 0;
        int doubles = 0;
        Random r = new Random();
        int[] freq = new int[squares.length];
        for (int turn = 0; turn < rep; turn++) {
            int dice1 = 1 + r.nextInt(diceSides1);
            int dice2 = 1 + r.nextInt(diceSides2);
            doubles = (dice1 == dice2) ? doubles + 1 : 0;
            if (doubles == 3) {
                doubles = 0;
                loc = 10;
            } else {
                loc += (dice1 + dice2);
                loc = loc % squares.length;
                if (loc == 30) {
                    loc = 10;
                } else if (loc == 2 || loc == 17 || loc == 33) {
                    loc = getCC(loc);
                } else if (loc == 7 || loc == 22 || loc == 36) {
                    loc = getCH(loc);
                }
            }
            freq[loc]++;
        }

        Map<String, String> map = new TreeMap<>();
        for (int i = 0; i < freq.length; i++) {
            double d = (double) freq[i] / ((double) rep / 100D);
            map.put(d + "00000", ("" + (100 + i)).substring(1));
        }
        int i = 0;
        String rv = "";
        for (String s : map.keySet()) {
            if (++i > squares.length - 3) {
                rv = map.get(s) + rv;
            }
            System.out.println(s.substring(0, 5) + ": " + map.get(s));
        }
        return rv;
    }

    private static int getCC(int loc) {
        int c = cc.remove(0);
        cc.add(c);
        return c < 0 ? loc : c;
    }

    private static int getCH(int loc) {
        int c = ch.remove(0);
        ch.add(c);
        if (c < 100) {
            return c < 0 ? loc : c;
        }
        if (c == 107 || c == 108) {
            return loc == 7 ? 15 : (loc == 22 ? 25 : 5);
        }
        if (c == 109) {
            return loc == 7 ? 12 : (loc == 22 ? 28 : 12);
        }
        return loc == 7 ? 4 : (loc == 22 ? 19 : getCC(33));
    }

    public static void main(String[] args) {
        //System.out.println(P084.solve(6, 6, 200000000));
        System.out.println(P084.solve(4, 4, 200000000));
    }
}
