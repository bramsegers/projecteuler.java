package P051_P100;

import java.math.BigInteger;

/*
 A googol (10^100) is a massive number: one followed by one-hundred zeros.
 100^100 is almost unimaginably large: one followed by two-hundred zeros. 
 Despite their size, the sum of the digits in each number is only 1.

 Considering natural numbers of the form, a^b, where a,b <= 100, 
 what is the maximum digital sum?
 */
public class P056 {

    public static int solve(int num) {
        int sum = 0;
        for (int base = 0; base <= num; base++) {
            for (int exp = 0; exp <= num; exp++) {
                sum = Math.max(sum, digitalSum(base, exp));
            }
        }
        return sum;
    }

    public static int digitalSum(int base, int exp) {
        int sum = 0;
        for (char ch : ("" + new BigInteger("" + base).pow(exp)).toCharArray()) {
            sum += ch - '0';
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P056.solve(100));
    }
}
