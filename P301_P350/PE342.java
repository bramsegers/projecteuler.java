package P301_P350;

import java.util.BitSet;
import java.util.TreeMap;
import java.util.TreeSet;
import util.Util;

public class PE342 {

    public static void main(String[] args) {
        new PE342().test(100);
        new PE342().solve((long) 1E10);
    }

    long max, sum;
    BitSet primes;
    TreeMap<Integer, TreeMap<Integer, Integer>> factors = new TreeMap<>();

    void solve(long m) {
        max = m;
        int pmax = (int) Math.sqrt(max / 2);
        primes = Util.getPrimesBS(pmax);
        pmax = primes.length() - 1;
        for (int n = 2; n <= pmax; n++) {
            factors.put(n, factorize(n));
        }
        solve(1, pmax, new TreeMap<>());
        System.out.format("P(%d)=%d%n", max, sum);
    }

    void solve(long n, int pmax, TreeMap<Integer, Integer> f) {
        sum += (f.isEmpty() && n > 1) ? n : 0;
        int pmin = f.isEmpty() ? 2 : primes.nextSetBit(f.lastKey() + 1);
        for (int p = pmax; p >= pmin; p = primes.previousSetBit(p - 1)) {
            double pe = 1D * p * p;
            while (pe * n < max) {
                solve((long) (n * pe), primes.previousSetBit(p - 1), factors(f, p - 1));
                pe *= 1D * p * p * p;
            }
        }
        if (!f.isEmpty()) {
            int p = f.lastKey();
            int e = f.get(p) == 2 ? 1 : 3;
            double pe = Math.pow(p, e);
            while (pe * n < max) {
                TreeMap<Integer, Integer> f2 = factors(f, p - 1);
                f2.remove(p);
                solve((long) (n * pe), primes.previousSetBit(p - 1), f2);
                pe = Math.pow(p, (e = e + 3));
            }
        }
    }

    TreeMap<Integer, Integer> factorize(int n) {
        TreeMap<Integer, Integer> f = new TreeMap<>();
        int d = 1;
        while (n > 1) {
            d = primes.nextSetBit(d + 1);
            int e = 0;
            while (n % d == 0) {
                n /= d;
                e++;
            }
            if (e > 0) {
                f.put(d, e);
            }
            if (primes.get(n)) {
                f.put(n, 1);
                n = 1;
            }
        }
        return f;
    }

    TreeMap<Integer, Integer> factors(TreeMap<Integer, Integer> f1, int n) {
        TreeMap<Integer, Integer> f = new TreeMap<>();
        TreeMap<Integer, Integer> f2 = factorize(n);
        TreeSet<Integer> keys = new TreeSet<>(f1.keySet());
        keys.addAll(f2.keySet());
        for (int k : keys) {
            Integer i = f1.get(k);
            Integer j = f2.get(k);
            int e = (i == null) ? 0 : i;
            e += (j == null) ? 0 : j;
            e %= 3;
            if (e > 0) {
                f.put(k, e);
            }
        }
        return f;
    }

    void test(int max) {
        long sum = 0;
        Util.initPrimes(max);
        for (int n = 2; n < max; n++) {
            int phi = Util.totient(n * n);
            int c = (int) Math.cbrt(phi);
            if (c * c * c == phi) {
                sum += n;
            }
        }
        System.out.format("P(%d)=%d%n", max, sum);
    }

}
