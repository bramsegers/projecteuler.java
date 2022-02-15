package P051_P100;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/*
 The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes 
 and concatenating them in any order the result will always be prime. 
 For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these 
 four primes, 792, represents the lowest sum for a set of four primes with this property.

 Find the lowest sum for a set of five primes for which any two primes 
 concatenate to produce another prime.
 */
public class P060 {

    private static final Map<Integer, BitSet> map = new HashMap<>();

    public static int solve(int sMax, int pMax) {

        System.out.println("Generating primes");
        int pMaxSq = pMax * pMax;
        BitSet p = new BitSet(pMaxSq);
        p.flip(2, pMaxSq);
        for (int n = 2; n * n <= pMaxSq; n++) {
            if (p.get(n)) {
                for (int j = n; n * j <= pMaxSq; j++) {
                    p.set(n * j, false);
                }
            }
        }

        System.out.println("Generating concat pairs");
        for (int i = p.nextSetBit(0); i < pMax; i = p.nextSetBit(i + 1)) {
            BitSet p2 = new BitSet();
            for (int j = p.nextSetBit(i); j < pMax; j = p.nextSetBit(j + 1)) {
                p2.set(j, isConcat(i, j, p) && isConcat(j, i, p));
            }
            map.put(i, p2);
        }

        System.out.println("Generating solution");
        int sum = 0;
        for (int i = p.nextSetBit(0); i < pMax; i = p.nextSetBit(i + 1)) {
            BitSet b = new BitSet();
            b.or(map.get(i));
            sum += solve(b, sMax - 1, i, String.valueOf(i));
        }
        return sum;
    }

    private static boolean isConcat(int p1, int p2, BitSet p) {
        int pTemp = p2;
        while (pTemp > 0) {
            pTemp /= 10;
            p1 *= 10;
        }
        return p.get(p1 + p2);
    }

    private static int solve(BitSet b, int size, int sum, String path) {
        if (size == 0) {
            System.out.println(path + "-" + b);
            return sum + b.nextSetBit(0);
        } else {
            int rv = 0;
            for (int i = b.nextSetBit(0); i != -1; i = b.nextSetBit(i + 1)) {
                if (b.intersects(map.get(i))) {
                    BitSet b2 = new BitSet();
                    b2.or(b);
                    b2.and(map.get(i));
                    rv += solve(b2, size - 1, sum + i, path + "-" + i);
                }
            }
            return rv;
        }
    }

    public static void main(String[] args) {
        System.out.println(P060.solve(4, 10000));
    }

}
