package P201_P250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import util.Util;

public class PE224 {

    /*
     * Info: http://www.gutenberg.org/files/20073/20073-pdf.pdf?session_id=26707f9635cdfd3cbf18e4c3b32a09fb365867f4
     * Page 31:
     *
     * x^2 + y^2 + z^2 = t^2
     *  
     * t = m^2 + n^2 + p^2 + q^2
     * x = m^2 - n^2 - p^2 + q^2
     * y = 2mn - 2pq
     * z = 2mp + 2nq
     *
     * Let x=1:
     *    (q-p)(q+p) = R = 1 - m^2 + n^2
     * or (p-q)(p+q) = -R if R<0 
     * 
     * => factor R
     * => for each (q-p)(q+p)=f1*f2 (f1<f2, f1+f2 is even):
     *             q-p=f1, q+p=f2
     *             q = (f1+f2)/2
     *             p = f2-q
     * 
     * (a,b,c) = sort(t,y,z)
     * check  (a,b,c) is unique, a+b+c<=max
     *
     * P(75000000)=4137330
     * BUILD SUCCESSFUL (total time: 2 minutes 21 seconds)
     */
    public static void main(String[] args) {
        //new Test().test(10000);
        new PE224().solve(75000000);
    }

    void solve(int nMax) {

        // init
        Util.initPrimes(nMax + 1);
        List<Integer> list = new ArrayList<>();
        long[] abc = new long[3];
        Set<String> sol = new TreeSet<>();
        sol.add("[2, 2, 3]");

        // iterate over n,m
        for (long m = 0; m * m <= nMax; m++) {
            System.out.println(m * m);
            for (long n = 0; m * m + n * n <= nMax; n++) {
                long r = 1 - m * m + n * n;
                boolean neg = r < 0;
                r = r < 0 ? -r : r;
                Map<Integer, Integer> map = Util.factorize((int) r);
                list.clear();
                int t = 1;
                for (int k : map.keySet()) {
                    int e = map.get(k);
                    list.add(e);
                    t *= e + 1;
                }
                int[] exponents = new int[list.size()];
                // iterate over factors of r
                for (int i = 0; i < t / 2; i++) {
                    long f = 1;
                    int j = 0;
                    for (int k : map.keySet()) {
                        int e = exponents[j++];
                        for (int p = 0; p < e; p++) {
                            f *= k;
                        }
                    }
                    long f1 = Math.min(f, r / f);
                    long f2 = Math.max(f, r / f);
                    if (((f1 + f2) & 1) == 0) {
                        long q = (f1 + f2) / 2;
                        long p = f2 - q;
                        if (neg) {
                            long tmp = q;
                            q = p;
                            p = tmp;
                        }
                        abc[0] = m * m + n * n + p * p + q * q;
                        abc[1] = Math.abs(2 * m * n - 2 * p * q);
                        abc[2] = 2 * m * p + 2 * n * q;
                        Arrays.sort(abc);
                        long a = abc[0];
                        long b = abc[1];
                        long c = abc[2];
                        if (a + b + c <= nMax) {
                            sol.add(Arrays.toString(abc));
                        }
                    }
                    next(list, exponents);
                }

            }
        }

        // show result
        System.out.format("P(%d)=%d%n", nMax, sol.size());
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

    void test(int nMax) {
        long count = 0;
        int limit = (nMax / 3) + 1;
        for (int a = 1; a <= limit; a++) {
            for (int b = a; a + b <= nMax; b++) {
                int c2 = a * a + b * b + 1;
                int c = (int) Math.sqrt(c2);
                if (c * c == c2 && a <= b && b <= c && a + b + c <= nMax) {
                    System.out.println(a + "," + b + "," + c);
                    count++;
                }
            }
        }
        System.out.println(nMax + " " + count);
    }
}
