package P051_P100;

import java.util.Arrays;
import util.Util;

/*
 Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
 Find the value of n, 1<n<10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
 */
public class P070 {

    public static int solve(int nMax) {
        int sq = (int) Math.sqrt(nMax);
        int a = sq >> 1;
        int b = sq + a;
        int minN = Integer.MAX_VALUE;
        int minPhi = 1;
        Util.initPrimes(b);
        for (int i = a; i < b; i++) {
            for (int j = i; i * j < nMax; j++) {
                int n = i * j;
                int phi = Util.totient(n);
                if ((1D * n / phi < 1D * minN / minPhi) && isPermutation(n, phi)) {
                    minN = n;
                    minPhi = phi;
                    System.out.println("φ(" + n + ")=" + phi + " φ(n)/n=" + (1D * n / phi));
                }
            }
        }
        return minN;
    }

    private static boolean isPermutation(int n1, int n2) {
        char[] ch1 = String.valueOf(n1).toCharArray();
        char[] ch2 = String.valueOf(n2).toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return Arrays.equals(ch1, ch2);
    }
    
    public static void main(String[] args) {
        System.out.println(P070.solve(10000000));
    }
}
