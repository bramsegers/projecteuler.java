package P301_P350;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE304 {

    /*
     http://oeis.org/A001175
     */
    public static void main(String[] args) {
        new PE304().solve(100000000000000L, 100000L, 1234567891011L);
    }

    void solve(long pMin, long pLen, long mod) {
        long count = 0;

        System.out.print("Pisano period: ");
        long period = pisanoPeriod(mod);
        System.out.println(period);

        System.out.print("Generating primes: ");
        List<Long> primes = new ArrayList<>();
        pMin += (pMin % 2 == 0) ? 1 : 0;
        BigInteger n = new BigInteger(String.valueOf(pMin));
        BigInteger two = new BigInteger("2");
        for (long i = 0; i < pLen; i++) {
            while (!n.isProbablePrime(10)) {
                n = n.add(two);
            }
            long p = n.longValue() % period;
            if (p < 2) {
                count += p;
            } else {
                primes.add(p);
            }
            n = n.add(two);
        }
        Collections.sort(primes);
        System.out.println("done");

        System.out.print("Fibonacci sum: ");
        count += fibonacci(primes, period, mod);
        System.out.println(count);
    }

    long pisanoPeriod(long mod) {
        long f, f1 = 0, f2 = 1, c = 2;
        while (true) {
            f = (f1 + f2) % mod;
            if (f == 1 && f2 == 0) {
                return c - 1;
            }
            f1 = f2;
            f2 = f;
            c++;
        }
    }

    long fibonacci(List<Long> nums, long period, long mod) {
        long sum = 0;
        long f1 = 0, f2 = 1, f = 1;
        for (long c = 2;; c++) {
            f = (f1 + f2) % mod;
            while (!nums.isEmpty() && c == nums.get(0)) {
                sum = (sum + f) % mod;
                nums.remove(0);
                if (nums.isEmpty()) {
                    return sum;
                }
            }
            f1 = f2;
            f2 = f;
        }
    }

}
