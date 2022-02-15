package P101_P150;

import java.math.BigInteger;
import util.Util;

/*
 * A number consisting entirely of ones is called a repunit. We shall define R(k) 
 * to be a repunit of length k. For example, R(6) = 111111. 
 * 
 * Let us consider repunits of the form R(10^n).
 * Although R(10), R(100), or R(1000) are not divisible by 17, R(10000) is divisible by 17. 
 * Yet there is no value of n for which R(10^n) will divide by 19. In fact, it is remarkable 
 * that 11, 17, 41, and 73 are the only four primes below one-hundred that can be a factor of R(10^n).
 * 
 * Find the sum of all the primes below one-hundred thousand that will never be a factor of R(10^n).
 */
public class P133 {

    public static void main(String[] args) {
        System.out.println(P133.solve(100000, 20));
    }

    public static long solve(int primeLimit, int nLimit) {
        long sum = 0;
        long p = 0;
        BigInteger ONE = BigInteger.ONE;
        BigInteger TEN = new BigInteger(String.valueOf(10));
        while (p < primeLimit) {
            while (!Util.isPrime(++p)) {
            }
            if (p < primeLimit) {
                sum += p;
                for (int n = 1; n <= nLimit; n++) {
                    if (TEN.modPow(TEN.pow(n), new BigInteger(String.valueOf(9 * p))).equals(ONE)) {
                        sum -= p;
                        break;
                    }
                }
            }
        }
        return sum;
    }

}
