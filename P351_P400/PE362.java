package P351_P400;

import util.Primes;

public class PE362 {

    // Jonathan Paulson's thread (page 2, 1st thread)
    public static void main(String[] args) {
        new PE362().solve((long) 1E10);
    }

    Primes pr;
    long[] sqfree;

    void solve(long m) {
        int sqm = (int) Math.sqrt(m);
        pr = new Primes(sqm);
        sqfree = new long[sqm + 1];
        for (int i = 1; i <= sqm; i++) {
            sqfree[i] = i - excl(i, 1, 1, -1);
        }
        System.out.format("P(%d)=%d%n", m, f(m, 2));
    }

    long f(long n, long start) {
        long sum = (n < sqfree.length)
                ? sqfree[(int) n]
                : n - excl(n, 1, 1, -1);
        sum -= sqfree[(int) start - 1];
        for (long k = start; k * k <= n; k++) {
            if (sqfree[(int) k] - sqfree[(int) k - 1] == 1) {
                sum += f(n / k, k);
            }
        }
        return sum;
    }

    long excl(long m, long p, long q, int sign) {
        long q2, rv = 0;
        while ((p = pr.next(p)) >= 0 && (q2 = q * p) <= Math.sqrt(m)) {
            rv += excl(m, p, q2, -sign);
            rv -= sign * m / (q2 * q2);
        }
        return rv;
    }

}
