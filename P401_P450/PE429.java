package P401_P450;

import java.util.List;
import util.Util;

public final class PE429 {

    public static void main(String[] args) {
        new PE429().solve(100000000, 1000000009);
    }

    void solve(int fac, int mod) {
        long sum = 1;
        List<Integer> primes = Util.getPrimes(fac);
        for (int p : primes) {
            int power = factorsInFactorial(p, fac);
            sum *= 1 + powMod(p, power * 2, mod);
            sum %= mod;
        }
        System.out.format("P(%d,%d)=%d%n", fac, mod, sum);
    }

    int factorsInFactorial(int p, int fac) {
        return fac == 0 ? 0 : fac / p + factorsInFactorial(p, fac / p);
    }

    long powMod(int p, int pow, int mod) {
        long rv = 1;
        for (int i = 1; i <= pow; i++) {
            rv *= p;
            rv %= mod;
        }
        return rv;
    }

}
