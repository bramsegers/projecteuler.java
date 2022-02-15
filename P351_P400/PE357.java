package P351_P400;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import util.Util;

public class PE357 {

    public static void main(String[] args) {
        //new Test().test(10000);
        new PE357().solve(100000000);
    }

    void solve(int nMax) {
        long sum = 0;
        Util.initPrimes(nMax);
        BitSet bs = new BitSet(nMax);
        bs.flip(1, nMax);
        for (int d = 1; d <= nMax; d++) {
            for (int j = d; j <= nMax; j += d) {
                if (bs.get(j)) {
                    bs.set(j, Util.isPrime[d + (j / d)]);
                }
            }
        }
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
            sum += i;
        }
        System.out.println(sum);
    }

    void test(int nMax) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 1; i <= nMax; i++) {
            map.put(i, new ArrayList<Integer>());
        }
        for (int i = 1; i <= nMax; i++) {
            for (int j = i; j <= nMax; j += i) {
                map.get(j).add(i);
            }
        }
        long count = 0;
        for (int i : map.keySet()) {
            if (isValid(i, map.get(i))) {
                System.out.println(i + " " + map.get(i));
                count++;
            }
        }
        System.out.println(count);
    }

    boolean isValid(int n, List<Integer> div) {
        for (int d : div) {
            if (!Util.isPrime(d + (n / d))) {
                return false;
            }
        }
        return true;
    }

}
