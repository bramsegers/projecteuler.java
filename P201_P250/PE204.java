package P201_P250;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PE204 {

    public static void main(String[] args) {
        new PE204().solve(100, 1000000000);
    }

    void solve(int pMax, int nMax) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= pMax; i++) {
            if (!new String(new char[i]).matches(".?|(..+?)\\1+")) {
                primes.add(i);
            }
        }
        long start = System.currentTimeMillis();
        long count = 1 + count(primes, nMax, 0, primes.size());
        long end = System.currentTimeMillis();
        System.out.format("P(%d,%d)=%d%nElapsed:%dms%n", pMax, nMax, count, end - start);
    }

    long count(List<Integer> primes, int nMax, int offset, int length) {
        if (offset == primes.size() || length == 0) {
            return 0;
        }
        int base = primes.get(offset);
        int eMax = (int) (Math.log(nMax) / Math.log(base));
        BitSet bits = new BitSet();
        long rv = eMax;
        for (int r = eMax - 1; r >= 0; r--) {
            int len = 0;
            for (int c = 0; c < length - 1; c++) {
                int i = (length - 1) * r + c;
                if (bits.get(i + length - 1)) {
                    bits.set(i);
                } else {
                    bits.set(i, primes.get(c + offset + 1) <= nMax / Math.pow(base, r));
                }
                len += bits.get(i) ? 1 : 0;
            }
            rv += count(primes, nMax / (int) Math.pow(base, r), offset + 1, len);
        }
        return rv;
    }

}
