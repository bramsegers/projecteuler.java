package P201_P250;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE248 {

    public static void main(String[] args) {
        new PE248().solve(13, 150000);
    }

    List<Long> candidates = new ArrayList<>();
    List<Long> solutions = new ArrayList<>();

    void solve(int f, int pos) {

        searchCandidates(1, 2, f);
        Collections.sort(candidates);

        searchSolutions(1, fac(f), candidates.size());
        Collections.sort(solutions);

        System.out.println(solutions.get(pos - 1));

    }

    void searchCandidates(long n, long p, long f) {
        if (p > f && isPrime(n + 1)) {
            candidates.add(n + 1);
        }
        for (int i = 0; p <= f && i <= factors(p, f); i++) {
            searchCandidates(n, nextPrime(p), f);
            n *= p;
        }
    }

    void searchSolutions(long s, long left, int size) {
        if (left == 1) {
            solutions.add(s);
        }
        for (int i = 0; i < size; i++) {
            long p = candidates.get(i);
            if (left % (p - 1) == 0) {
                long f2 = left / (p - 1);
                long pe = 1;
                while (f2 % pe == 0) {
                    searchSolutions(s * p * pe, f2 / pe, i);
                    pe *= p;
                }
            }
        }
    }

    long fac(long f) {
        return f < 2 ? 1 : f * fac(f - 1);
    }

    long factors(long p, long fac) {
        return fac == 0 ? 0 : (fac / p + factors(p, fac / p));
    }

    boolean isPrime(long n) {
        return BigInteger.valueOf(n).isProbablePrime(100);
    }

    long nextPrime(long n) {
        return isPrime(n + 1) ? n + 1 : nextPrime(n + 1);
    }

}
