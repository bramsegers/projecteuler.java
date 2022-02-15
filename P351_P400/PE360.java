package P351_P400;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE360 {

    /*
     * 1. S(2*r) = 2*S(r)
     * 2. https://en.wikipedia.org/wiki/Pythagorean_quadruple 
     */
    public static void main(String[] args) {
        new PE360().solve();
    }

    Set<List<Long>> abc = new HashSet<>();

    private void solve() {
        long n = (long) Math.pow(5, 10);
        long sum = 0, d = 1, ij2, m2;
        while (d <= n) {
            for (long i = 0; i * i <= d; i++) {
                for (long j = i; (ij2 = i * i + j * j) <= d; j++) {
                    for (long k = j; (m2 = d - ij2 - k * k) >= k * k; k++) {
                        long m = (long) Math.sqrt(m2);
                        if (m * m == m2 && (i + j + k + m) % 2 == 1) {
                            long gcd = Util.gcd(i, j);
                            gcd = (gcd == 1) ? 1 : Util.gcd(gcd, k);
                            gcd = (gcd == 1) ? 1 : Util.gcd(gcd, m);
                            if (gcd == 1) {
                                abc(n / d, new long[]{i, j, k, m});
                            }
                        }
                    }
                }
            }
            d *= 5;
        }
        for (List<Long> list : abc) {
            long i = list.get(0);
            long j = list.get(1);
            long k = list.get(2);
            long m = 1;
            m *= i == 0 ? 1 : 2;
            m *= j == 0 ? 1 : 2;
            m *= k == 0 ? 1 : 2;
            m *= (i < j && j < k) ? 6 : 3;
            sum += m * (i + j + k);
        }
        System.out.println(sum << 10);
    }

    void abc(long mult, long[] arr) {
        Set<List<Long>> set1 = new HashSet<>();
        for (List<Integer> p : Util.getPermutations(4)) {
            List<Long> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                list.add(arr[p.get(j)]);
            }
            set1.add(list);
        }
        for (List<Long> list : set1) {
            long m = list.get(0);
            long n = list.get(1);
            long p = list.get(2);
            long q = list.get(3);
            long a = mult * Math.abs(m * m + n * n - p * p - q * q);
            long b = mult * 2 * (m * q + n * p);
            long c = mult * Math.abs(2 * (n * q - m * p));
            List<Long> list2 = new ArrayList<>();
            list2.add(a);
            list2.add(b);
            list2.add(c);
            Collections.sort(list2);
            abc.add(list2);
        }
    }

}
