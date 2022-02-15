package P151_P200;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Util;

public class PE200 {

    public static void main(String[] args) {
        PE200 t = new PE200();
        t.solve(200);
    }

    void solve(int n) {
        long start = System.currentTimeMillis();
        long max = 1000000000000L; // just a wild guess :s
        long pMax = (long) Math.pow(max / 8, 1D / 2);
        long qMax = (long) Math.pow(max / 4, 1D / 3);
        List<Long> scubes = new ArrayList<>();
        Util.initPrimes((int) pMax);
        long p = 0;
        for (int i = 0; i < Util.primes.size() && p < pMax; i++) {
            long q = 0;
            for (int j = 0; j < Util.primes.size() && q < qMax; j++) {
                p = Util.primes.get(i);
                q = Util.primes.get(j);
                if (!(p == 2 || p == 5 || q == 2 || q == 5)) {
                    // Testen wijst uit dat (p,q) = (2,n),(5,n),(n,2) of (n,5)
                    continue;
                }
                long scube = p * p * q * q * q;
                if (contains200(scube) && isPrimeproof(scube)) {
                    scubes.add(scube);
                    Collections.sort(scubes);
                    if (scubes.size() > n) {
                        scubes.remove(n);
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", n, scubes.get(n - 1), end - start);
    }

    boolean contains200(long scube) {
        while (scube > 100) {
            if (scube % 1000 == 200) {
                return true;
            }
            scube /= 10;
        }
        return false;
    }

    boolean isPrimeproof(long scube) {
        String s = String.valueOf(scube);
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 10; j++) {
                if (s.charAt(i) - '0' != j) {
                    String ss = s.substring(0, i) + j + s.substring(i + 1);
                    if (new BigInteger(ss).isProbablePrime(10)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
