package P251_P300;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.TreeMap;
import util.Util;

public class PE251 {

    /*
     * Wolfram alpha: 
     * (a+b*sqrt(c))^(1/3) + (a-b*sqrt(c))^(1/3) = 1 
     * => 27 * b^2 * c = (a+1)^2 * (8a-1)
     */
    public static void main(String[] args) {
        new PE251().solve(110000000); //P(110000000)=18946051 (total time: 2 minutes 17 seconds)
    }

    int max;
    BitSet primes;
    BigInteger A, BBC, MAX;
    TreeMap<Integer, Integer> f1 = new TreeMap<>();
    TreeMap<Integer, Integer> f2 = new TreeMap<>();

    void solve(int m) {
        max = m;
        MAX = BigInteger.valueOf(m);
        primes = Util.getPrimesBS(4 * m);
        int count = 0;
        for (int a = 2; a <= m / 2; a += 3) {
            f1.clear();
            f2.clear();
            dissolve(8 * a - 1, 1);
            dissolve(a + 1, 2);
            f1.put(3, f1.get(3) - 3);
            for (int p : f1.keySet()) {
                int e = f1.get(p) / 2;
                if (e > 0) {
                    f2.put(p, e);
                }
            }
            A = BigInteger.valueOf(a);
            BBC = BigInteger.valueOf(8 * a - 1)
                    .multiply(A.add(BigInteger.ONE))
                    .multiply(A.add(BigInteger.ONE))
                    .divide(BigInteger.valueOf(27));
            count += count(a, 1, 0);
        }
        System.out.format("P(%d)=%d%n", m, count);
    }

    void dissolve(int n, int e) {
        int p = 0;
        while (n > 1) {
            p = primes.nextSetBit(p + 1);
            while (n % p == 0) {
                n /= p;
                Integer v = f1.get(p);
                f1.put(p, v == null ? e : v + e);
            }
            if (primes.get(n)) {
                Integer v = f1.get(n);
                f1.put(n, v == null ? e : v + e);
                return;
            }
        }
    }

    int count(long a, long b, Integer p) {
        p = f2.higherKey(p);
        int rv = (p == null) && withinMax(b) ? 1 : 0;
        if (p != null && a + b < max) {
            for (int e = 0; e <= f2.get(p); e++) {
                rv += count(a, b, p);
                b *= p;
            }
        }
        return rv;
    }

    boolean withinMax(long b) {
        BigInteger B = BigInteger.valueOf(b);
        BigInteger C = BBC.divide(B.multiply(B));
        return A.add(B).add(C).compareTo(MAX) <= 0;
    }

}
