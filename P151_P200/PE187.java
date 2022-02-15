package P151_P200;

import util.Util;

public class PE187 {

    public static void main(String[] args) {
        new PE187().solve(100000000);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long count = 0;
        Util.initPrimes(nMax);
        for (int i = 0; i < Util.primes.size(); i++) {
            for (int j = i; j < Util.primes.size(); j++) {
                if ((long) Util.primes.get(i) * (long) Util.primes.get(j) >= nMax) {
                    break;
                }
                count++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, count, end - start);
    }

}
