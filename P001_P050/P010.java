package P001_P050;

import util.Util;

/*
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
public class P010 {

    public static long solve(int nMax) {
        long sum = 0;
        Util.initPrimes(nMax);
        for (int i = 0; i < Util.isPrime.length; i++) {
            sum += Util.isPrime[i] ? i : 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P010.solve(2000000));
    }
}
