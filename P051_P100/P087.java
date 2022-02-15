package P051_P100;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import util.Util;

/*
 The smallest number expressible as the sum of a prime square, prime cube, 
 and prime fourth power is 28. In fact, there are exactly four numbers below 
 fifty that can be expressed in such a way:
 28 = 2^2 + 2^3 + 2^4
 33 = 3^2 + 2^3 + 2^4
 49 = 5^2 + 2^3 + 2^4
 47 = 2^2 + 3^3 + 2^4

 How many numbers below fifty million can be expressed as 
 the sum of a prime square, prime cube, and prime fourth power?
 */
public class P087 {

    private static int solve(int nMax) {
        BitSet bs = new BitSet(nMax + 1);
        int m2 = (int) Math.pow(nMax, 1D / 2);
        int m3 = (int) Math.pow(nMax, 1D / 3);
        int m4 = (int) Math.pow(nMax, 1D / 4);
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        Util.initPrimes(m2);
        for (int p : Util.primes) {
            list2.add(p);
            if (p <= m3) {
                list3.add(p);
            }
            if (p <= m4) {
                list4.add(p);
            }
        }
        for (int p2 : list2) {
            for (int p3 : list3) {
                for (int p4 : list4) {
                    int n = p2 * p2 + p3 * p3 * p3 + p4 * p4 * p4 * p4;
                    if (n <= nMax) {
                        bs.set(n);
                    }
                }
            }
        }
        return bs.cardinality();
    }

    public static void main(String[] args) {
        System.out.println(P087.solve(50000000));
    }
}
