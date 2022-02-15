package P051_P100;

import java.math.BigInteger;

/*
 The 5-digit number, 16807=7^5, is also a fifth power. 
 Similarly, the 9-digit number, 134217728=8^9, is a ninth power.

 How many n-digit positive integers exist which are also an nth power?
 */
public class P063 {

    public static int solve(int maxB, int maxE) {
        int sum = 0;
        for (int b = 1; b <= maxB; b++) {
            for (int e = 1; e <= maxE; e++) {
                String pow = new BigInteger(String.valueOf(b)).pow(e).toString();
                if (pow.length() == e) {
                    System.out.println(b + "^" + e + "=" + pow);
                    sum++;
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P063.solve(100, 100));
    }
}
