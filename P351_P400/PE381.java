package P351_P400;

import java.util.List;
import util.Util;

public class PE381 {

    public static void main(String[] args) {
        new PE381().solve(100000000);
    }

    void solve(int m) {
        long sum = 0;
        List<Integer> primes = Util.getPrimes(m);
        primes.remove(Integer.valueOf(2));
        primes.remove(Integer.valueOf(3));
        for (int p : primes) {
            int a1 = p - 1;
            int a2 = 1;
            int a3 = p / 2;
            int a4 = (int) (p + (euclidean(p - 3, p)[0] * a3) % p);
            int a5 = (int) (p + (euclidean(p - 4, p)[0] * a4) % p);
            int modSum = (a1 + a2 + a3 + a4 + a5) % p;
            //System.out.format("%d - %d,%d,%d,%d,%d -> %d %n", p, a1, a2, a3, a4, a5, modSum);
            sum += modSum;
        }
        System.out.println(sum);
    }

    long[] euclidean(long a, long b) {
        if (b > a) {
            long[] coeffs = euclidean(b, a);
            long[] output = {coeffs[1], coeffs[0]};
            return output;
        }
        long q = a / b;
        long r = a - q * b;
        if (r == 0) {
            long[] output = {0, 1};
            return output;
        }
        long[] next = euclidean(b, r);
        long[] output = {next[1], next[0] - q * next[1]};
        return output;
    }

}
