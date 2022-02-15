package P501_P550;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import util.Util;

public class PE501 {

    /*
     197912312715
     BUILD SUCCESSFUL (total time: 41 minutes 29 seconds)
     */
    public static void main(String[] args) {
        new PE501().solve(1000000000000L);
    }

    List<Long> low = new ArrayList<>();
    List<Long> high = new ArrayList<>();

    long lo, hi, hi_max;
    int lo_i, hi_i, lo_size, hi_size;
    long tp = 0;

    private void solve(long limit) {

        // p1^7
        BitSet bs = Util.getPrimesBS((int) Math.pow(limit, 1D / 7));
        long sum = bs.cardinality();

        // p1*p2^3  
        bs = Util.getPrimesBS((int) Math.pow(limit / 2, 1D / 3));
        for (int p1 = bs.nextSetBit(0); p1 >= 0; p1 = bs.nextSetBit(p1 + 1)) {
            for (int p2 = bs.nextSetBit(p1 + 1); p2 >= 0 && 1L * p1 * p2 * p2 * p2 <= limit; p2 = bs.nextSetBit(p2 + 1)) {
                sum++;
            }
        }

        // p1^3*p2
        bs = Util.getPrimesBS((int) Math.pow(limit, 1D / 4));
        for (int p1 = bs.nextSetBit(0); p1 >= 0; p1 = bs.nextSetBit(p1 + 1)) {
            long min = p1;
            long max = limit / (p1 * p1 * p1);
            low.add(min);
            high.add(max);
        }

        // p1*p2*p3
        bs = Util.getPrimesBS((int) Math.sqrt(limit / 2));
        for (int p1 = bs.nextSetBit(0); p1 >= 0; p1 = bs.nextSetBit(p1 + 1)) {
            for (int p2 = bs.nextSetBit(p1 + 1); p2 >= 0 && 1L * p1 * p2 * p2 <= limit; p2 = bs.nextSetBit(p2 + 1)) {
                long min = p2;
                long max = limit / (p1 * p2);
                low.add(min);
                high.add(max);
            }
        }

        Collections.sort(low);
        Collections.sort(high);
        lo = low.get(0);
        hi = high.get(0);
        hi_max = high.get(high.size() - 1);
        lo_size = low.size();
        hi_size = high.size();

        int sqrt = (int) (0.5D + Math.sqrt(limit));
        BitSet primes = Util.getPrimesBS(sqrt);
        sum += calc(primes, 0);

        for (long i = sqrt; i < hi_max; i += sqrt) {

            BitSet primes2 = new BitSet(sqrt);
            primes2.set(0, sqrt);
            for (int j = primes.nextSetBit(0); j >= 0; j = primes.nextSetBit(j + 1)) {
                int d = (int) (((j - (i % j))) % j);
                for (int c = d; c < sqrt; c += j) {
                    primes2.clear(c);
                }
            }
            if (lo > i + sqrt && hi > i + sqrt) {
                tp += primes2.cardinality();
            } else {
                sum += calc(primes2, i);
            }
            System.out.println((i / sqrt) + "/" + (hi_max / sqrt) + " " + sum);
        }
        System.out.println(sum);
    }

    long calc(BitSet primes, long offset) {
        long rv = 0;
        for (int i = primes.nextSetBit(0); i >= 0; i = primes.nextSetBit(i + 1)) {
            long p = offset + i;
            tp++;
            while (p > lo) {
                rv -= tp;
                lo = (++lo_i == lo_size) ? Long.MAX_VALUE : low.get(lo_i);
            }
            while (p > hi) {
                rv += tp;
                hi = (++hi_i == hi_size) ? Long.MAX_VALUE : high.get(hi_i);
            }
        }
        return rv;
    }

}
