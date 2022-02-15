package P151_P200;

import java.util.BitSet;
import util.Util;

public class PE193 {

    /*
     * A positive integer n is called squarefree, if no square of a prime divides n.
     * Thus 1, 2, 3, 5, 6, 7, 10, 11 are squarefree, but not 4, 8, 9, 12.
     * How many squarefree numbers are there below 2^50?
     * 
     * Info: http://www.challenge.nm.org/archive/09-10/ProjectEuler.net_193_Solution+Explanation.pdf
     */
    public static void main(String[] args) {
        //new Test().test(30);
        new PE193().solve(50);
    }

    void solve(int eMax) {
        nMax = (1L << eMax) - 1;
        pMax = 1 + (int) Math.sqrt(nMax);
        sieve = new BitSet(pMax);
        sieve.set(2, pMax);
        int root = (int) Math.sqrt(pMax - 1);
        for (int i = 2; i <= root; i = sieve.nextSetBit(i + 1)) {
            for (int j = i * i; j < pMax; j += i) {
                sieve.clear(j);
            }
        }
        long sum = nMax - countExclusions(1, 1, -1);
        System.out.format("P(2^%d)=%d%n", eMax, sum);
    }

    long nMax;
    int pMax;
    BitSet sieve;

    long countExclusions(int prime, long product, int sign) {
        long exclusions = 0;
        int newSign = -1 * sign;
        for (int newPrime = sieve.nextSetBit(prime + 1);
                (newPrime >= 0) && (product * newPrime < pMax);
                newPrime = sieve.nextSetBit(newPrime + 1)) {
            long newProduct = product * newPrime;
            exclusions += countExclusions(newPrime, newProduct, newSign)
                    + newSign * nMax / (newProduct * newProduct);
        }
        return exclusions;
    }

    void test(int pMax) {
        Util.initPrimes(1 << (pMax >> 1));
        int max = 1 << pMax;
        BitSet bs = new BitSet(max);
        for (int p : Util.primes) {
            int pm, i = 0, pSq = p * p;
            while ((pm = (++i * pSq)) < max) {
                bs.set(pm);
            }
        }
        System.out.println(max - 1 - bs.cardinality());
    }

}
