package P001_P050;

import java.math.BigInteger;

/*
 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 What is the sum of the digits of the number 2^1000?
 */
public class P016 {

    public static int solve(int base, int exp) {
        int sum = 0;
        BigInteger num = new BigInteger(String.valueOf(base)).pow(exp);
        for (char ch : num.toString().toCharArray()) {
            sum += ch - '0';
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P016.solve(2, 1000));
    }
}
