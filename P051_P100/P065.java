package P051_P100;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/* 
 What is most surprising is that the important mathematical constant,
 e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].

 The first ten terms in the sequence of convergents for e are:
 2, 3, 8/3, 11/4, 19/7, 87/32, 106/39, 193/71, 1264/465, 1457/536, ...
 The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.

 Find the sum of digits in the numerator of the 100th convergent of the continued fraction for e.

 Info: http://mathworld.wolfram.com/PellEquation.html
 Info: http://mathworld.wolfram.com/ContinuedFraction.html  
 */
public class P065 {

    public static int solve(int steps) {
        List<Integer> e = new ArrayList<>();
        e.add(2);
        e.add(1);
        int n = 0;
        while (e.size() < 100) {
            e.add(2 * ++n);
            e.add(1);
            e.add(1);
        }
        BigInteger pLast1 = BigInteger.ZERO;
        BigInteger pLast2 = BigInteger.ZERO;
        BigInteger qLast1 = BigInteger.ZERO;
        BigInteger qLast2 = BigInteger.ZERO;
        BigInteger list0 = new BigInteger(e.get(0).toString());
        BigInteger list1 = new BigInteger(e.get(1).toString());
        for (n = 0; n < steps; n++) {
            BigInteger p
                    = (n == 0) ? list0
                    : (n == 1) ? (list0.multiply(list1)).add(BigInteger.ONE)
                    : (new BigInteger(e.get(n).toString()).multiply(pLast1)).add(pLast2);
            BigInteger q
                    = (n == 0) ? BigInteger.ONE
                    : (n == 1) ? list1
                    : (new BigInteger(e.get(n).toString()).multiply(qLast1)).add(qLast2);
            pLast2 = pLast1;
            qLast2 = qLast1;
            pLast1 = p;
            qLast1 = q;
            System.out.println("p/q=" + p + "/" + q);
        }
        int sum = 0;
        for (char ch : (pLast1.toString()).toCharArray()) {
            sum += ch - '0';
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P065.solve(100));
        System.out.println("p/q=6963524437876961749120273824619538346438023188214475670667/2561737478789858711161539537921323010415623148113041714756");
    }
}
