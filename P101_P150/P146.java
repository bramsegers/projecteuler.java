package P101_P150;

import java.math.BigInteger;

/*
 * The smallest positive integer n for which the numbers 
 * n^2+1, n^2+3, n^2+7, n^2+9, n^2+13, and n^2+27 are consecutive primes is 10. 
 * The sum of all such integers n below one-million is 1242490.
 * 
 * What is the sum of all such integers n below 150 million?
 */
public class P146 {

    public static long solve(long num) {
        long sum = 0;
        int[] seq1 = new int[]{1, 3, 7, 9, 13, 27};
        int[] seq2 = new int[]{5, 11, 15, 17, 19, 21, 23, 25};
        for (long n = 10; n < num; n = n + 10) {
            long m = n % 210;
            if ((m == 10 || m == 80 || m == 130 || m == 200)
                    && areProbablyPrimes(n * n, seq1)
                    && areProbablyNoPrimes(n * n, seq2)) {
                System.out.println(n);
                sum += n;
            }
        }
        return sum;
    }

    private static boolean areProbablyPrimes(long n, int[] seq) {
        for (int s : seq) {
            if (!isProbPrime(n + s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areProbablyNoPrimes(long n, int[] seq) {
        for (int s : seq) {
            if (isProbPrime(n + s)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isProbPrime(long n) {
        return new BigInteger(String.valueOf(n)).isProbablePrime(2);
    }

    public static void main(String[] args) {
        System.out.println(P146.solve(150000000));
    }
}
