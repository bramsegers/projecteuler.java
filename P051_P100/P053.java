package P051_P100;

import java.math.BigInteger;

/*
 * There are exactly ten ways of selecting three from five, 12345:
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 * In combinatorics, we use the notation, 5C3 = 10. In general,
 *           n!
 *  nCr = --------  ,where r <=n, n! = n x (n-1) x ... x 3 x 2 x 1, and 0! = 1.
 *        r!(n-r)!
 * 
 * It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.
 * How many, not necessarily distinct, values of  nCr, for 1<=n<=100, 
 * are greater than one-million?
 */
public class P053 {

    public static int solve(int nMax, int bound) {
        int sum = 0;
        BigInteger max = new BigInteger("" + bound);
        for (int n = 0; n <= nMax; n++) {
            for (int r = 0; r <=n; r++) {
                sum += nCr(n, r).compareTo(max) < 0 ? 0 : 1;
            }
        }
        return sum;
    }

    public static BigInteger nCr(int n, int r) {
        BigInteger a = BigInteger.ONE;
        for (int i = r + 1; i <= n; i++) {
            a = a.multiply(new BigInteger(String.valueOf(i)));
        }
        for (int i = 2; i <= n - r; i++) {
            a = a.divide(new BigInteger(String.valueOf(i)));
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(P053.solve(100, 1000000));
    }
}
