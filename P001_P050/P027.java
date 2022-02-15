package P001_P050;

import util.Util;

/*
 Considering quadratics of the form:
 n² + an + b, where |a|<1000 and |b|<1000 
 
 Find the product of the coefficients, a and b, for the quadratic expression 
 that produces the maximum number of primes for consecutive values of n, 
 starting with n = 0.
 */
public class P027 {

    public static int solve(int maxA, int maxB) {
        int max = 0;
        int maxab = 0;
        for (int a = -maxA + 1; a < maxA; a++) {
            for (int b = -maxB + 1; b < maxB; b++) {
                int n = 0;
                while (Util.isPrime((n * n) + (a * n) + b)) {
                    n++;
                }
                if (n >= max) {
                    max = n;
                    maxab=a*b;
                    System.out.println("(" + a + "," + b + ") -> " + n);
                }
            }
        }
        return maxab;
    }

    public static void main(String[] args) {
        System.out.println(P027.solve(1000, 1000)); 
    }
}
