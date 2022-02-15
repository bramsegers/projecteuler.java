package P251_P300;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/*
 https://en.wikipedia.org/wiki/Practical_number
 */
public class PE263 {

    public static void main(String[] args) {
        new PE263().solve();
    }

    long count = 4, sum = 0;
    BitSet primes;

    void solve() {
        long limit = 1000000000000L;
        int sqrt = (int) (0.5D + Math.sqrt(limit));
        int off = 18;
        primes = getPrimesBS(sqrt + off);
        searchEngineersParadise(primes, 0, sqrt);
        for (long i = sqrt; i < limit; i += sqrt) {
            BitSet primes2 = new BitSet();
            primes2.set(0, sqrt + off);
            for (int j = primes.nextSetBit(0); j >= 0; j = primes.nextSetBit(j + 1)) {
                int d = (int) (((j - (i % j))) % j);
                for (int c = d; c < sqrt + off; c += j) {
                    primes2.clear(c);
                }
            }
            searchEngineersParadise(primes2, i, sqrt);
        }
    }

    BitSet getPrimesBS(int n) {
        BitSet bs = new BitSet();
        bs.set(2, n + 1);
        for (int i = 2; i * i <= n; i++) {
            if (bs.get(i)) {
                for (int j = i; i * j <= n; j++) {
                    bs.clear(i * j);
                }
            }
        }
        return bs;
    }

    void searchEngineersParadise(BitSet p, long offset, int sqrt) {
        for (int i = p.nextSetBit(0); i >= 0 && i < sqrt; i = p.nextSetBit(i + 1)) {
            if (isSexyTriplet(i, p)) {
                int n = i + 9;
                long no = offset + n;
                if (isPractical(no - 8) && isPractical(no - 4) && isPractical(no) && isPractical(no + 4) && isPractical(no + 8)) {
                    sum += no;
                    System.out.format("PT: %d,%d,%d,%d    %n", no - 9, no - 3, no + 3, no + 9);
                    System.out.format("PN: %d,%d,%d,%d,%d %n", no - 8, no - 4, no + 0, no + 4, no + 8);
                    System.out.format("Σ EP = %d%n%n", sum);
                    if (--count == 0) {
                        System.exit(0);
                    }
                }
            }
        }
    }

    boolean isSexyTriplet(int i, BitSet p) {
        int j = i;
        for (int k = 0; k < 3 && p.nextSetBit(j + 1) == j + 6; k++) {
            j += 6;
        }
        return j == i + 18;
    }

    boolean isPractical(long n) {
        if (n != 1 && (n & 1) == 1) {
            return false;
        }
        List<Long> prm = new ArrayList<>();
        List<Integer> exp = new ArrayList<>();
        List<Long> divsum = new ArrayList<>();
        int sqrt = (int) Math.sqrt(n);
        int p = primes.nextSetBit(0);
        while (n > 1 && p <= sqrt && p > 0) {
            int pe = 0;
            while (n % p == 0) {
                pe++;
                n /= p;
            }
            if (pe > 0) {
                prm.add((long) p);
                exp.add(pe);
            }
            p = primes.nextSetBit(p + 1);
        }
        if (n > 1) {
            prm.add(n);
            exp.add(1);
        }
        for (int i = 0; i < prm.size() - 1; i++) {
            long pi = prm.get(i);
            int ei = exp.get(i);
            divsum.add((long) ((Math.pow(pi, ei + 1) - 1) / (pi - 1)));
        }
        long dsprod = 1;
        for (int i = 0; i < divsum.size(); i++) {
            long pi = prm.get(i + 1);
            dsprod *= divsum.get(i);
            if (pi > 1 + dsprod) {
                return false;
            }
        }
        return true;
    }

}
