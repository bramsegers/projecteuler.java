package P101_P150;

import java.math.BigInteger;
import util.Util;

/*
 * A number consisting entirely of ones is called a repunit. We shall define R(k) 
 * to be a repunit of length k. For example, R(10) = 1111111111 = 11*41*271*9091, 
 * and the sum of these prime factors is 9414.
 * 
 * Find the sum of the first forty prime factors of R(10^9).
 */
public class P132 {

    public static void main(String[] args) {
        System.out.println(P132.solve(40, 1000000000));
    }

    public static int solve(int nrPrimes, int limit) {
        int sum = 0;
        int primesFound = 0;
        int p = 0;
        BigInteger ONE = BigInteger.ONE;
        BigInteger TEN = new BigInteger(String.valueOf(10));
        BigInteger EXP = new BigInteger(String.valueOf(limit));
        while (primesFound < nrPrimes) {
            while (!Util.isPrime(++p)) {
            }
            if (TEN.modPow(EXP, new BigInteger(String.valueOf(9 * p))).equals(ONE)) {
                sum += p;
                System.out.println(p);
                primesFound++;
            }
        }
        return sum;
    }
}
