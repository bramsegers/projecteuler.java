package P201_P250;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE203 {

    public static void main(String[] args) {
        new PE203().solve(51);
    }

    void solve(int nMax) {
        long sum = 0;
        Util.initPrimes(nMax - 1);
        List<Integer> pSq = new ArrayList<>();
        for (int p : Util.primes) {
            pSq.add(p * p);
        }
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < nMax; i++) {
            for (int j = 0; j <= i / 2; j++) {
                set.add(nCr(i, j));
            }
        }
        for (long n : set) {
            sum += satisfies(n, pSq) ? n : 0;
        }
        System.out.format("P(%d)=%d%n",nMax,sum); 
    }

    long nCr(int i, int j) {
        j = Math.min(j, i - j);
        BigInteger rv = BigInteger.ONE;
        for (int n = i; n > i - j; n--) {
            rv = rv.multiply(new BigInteger(String.valueOf(n)));
        }
        for (int n = 2; n <= j; n++) {
            rv = rv.divide(new BigInteger(String.valueOf(n)));
        }
        return Long.parseLong(rv.toString());
    }

    boolean satisfies(long n, List<Integer> pSq) {
        for (int p : pSq) {
            if (n % p == 0) {
                return false;
            }
        }
        return true;
    }

}
