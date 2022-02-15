package P101_P150;

import util.Util;

/*
 * Let Pn be the nth prime: 2, 3, 5, 7, 11, ..., and let r be the remainder when 
 * (Pn-1)^n + (Pn+1)^n is divided by Pn^2.
 * 
 * For example, when n = 3, P3 = 5, and 4^3 + 6^3 = 280 ≡ 5 mod 25.
 * The least value of n for which the remainder first exceeds 10^9 is 7037.
 * 
 * Find the least value of n for which the remainder first exceeds 10^10.
 */
public class P123 {

    // r = 2*Pn*n mod Pn^2 
    public static long solve(long rMin) {
        Util.initPrimes(1000000);
        int n = 1;
        long pn, rem = 0;
        while (rem < rMin) {
            n += 2;
            pn = (long) Util.primes.get(n - 1);
            rem = (2 * pn * n) % (pn * pn);
            System.out.format("(n,pn,r)=(%d,%d,%d)%n", n, pn, rem);
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(P123.solve(10000000000L));
    }
}
