package P201_P250;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import util.Util;

public class PE223 {

    /*
     *  a^2 + b^2 = c^2 + 1
     *  a^2 - 1 = c^2 - b^2
     * (a-1)(a+1) = (c-b)(c+b)
     * => factorize (a-1) en (a+1)
     * => for each a=f1*f2 (a<=max/3, f1<f2):
     *             c-b=f1, c+b=f2
     *             c = (f1+f2)/2
     *             b = f2-c
     * ( check f1+f2 is even, a<=b<=c, a+b+c<=max )             
     *
     * P(25000000)=61614848
     * BUILD SUCCESSFUL (total time: 1 minute 38 seconds)
     */
    public static void main(String[] args) {
        new PE223().solve(1000);
    }

    void solve(int nMax) {

        // init
        long count = (nMax - 1) / 2; // trivial case where a=1
        List<Integer> list = new ArrayList<>();
        List<Integer> primes = Util.getPrimes(nMax / 3);
        decompose = new int[nMax / 3 + 1][2];
        for (int p : primes) {
            for (int i = p; i <= nMax / 3; i += p) {
                if (decompose[i][0] == 0) {
                    decompose[i][0] = p;
                    decompose[i][1] = i / p;
                }
            }
        }

        // iterate over a, decompose a^2-1
        for (long a = 2; a < nMax / 3; a++) {
            decompose((int) (a - 1), (int) (a + 1));
            list.clear();
            int m = 1;
            for (int k : map.keySet()) {
                int e = map.get(k);
                list.add(e);
                m *= e + 1;
            }
            int[] exponents = new int[list.size()];
            // iterate over factors of a^2-1
            for (int i = 0; i < m / 2; i++) {
                long f = 1;
                int j = 0;
                for (int k : map.keySet()) {
                    int e = exponents[j++];
                    for (int p = 0; p < e; p++) {
                        f *= k;
                    }
                }
                long A = (a - 1) * (a + 1);
                long f1 = Math.min(f, A / f);
                long f2 = Math.max(f, A / f);
                long c = (f1 + f2) / 2;
                long b = f2 - c;
                if (a <= b && b <= c && a + b + c <= nMax && ((f1 + f2) & 1) == 0) {
                    //System.out.println("    " + a + "," + b + "," + c);
                    count++;
                }
                next(list, exponents);
            }
        }

        // show result
        System.out.format("P(%d)=%d%n", nMax, count);
    }

    int[][] decompose;
    Map<Integer, Integer> map = new TreeMap<>();

    void decompose(int... n) {
        map.clear();
        for (int i : n) {
            int t = i;
            while (t > 1) {
                int f = decompose[t][0];
                Integer e = map.get(f);
                e = e == null ? 1 : e + 1;
                map.put(f, e);
                t = decompose[t][1];
            }
        }
    }

    int[] next(List<Integer> list, int[] c) {
        int i = c.length - 1;
        c[i]++;
        while (c[i] > list.get(i)) {
            if (i == 0) {
                return null;
            }
            c[i] = 0;
            c[--i]++;
        }
        return c;
    }
}
