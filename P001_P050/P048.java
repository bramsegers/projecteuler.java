package P001_P050;

import java.math.BigInteger;

/*
 The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 */
public class P048 {

    public static String solve(int num, int dig) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= num; i++) {
            sum = sum.add(new BigInteger(String.valueOf(i)).pow(i));
        }
        return sum.toString().substring((sum.toString()).length() - dig);
    }

    public static void main(String[] args) {
        System.out.println(P048.solve(1000, 10));
    }
}
