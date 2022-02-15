package P051_P100;

import java.math.BigDecimal;

/*
 It is well known that if the square root of a natural number is not an integer, 
 then it is irrational. The decimal expansion of such square roots is infinite 
 without any repeating pattern at all.
 The square root of two is 1.41421356237309504880...,
 and the digital sum of the first one hundred decimal digits is 475.

 For the first one hundred natural numbers, find the total of the digital sums 
 of the first one hundred decimal digits for all the irrational square roots.
 */
public class P080 {

    public static int solve(int num, int nrDigits) {
        int sum = 0;
        for (int n = 1; n <= num; n++) {
            if ((int) Math.sqrt(n) * (int) Math.sqrt(n) != n) {
                BigDecimal b = sqrt(new BigDecimal(String.valueOf(n)), nrDigits + 1);
                char[] ch = b.toString().substring(0, nrDigits + 1).toCharArray();
                for (char c : ch) {
                    sum += c - '0';
                }
                sum -= '.' - '0';
                System.out.println(n + ": " + b);
            }
        }
        return sum;
    }

    private static BigDecimal sqrt(BigDecimal A, int scale) {
        BigDecimal x0 = BigDecimal.ZERO;
        BigDecimal two = new BigDecimal("2");
        BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
        while (!x0.equals(x1)) {
            x0 = x1;
            x1 = A.divide(x0, scale, BigDecimal.ROUND_HALF_UP);
            x1 = x1.add(x0);
            x1 = x1.divide(two, scale, BigDecimal.ROUND_HALF_UP);
        }
        return x1;
    }

    public static void main(String[] args) {
        System.out.println(P080.solve(100, 100));
    }
}
