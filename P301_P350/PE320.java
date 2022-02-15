package P301_P350;

import util.Primes;

public class PE320 {

    public static void main(String[] args) {
        new PE320().solve(1000000, (long) 1E18);
    }

    void solve(long u, long mod) {

        long sum = 0;
        long max = 0;
        long exp = 1234567890L;
        Primes primes = new Primes(u);

        for (long i = 10; i <= u; i++) {
            for (long f : primes.factors(i)) {
                long ff = factors(f, i);
                long min_n = min_n(f, exp * ff);
                max = Math.max(max, min_n);
            }
            sum += max;
            sum %= mod;
        }

        System.out.println(sum);
    }

    long min_n(long F, long FF) {
        long min_n = upper(F, FF);
        long lower = min_n / 2;
        long mid = (lower + min_n) / 2;
        while (min_n - lower > 1) {
            if (factors(F, mid) >= FF) {
                min_n = mid;
            } else {
                lower = mid;
            }
            mid = (lower + min_n) / 2;
        }
        return min_n;
    }

    long upper(long F, long FF) {
        long n = 1;
        long factors = 0;
        while (factors < FF) {
            n *= 2;
            factors = factors(F, n);
        }
        return n;
    }

    long factors(long f, long factorial) {
        long d = factorial / f;
        return d == 0 ? 0 : d + factors(f, d);
    }

}
