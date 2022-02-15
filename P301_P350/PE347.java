package P301_P350;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE347 {

    public static void main(String[] args) {
        new PE347().solve(10000000);
    }

    void solve(int nMax) { 
        List<Integer> primes = Util.getPrimes(nMax);
        Set<Integer> set = new HashSet<>();
        int p, q;
        for (int i = 0; i < primes.size(); i++) {
            p = primes.get(i);
            for (int j = i + 1; j < primes.size() && p * (q = primes.get(j)) <= nMax; j++) {
                set.add(largest(p, q, nMax));
            }
        }
        long sum = 0;
        for (int s : set) {
            sum += s;
        }
        System.out.println(sum);
    }

    int largest(int p, int q, int lim) {
        long max = 0;
        for (long i = p; i <= lim; i *= p) {
            for (long j = q; i * j <= lim; j *= q) {
                max = Math.max(i * j, max);
            }
        }
        return (int) max;
    }

}
