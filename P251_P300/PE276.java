package P251_P300;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import util.Util;

public class PE276 {

    /*
     * b(n) = https://oeis.org/A051493
     *      = moebius transform of a(n)
     * a(n) = https://oeis.org/A005044
     *      = a(n-2)+a(n-3)+a(n-4)-a(n-5)-a(n-6)-a(n-7)+a(n-9) for n>=9
     *
     * Edit: https://oeis.org/A001400
     */
    public static void main(String[] args) {
        new PE276().solve((int) 1E7);
    }

    long[] a;
    byte[] moebius;
    BitSet p;
    Map<Integer, List<Integer>> f;

    void solve(int max) {
        long sum = 0;
        a = new long[max + 1];
        moebius = new byte[max + 1];
        moebius[1] = (byte) 1;
        for (int n = 0; n <= max; n++) {
            a[n] = n < 9
                    ? countA005044(n)
                    : a[n - 2] + a[n - 3] + a[n - 4] - a[n - 5] - a[n - 6] - a[n - 7] + a[n - 9];
        }

        p = Util.getPrimesBS(max);
        f = new TreeMap<>();
        for (int i = p.nextSetBit(0); i >= 0; i = p.nextSetBit(i + 1)) {
            //System.out.println(i);
            for (int j = i; j <= max; j += i) {
                List<Integer> list = f.get(j);
                if (list == null) {
                    list = new ArrayList<>();
                    f.put(j, list);
                }
                list.add(i);
            }
        }
        for (int n : f.keySet()) {
            //System.out.println(n);
            List<Integer> fact = f.get(n);
            List<Integer> exp = new ArrayList<>();
            int te = 0;
            for (int p : fact) {
                int m = n, e = 0;
                while (m % p == 0) {
                    m /= p;
                    e++;
                }
                exp.add(e);
                te += e;
            }
            if (fact.size() == te) {
                moebius[n] = (byte) ((te % 2 == 0) ? 1 : -1);
            }
            List<Integer> divs = new ArrayList<>();
            getDivs(1, 0, divs, fact, exp);
            long b = 0;
            for (int d : divs) {
                b += a[d] * moebius[n / d];
            }
            sum += b;
        }
        System.out.println(sum);
    }

    void getDivs(int p, int i, List<Integer> divs, List<Integer> fact, List<Integer> exp) {
        if (i == exp.size()) {
            divs.add(p);
            return;
        }
        for (int ei = 0; ei <= exp.get(i); ei++) {
            getDivs(p, i + 1, divs, fact, exp);
            p *= fact.get(i);
        }
    }

    int countA005044(int n) {
        int count = 0;
        int bt = n + (n % 2 == 0 ? 2 : 1);
        for (int a = 1; a <= n / 3; a++) {
            int bs = Math.max(a, bt / 2 - a);
            count += ((n - a) / 2) - bs + 1;
        }
        return count;
    }

}
