package P501_P550;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE516 {

    public static void main(String[] args) {
        new PE516().solve(1000000000000L, 1L << 32);
    }

    long max, sum, mod;
    List<Long> smooth = new ArrayList<>();
    List<Long> primes = new ArrayList<>();

    void solve(long n, long m) {

        max = n;
        mod = m;
        getSmoothNums(1, 0, new int[]{2, 3, 5});
        Collections.sort(smooth);
        Collections.sort(primes);
        System.out.println("smooth numbers: " + smooth);
        System.out.println("primes with smooth predecessor: " + primes);

        int i = 0;
        for (long s : smooth) {
            System.out.format("%d/%d%n", ++i, smooth.size());
            getSmoothPhi(s, 0);
        }
        System.out.format("P(%d) mod %d = %d%n", max, mod, sum);
    }

    void getSmoothNums(long cur, int ci, int[] p) {
        if (cur > max) {
            return;
        }
        smooth.add(cur);
        if (cur > p[p.length - 1] && cur < max && nextIsPrime(cur)) {
            primes.add(cur + 1);
        }
        for (int i = ci; i < p.length; i++) {
            getSmoothNums(cur * p[i], i, p);
        }
    }

    void getSmoothPhi(long cur, int ci) {
        if (ci == primes.size()) {
            sum = (sum + cur) % mod;
            return;
        }
        getSmoothPhi(cur, ci + 1);
        if (primes.get(ci) <= max / cur) {
            getSmoothPhi(cur * primes.get(ci), ci + 1);
        }
    }

    boolean nextIsPrime(long n) {
        BigInteger b = new BigInteger(Long.toString(n + 1));
        return b.isProbablePrime(100);
    }
}
