package P101_P150;

import java.math.BigInteger;
import java.util.Arrays;

/*
 * In the following equation x, y, and n are positive integers.
 * 
 *  1     1     1
 *  -  +  -  =  -
 *  x     y     n
 * 
 * It can be verified that when n = 1260 there are 113 distinct solutions and this is the 
 * least value of n for which the total number of distinct solutions exceeds one hundred.
 * 
 * What is the least value of n for which the number of distinct solutions exceeds four million?
 * Info: http://www.mathblog.dk/project-euler-110-efficient-diophantine-equation/
 */
public class P110 {

    private static int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43};
    private static BigInteger max, min;

    public static String solve(int nrSolutions) {
        // 3^x = 8000000 -> x=log(81)/log(3) -> x=14
        // a >= b >= c >= d >= ...... >= n 
        // 2^a * 3^b * 5^c * ... * 37^l * 41^m * 43^n <= 13082761331670030 
        // max a = 54         
        max = BigInteger.ONE;
        for (int p : primes) {
            max = max.multiply(new BigInteger(String.valueOf(p)));
        }
        min = new BigInteger(String.valueOf(max));
        for (int i = 0; i <= 54; i++) {
            poss(i, 0, new int[14], nrSolutions);
        }
        return String.valueOf(min);
    }

    private static void poss(int n, int pos, int[] exp, int nrSolutions) {
        exp[pos++] = n;
        if (getProduct(exp).compareTo(max) < 0) {
            if (pos == 14) {
                if (solutionsExceed(nrSolutions, exp)) {
                    BigInteger prod = getProduct(exp);
                    System.out.println(Arrays.toString(exp) + " " + prod);
                    if (prod.compareTo(min) < 0) {
                        min = prod;
                    }
                }
            } else {
                for (int i = 0; i <= n; i++) {
                    poss(i, pos, Arrays.copyOf(exp, exp.length), nrSolutions);
                }
            }
        }
    }

    private static BigInteger getProduct(int[] exp) {
        BigInteger prod = new BigInteger("1");
        for (int i = 0; i < exp.length; i++) {
            String b = String.valueOf(primes[i]);
            prod = prod.multiply(new BigInteger(b).pow(exp[i]));
        }
        return prod;
    }

    private static boolean solutionsExceed(int minSolutions, int[] exp) {
        int prod = 1;
        for (int i : exp) {
            prod *= 2 * i + 1;
        }
        return (prod / 2) + 1 > minSolutions;
    }

    public static void main(String[] args) {
        System.out.println(P110.solve(4000000));
    }
}
