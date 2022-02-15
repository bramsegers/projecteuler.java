package P251_P300;

import java.math.BigInteger;
import java.util.List;
import java.util.TreeSet;
import util.Util;

public class PE266 {

    public static void main(String[] args) {
        new PE266().solve(190, 10000000000000000L);
    }

    List<Integer> primes;
    BigInteger SQR, PSR;

    void solve(int pMax, long mod) {

        // init
        TreeSet<BigInteger> set1 = new TreeSet<>();
        TreeSet<BigInteger> set2 = new TreeSet<>();
        primes = Util.getPrimes(pMax);
        BigInteger P = BigInteger.ONE;
        for (int p : primes) {
            P = P.multiply(BigInteger.valueOf(p));
        }
        SQR = Util.sqrt(P);
        PSR = BigInteger.ZERO;
        System.out.print("SQR=" + SQR + "\nPSR=");

        // divide in 2 subsets
        int size = primes.size() / 2;
        for (int t = 1; t < (1 << size); t++) {
            addToSet(set1, t, 0);
            addToSet(set2, t, size);
        }

        // check max
        for (BigInteger b : set1) {
            BigInteger t1 = SQR.divide(b);
            BigInteger t2 = set2.floor(t1);
            if (t2 != null) {
                BigInteger psr = b.multiply(t2);
                if (psr.compareTo(PSR) > 0) {
                    PSR = psr;
                }
            }
        }
        
        // show result
        BigInteger M = PSR.mod(BigInteger.valueOf(mod));
        System.out.format("%s%nP(%d)=%d%n", PSR, pMax, M);
    }

    void addToSet(TreeSet<BigInteger> set, int bin, int offset) {
        BigInteger P = BigInteger.ONE;
        int i = offset;
        while (bin > 0) {
            if ((bin & 1) == 1) {
                P = P.multiply(BigInteger.valueOf(primes.get(i)));
                if (P.compareTo(SQR) > 0) {
                    return;
                }
            }
            bin >>= 1;
            i++;
        }
        if (P.compareTo(PSR) > 0) {
            PSR = P;
        }
        set.add(P);
    }

}
