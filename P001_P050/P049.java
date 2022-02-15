package P001_P050;

import java.util.Arrays;
import util.Util;

/*
 The arithmetic sequence, 1487, 4817, 8147, in which each of the terms 
 increases by 3330, is unusual in two ways: 
 (i)  each of the three terms are prime
 (ii) each of the 4-digit numbers are permutations of one another.

 There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes
 exhibiting this property, but there is one other 4-digit increasing sequence.

 What 12-digit number do you form by concatenating the three terms in this sequence?
 */
public class P049 {

    public static String solve(int digits) {
        String out = null;
        int nMax = (int) Math.pow(10, digits);
        Util.initPrimes(nMax);
        for (int p1 : Util.primes) {
            System.out.println(p1);
            for (int i = 2; i < (nMax - p1) >> 1; i = i + 2) {
                int p2 = p1 + i;
                int p3 = p2 + i;
                if (Util.isPrime[p2]
                        && Util.isPrime[p3]
                        && isPermutation(p1, p2)
                        && isPermutation(p1, p3)) {
                    System.out.format("%d: %d %d %d%n", i, p1, p2, p3);
                    out = String.format("%d%d%d", p1, p2, p3);
                }
            }
        }
        return out;
    }

    public static boolean isPermutation(int p1, int p2) {
        char[] digits1 = String.valueOf(p1).toCharArray();
        char[] digits2 = String.valueOf(p2).toCharArray();
        Arrays.sort(digits1);
        Arrays.sort(digits2);
        return Arrays.equals(digits1, digits2);
    }

    public static void main(String[] args) {
        System.out.println(P049.solve(4));
    }
}
