package P501_P550;

import java.util.BitSet;
import java.util.TreeMap;
import util.Util;

public class PE518 {

    public static void main(String[] args) {
        //new Test().test();
        new PE518().solve();
    }

    long sum;
    int max = 100000000;
    BitSet primes = Util.getPrimesBS(max);
    TreeMap<Integer, Integer> factors = new TreeMap<>();

    void test() {
        for (int i = primes.nextSetBit(0); i >= 0; i = primes.nextSetBit(i + 1)) {
            long p2 = i + 1;
            for (int j = primes.previousSetBit(i); (j = primes.previousSetBit(j - 1)) >= 0;) {
                long p1 = j + 1;
                long p3 = p2 * p2 / p1;
                if (p3 > max) {
                    break;
                }
                if (p2 * p2 % p1 == 0 && primes.get((int) p3 - 1)) {
                    sum += p1 + p2 + p3 - 3;
                    System.out.println((p1 - 1) + " " + (p2 - 1) + " " + (p3 - 1));
                }
            }
        }
        System.out.println(sum);
    }

    void solve() {
        for (int i = primes.nextSetBit(0); i >= 0; i = primes.nextSetBit(i + 1)) {
            factors.clear();
            factorize(i + 1);
            count(1, i + 1, factors.firstKey());
        }
        System.out.println(sum);
    }

    void count(long p1, long p2, Integer p) {
        if (p == null) {
            if (primes.get((int) p1 - 1)) {
                long p3 = p2 * p2 / p1;
                if (p3 < max && primes.get((int) p3 - 1)) {
                    sum += p1 + p2 + p3 - 3;
                }
            }
        } else {
            for (int e = 0; e <= 2 * factors.get(p) && p1 < p2; e++) {
                count(p1, p2, factors.higherKey(p));
                p1 *= p;
            }
        }
    }

    void factorize(int n) {
        int d = 2;
        while (n > 1) {
            int e = 0;
            while (n % d == 0) {
                n /= d;
                e++;
            }
            if (e > 0) {
                factors.put(d, e);
            }
            if (primes.get(n)) {
                factors.put(n, 1);
                return;
            }
            d = primes.nextSetBit(d + 1);
        }
    }

}
