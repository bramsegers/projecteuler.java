package P051_P100;

import java.util.HashMap;
import java.util.Map;
import util.Util;

/*
 By replacing the 1st digit of *3, it turns out that six of the nine 
 possible values: 13, 23, 43, 53, 73, and 83, are all prime.

 By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit 
 number is the first example having seven primes among the ten generated numbers, 
 yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993.
 Consequently 56003, being the first member of this family, is the smallest 
 prime with this property.

 Find the smallest prime which, by replacing part of the number (not necessarily
 adjacent digits) with the same digit, is part of an eight prime value family.
 */
public class P051 {

    public static String solve(int num, int pMax) {
        Util.initPrimes(pMax);
        Map<Integer, Integer> map = new HashMap<>();
        for (int p : Util.primes) {
            map.clear();
            int pCut = p;
            while (pCut > 0) {
                int d = pCut % 10;
                pCut /= 10;
                Integer i = map.get(d);
                map.put(d, i == null ? 1 : i + 1);
            }
            for (int k : map.keySet()) {
                if (map.get(k) > (num > 7 ? 2 : 1)) {
                    int count = 0;
                    String out = "";
                    for (int i = 0; i < 10; i++) {
                        String pFam = String.valueOf(p).replaceAll(String.valueOf(k), String.valueOf(i));
                        if (!pFam.startsWith("0") && Util.primes.contains(Integer.parseInt(pFam))) {
                            count++;
                            out += pFam + " ";
                        }
                    }
                    if (count >= num) {
                        return p + "\n" + out;
                    }
                }
            }
        }
        return "Not found in prime range";
    }

    public static void main(String[] args) {
        System.out.println(P051.solve(8, 1000000));
    }
}
