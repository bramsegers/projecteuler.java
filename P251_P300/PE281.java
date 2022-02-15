package P251_P300;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE281 {

    /*
     * test() => https://oeis.org/A003239
     *           https://oeis.org/A118644
     *           https://oeis.org/A207816
     *           https://oeis.org/A208190
     *        => f(n) = Sum_{d|n} phi(n/d)*(m*d)!/(d!^m*m*n) 
     */
    public static void main(String[] args) {
        new PE281().solve();
    }

    void solve() {
        Util.initPrimes(100);
        BigInteger f, sum = BigInteger.ZERO;
        BigInteger max = BigInteger.valueOf((long) 1E15);
        int m = 1;
        while (f(++m, 1).compareTo(max) <= 0) {
            int n = 0;
            while ((f = f(m, ++n)).compareTo(max) <= 0) {
                System.out.format("f(%d,%d)=%d%n", m, n, f);
                sum = sum.add(f);
            }
        }
        System.out.format("Σf(m,n)=%d%n", sum); 
    }

    BigInteger f(int m, int n) {
        BigInteger sum_p = BigInteger.ZERO;
        BigInteger sum_q = BigInteger.ONE;
        BigInteger a, b, p, q;
        for (int d = 1; d <= n; d++) {
            if (n % d == 0) {

                int phi = Util.totient(n / d);
                a = BigInteger.valueOf(phi);
                b = fac(m * d);
                p = a.multiply(b);

                a = fac(d).pow(m);
                b = BigInteger.valueOf(m * n);
                q = a.multiply(b);

                sum_p = (sum_p.multiply(q)).add(sum_q.multiply(p));
                sum_q = sum_q.multiply(q);
            }
        }
        return sum_p.divide(sum_q);
    }

    BigInteger fac(long n) {
        BigInteger rv = BigInteger.ONE;
        for (long i = 1; i <= n; i++) {
            rv = rv.multiply(BigInteger.valueOf(i));
        }
        return rv;
    }

    void test(int m, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(n);
        }
        Set<String> set = new HashSet<>();
        test(list, m * n, "", set);
        System.out.format("T(%d,%d)=%d%n", m, n, set.size());
    }

    void test(List<Integer> list, int left, String p, Set<String> set) {
        if (left == 0) {
            for (int i = 0; i < p.length(); i++) {
                String rot = p.substring(i) + p.substring(0, i);
                if (set.contains(rot)) {
                    return;
                }
            }
            set.add(p);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 0) {
                List<Integer> list2 = new ArrayList<>(list);
                list2.set(i, list.get(i) - 1);
                test(list2, left - 1, p + i, set);
            }
        }
    }

}
