package P101_P150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P143 {

    public static void main(String[] args) {
        //new P143().test(120000);
        new P143().solve(120000);
    }

    void solve(long nMax) {
        Map<Long, List<Long>> map = new HashMap<>();
        for (long m = 1; m * m <= nMax; m++) {
            for (long n = 1; n < m && m * (2 * n + m) <= nMax; n++) {
                if (gcd(m, n) == 1 && (m - n) % 3 != 0) {
                    long a = (2 * m * n) + (n * n);
                    long b = (m * m) - (n * n);
                    long A = Math.min(a, b);
                    long B = Math.max(a, b);
                    a = A;
                    b = B;
                    while (a + b <= nMax) {
                        List<Long> list = map.get(a);
                        if (list == null) {
                            list = new ArrayList<>();
                            map.put(a, list);
                        }
                        list.add(b);
                        a += A;
                        b += B;
                    }
                }
            }
        }
        Set<Long> sum = new HashSet<>();
        for (long k : map.keySet()) {
            List<Long> list = map.get(k);
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    long l = list.get(i);
                    long m = list.get(j);
                    List<Long> list3 = map.get(l);
                    if (list3 != null && list3.contains(m) && k + l + m <= nMax) {
                        System.out.println(k + " " + l + " " + m);
                        sum.add(k + l + m);
                    }
                }
            }
        }
        long rv = 0;
        for (long n : sum) {
            rv += n;
        }
        System.out.println(rv);
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    void test(long nMax) {
        long start = System.currentTimeMillis();
        Set<Long> pqr = new HashSet<>();
        for (long p = 1; p <= nMax; p++) {
            for (long q = p; q + p <= nMax; q++) {
                long aSq = (q * q) + (p * p) + q * p;
                long a = (long) Math.sqrt(aSq);
                if (a * a == aSq) {
                    for (long r = q; r + q + p <= nMax; r++) {
                        long bSq = (r * r) + (q * q) + r * q;
                        long b = (long) Math.sqrt(bSq);
                        if (b * b == bSq) {
                            long cSq = (r * r) + (p * p) + r * p;
                            long c = (long) Math.sqrt(cSq);
                            if (c * c == cSq) {
                                System.out.format("(p,q,r,a,b,c)=%d,%d,%d,%d,%d,%d%n", p, q, r, a, b, c);
                                pqr.add(r + q + p);
                            }
                        }
                    }
                }
            }
        }
        long sum = 0;
        for (long i : pqr) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }
}
