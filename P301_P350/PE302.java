package P301_P350;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

public class PE302 {

    // 1. if gcd(m, n) = 1, then φ(m*n) = φ(m) * φ(n)
    // 2. highest factor of S must be  ≥ 3
    // 3. φ(p^k) = p^(k−1) * (p − 1)
    public static void main(String[] args) {
        new PE302().solve((long) 1E18);
    }

    List<Integer> primes;
    int count;

    void solve(long max) {
        int maxPrime = (int) Math.cbrt(max);
        primes = Util.getPrimes(maxPrime); // simple Eratosthenes sieve
        for (int i = primes.size() - 1; i >= 0; i--) {
            int p = primes.get(i);
            int e = 3;
            long prod = 1L * p * p;
            while (prod < max / p) { // avoid overflow
                prod *= p;
                Map<Integer, Integer> S = new HashMap<>();
                S.put(p, e++);
                solve(i, prod, max, S);
            }
        }
        System.out.format("P(%d)=%d%n", max, count);
    }

    void solve(int pi, long prod, long max, Map<Integer, Integer> S) {
        count += (isAchilles(S) && isAchilles(phi(S))) ? 1 : 0;
        for (int i = pi - 1; i >= 0; i--) {
            int p = primes.get(i);
            int e = 1;
            if (prod < max / p) {
                long prod2 = prod * p;
                while (prod2 < max / p) { // avoid overflow
                    prod2 *= p;
                    Map<Integer, Integer> S2 = new HashMap<>(S);
                    S2.put(p, ++e);
                    solve(i, prod2, max, S2);
                }
            }
        }
    }

    Map<Integer, Integer> phi(Map<Integer, Integer> S) {
        Map<Integer, Integer> phi = new HashMap<>();
        for (int f : S.keySet()) {
            Integer i = phi.get(f);
            i = (i == null) ? 0 : i;
            phi.put(f, i + S.get(f) - 1);
            Map<Integer, Integer> dec = Util.factorize(f - 1); // needs sieve
            for (int d : dec.keySet()) {
                Integer j = phi.get(d);
                j = (j == null) ? 0 : j;
                phi.put(d, j + dec.get(d));
            }
        }
        return phi;
    }

    boolean isAchilles(Map<Integer, Integer> S) {
        int gcd = 0;
        for (int f : S.keySet()) {
            int e = S.get(f);
            if (e == 1) {
                return false; // must be powerful
            }
            gcd = (gcd == 0) ? e : Util.gcd(gcd, e); // Euclides, recursive
        }
        return gcd == 1; // must not be perfect power
    }

}
