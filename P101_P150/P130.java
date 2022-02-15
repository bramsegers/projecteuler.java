package P101_P150;

import util.Util;

/*
 * A number consisting entirely of ones is called a repunit. We shall define R(k) 
 * to be a repunit of length k. For example, R(6) = 111111. Given that n is a positive
 * integer and GCD(n, 10) = 1, it can be shown that there always exists a value k, 
 * for which R(k) is divisible by n, and let A(n) be the least such value of k.
 * For example, A(7) = 6 and A(41) = 5.
 * 
 * You are given that for all primes, p > 5, that p - 1 is divisible by A(p). 
 * For example, when p = 41, A(41) = 5, and 40 is divisible by 5.
 * However, there are rare composite values for which this is also true; the first 
 * five examples being 91, 259, 451, 481, and 703.
 * 
 * Find the sum of the first twenty-five composite values of n for which 
 * GCD(n, 10) = 1 and n  1 is divisible by A(n).
 */
public class P130 {

    public static void main(String[] args) {
        System.out.println(P130.solve(25));
    }

    public static int solve(int limit) {
        int sum = 0;
        int found = 0;
        int mod = 1;
        while (found < limit) {
            if ((mod = mod + 2) % 5 != 0) {
                int n = 1;
                int count = 1;
                while (n != 0) {
                    count++;
                    n = (n * 10 + 1) % mod;
                }
                if ((mod - 1) % count == 0 && !Util.isPrime(mod)) {
                    System.out.println(mod + "," + count);
                    found++;
                    sum += mod;
                }
            }
        }
        return sum;
    }
}
