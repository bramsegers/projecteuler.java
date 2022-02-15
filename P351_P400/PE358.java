package P351_P400;

import java.util.BitSet;
import util.Util;

/*
 * https://www.youtube.com/watch?v=WUlaUalgxqI
 * https://oeis.org/A006883
 * cyclic number is of form:  ( 10^(p-1) -1 ) / p 
 */
public class PE358 {

    public static void main(String[] args) {
        new PE358().solve();
    }

    private void solve() {
        System.out.println("Sieving primes");
        BitSet primes = Util.getPrimesBS(1000000000);
        System.out.println("Matching digits");
        for (int p = primes.nextSetBit(1); p >= 0; p = primes.nextSetBit(p + 1)) {
            if ((56789L * p + 1) % 100000L == 0L
                    && (1D / p) > 0.00000000137
                    && (1D / p) < 0.00000000138) {
                long mo = multiplicativeOrder(10, p);
                System.out.println("p          = " + p);
                System.out.println("MO(10,p)   = " + mo);
                System.out.println(mo == p - 1 ? "Cyclic sum = " + cyclicSum(p) : "");
            }
        }
    }

    /*
     https://reference.wolfram.com/language/ref/MultiplicativeOrder.html
     */
    long multiplicativeOrder(long k, long p) {
        long n = 1, m = k;
        while (m % p != 1) {
            m *= k;
            m %= p;
            n++;
            if (n == p) {
                return -1;
            }
        }
        return n;
    }

    long cyclicSum(int p) {
        long sum = 0;
        long n = p - 1;
        long cur = 0;
        while (n > 0) {
            while (cur < p) {
                cur *= 10;
                cur += 9;
                n--;
            }
            if (n >= 0) {
                long d = cur / p;
                sum += d;
                cur %= p;
            }
        }
        return sum;
    }

}
