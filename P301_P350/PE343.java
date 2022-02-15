package P301_P350;

import util.Util;

public class PE343 {

    /*
     * 1. test() => a(n) = lpf(n+1)-1 (https://oeis.org/A070777)
     * 2. n^3+1 = (n+1)(n^2-n+1)   
     */
    public static void main(String[] args) {
        //new PE343().test(100);
        new PE343().solve(2000000);
    }

    void solve(int size) {
        long[] f1 = new long[size + 1];
        long[] f2 = new long[size + 1];
        for (int k = 1; k <= size; k++) {
            f1[k] = k + 1;
            f2[k] = 1L * k * k - k + 1;
        }
        f1 = getLargestFactors(f1);
        f2 = getLargestFactors(f2);
        long s = 0;
        for (int k = 1; k <= size; k++) {
            long p = Math.max(f1[k], f2[k]);
            s += p - 1;
        }
        System.out.println(s);
    }

    long[] getLargestFactors(long[] f) {
        long[] lpf = new long[f.length];
        for (int i = 1; i < f.length; i++) {
            long p = f[i];
            for (long j = i; p > 1 && j < f.length; j += p) {
                int k = (int) j;
                lpf[k] = Math.max(lpf[k], p);
                while (f[k] % p == 0) {
                    f[k] /= p;
                }
            }
        }
        return lpf;
    }

    void test(long max) {
        for (long n = 1; n <= max; n++) {
            long p = 1, q = n;
            while (q > 1) {
                long gcd = Util.gcd(++p, --q);
                p /= gcd;
                q /= gcd;
            }
            System.out.println(n + " " + p);
        }
    }

}
