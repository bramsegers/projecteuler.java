package P001_P050;

import java.math.BigInteger;

/*
 n! means n x (n  1) x ... x 3 x 2 x 1
 For example, 10! = 10 x 9  ...  3 x 2 x 1 = 3628800,
 and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 Find the sum of the digits in the number 100!
 */
public class P020 {

    public static int solve(int num) {
        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= num; i++) {
            b = b.multiply(new BigInteger(String.valueOf(i)));
        }
        int sum = 0;
        for (char ch : b.toString().toCharArray()) {
            sum += ch - '0';
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P020.solve(100));
    }
}
