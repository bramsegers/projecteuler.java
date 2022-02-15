package P051_P100;

import java.math.BigInteger;

/*
 It is possible to show that the square root of two can be expressed 
 as an infinite continued fraction.
 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

 By expanding this for the first four iterations, we get:
 1 + 1/2 = 3/2 = 1.5
 1 + 1/(2 + 1/2) = 7/5 = 1.4
 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 The next three expansions are 99/70, 239/169, and 577/408, but the eighth 
 expansion, 1393/985, is the first example where the number of digits 
 in the numerator exceeds the number of digits in the denominator.

 In the first one-thousand expansions, how many fractions contain a numerator 
 with more digits than denominator?
 */
public class P057 {

    public static int solve(int num) {
        // (p/q) -> (p+2q)/(p+q)
        BigInteger p = new BigInteger("1");
        BigInteger q = new BigInteger("1");
        int sum = 0;
        for (int i = 0; i < num; i++) {
            BigInteger p2 = p.add(q.add(q));
            q = p.add(q);
            p = p2;
            System.out.println("gcd:" + p.gcd(q) + ", (p/q)=(" + p + "/" + q + ")");
            sum += ("" + p).length() > ("" + q).length() ? 1 : 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P057.solve(1000));
    }
}
