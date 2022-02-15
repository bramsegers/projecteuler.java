package P251_P300;
 
import java.util.ArrayList;
import java.util.List;
import util.Util;

public class PE273 {

    /*
     * Info: http://en.wikipedia.org/wiki/Proofs_of_Fermat's_theorem_on_sums_of_two_squares
     *       http://en.wikipedia.org/wiki/Brahmagupta%E2%80%93Fibonacci_identity
     *
     * p1 = a^2 + b^2  => 1 solution
     * p2 = c^2 + d^2  => 1 solution
     * p1p2 = (a^2+b^2)(c^2+d^2) = (ac-bd)^2 + (ad+bc)^2
     *                           = (ac+bd)^2 + (ad-bc)^2
     *
     * 2 primes=> 2 solutions
     * 3 primes=> 4 solutions
     * 4 primes=> 8 solutions
     * etc
     */
    public static void main(String[] args) {
        new PE273().solve(150);
    }

    void solve(int nMax) {
        // init 
        List<SumOfSq> primes = new ArrayList<>();
        for (int p = 1; p < nMax; p += 4) {
            if (Util.isPrime(p)) {
                for (int a = 1; a * a <= p; a++) {
                    for (int b = a + 1; a * a + b * b <= p; b++) {
                        if (a * a + b * b == p) {
                            primes.add(new SumOfSq(a, b));
                        }
                    }
                }
            }
        }

        // factor p1*p2*.....*pn
        long sum = 0;
        for (int i = 1; i < (1 << primes.size()); i++) {
            List<SumOfSq> list = null;
            for (int j = 0; j < primes.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    list = add(list, primes.get(j));
                }
            }
            for (SumOfSq s : list) {
                sum += s.A;
            }
        }

        // show result
        System.out.format("P(%d)=%d%n", nMax, sum);
    }

    List<SumOfSq> add(List<SumOfSq> list, SumOfSq s2) {
        List<SumOfSq> list2 = new ArrayList<>();
        if (list == null) {
            list2.add(s2);
            return list2;
        }
        for (SumOfSq s : list) {
            list2.add(s.mult1(s2));
            list2.add(s.mult2(s2));
        }
        return list2;
    }

    static class SumOfSq {

        long A, B, val;

        SumOfSq(long a, long b) {
            A = a;
            B = b;
            val = A * A + B * B;
        }

        SumOfSq mult1(SumOfSq s) {
            long a = Math.abs(A * s.A - B * s.B);
            long b = A * s.B + B * s.A;
            return new SumOfSq(Math.min(a, b), Math.max(a, b));
        }

        SumOfSq mult2(SumOfSq s) {
            long a = A * s.A + B * s.B;
            long b = Math.abs(A * s.B - B * s.A);
            return new SumOfSq(Math.min(a, b), Math.max(a, b));
        }

        @Override
        public String toString() {
            return A + "^2+" + B + "^2";
        }
    }

}
