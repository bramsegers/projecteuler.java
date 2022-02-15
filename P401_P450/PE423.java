package P401_P450;

import java.util.BitSet;
import util.Util;

public class PE423 {

    public static void main(String[] args) {
        new PE423().solve(50000000, 1000000007);
        System.out.println(653972374);
    }

    long last_mod1, last_mod2, last_n, last_r;

    void solve(int N, long M) {
        long sum1 = 0;
        long sum2 = 0;
        long c6 = 1;
        long c5 = 1;
        long pi = 0;
        int diff = 1;
        BitSet primes = Util.getPrimesBS(N);
        for (int n = 1; n <= N; n++) {
            pi += primes.get(n) ? 1 : 0;
            sum1 += c6;
            c6 = (6 * c6) % M;
            if (n - pi > diff) {
                long subtr1 = choosemod1(N, diff, M);
                subtr1 += M;
                subtr1 -= choosemod2(n - 1, diff, M);
                subtr1 *= c5;
                subtr1 %= M;
                sum2 += subtr1;
                sum2 %= M;
                c5 = (5 * c5) % M;
                diff++;
            }
        }
        sum1 = sum1 + M - sum2;
        System.out.println((6 * sum1) % M);
    }

    long choosemod1(int n, int r, long mod) {
        if (r == 1) {
            last_mod1 = n;
        } else {
            last_mod1 *= n - r + 1;
            last_mod1 %= mod;
            last_mod1 *= invmod(r, mod);
            last_mod1 %= mod;
        }
        return last_mod1;
    }

    long choosemod2(int n, int r, long mod) {
        if (r == 1) {
            last_mod2 = n;
        } else {
            for (long ni = last_n + 1; ni <= n; ni++) {
                last_mod2 *= ni;
                last_mod2 %= mod;
            }
            for (long i = last_r + 1; i <= r; i++) {
                last_mod2 *= invmod(i, mod);
                last_mod2 %= mod;
            }
            long len = (n - last_n) - (r - last_r);
            for (long i = 0; i < len; i++) {
                long a = n - r - i;
                last_mod2 *= invmod(a, mod);
                last_mod2 %= mod;
            }
        }
        last_n = n;
        last_r = r;
        return last_mod2;
    }

    long invmod(long k, long m) {
        boolean neg = true;
        long p1 = 1, p2 = 0, k1 = k, m1 = m, q, r, temp;
        while (k1 > 0) {
            q = m1 / k1;
            r = m1 % k1;
            temp = q * p1 + p2;
            p2 = p1;
            p1 = temp;
            m1 = k1;
            k1 = r;
            neg = !neg;
        }
        return neg ? m - p2 : p2;
    }

}
