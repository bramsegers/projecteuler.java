package P201_P250;

import java.math.BigInteger;
import java.util.BitSet;
import util.Util;


/*
 * Info: http://math.stackexchange.com/questions/403519/primality-of-the-numbers-in-the-form-of-2n2-1
 * Todo: solve b for 2b^2 ≡ 1(mod p)
 * 
 * P(50000000)=5437849
 * BUILD SUCCESSFUL (total time: 2 minutes 25 seconds)
 */
public class PE216 {

    public static void main(String[] args) {
        new PE216().solve(50000000);
    }

    void solve(int nMax) {
        long count = 0;
        BitSet bs = new BitSet(nMax + 1);
        Util.initPrimes((int) Math.sqrt(nMax));
        for (int p : Util.primes) {
            if (p % 8 == 1 || p % 8 == 7) {
                int b = 1;
                for (int i = 0; i < 2; i++) {
                    while (2 * b * b % p != 1) {
                        b++;
                    }
                    int c, k = 1;
                    while ((c = p * k + b) <= nMax) {
                        bs.set(c);
                        k++;
                    }
                    b++;
                }
            }
        }
        for (int n = 2; n <= nMax; n++) {
            count += !bs.get(n) && new BigInteger(String.valueOf(2L * n * n - 1)).isProbablePrime(4) ? 1 : 0;
        }
        System.out.format("P(%d)=%d%n", nMax, count);
    }

}
