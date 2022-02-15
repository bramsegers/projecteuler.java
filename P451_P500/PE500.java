package P451_P500;

import java.util.BitSet;
import java.util.Map;
import java.util.TreeSet;
import util.Util;

public class PE500 {

    public static void main(String[] args) {
        //new PE500().test();
        new PE500().solve(500500, 500500507);
    }

    void solve(int emax, int mod) {

        // init primes and set of factors
        BitSet primes = Util.getPrimesBS(20 * emax);
        TreeSet<Factor> factors = new TreeSet<>();
        factors.add(new Factor(2, 1));
        int nextPrime = 3;

        // calculate
        for (int e = 1; e < emax; e++) {
            Factor f = factors.first();
            if (f.cost < nextPrime) {
                factors.pollFirst();
                factors.add(new Factor(f.p, (f.e << 1) + 1));
            } else {
                factors.add(new Factor(nextPrime, 1));
                nextPrime = primes.nextSetBit(++nextPrime);
            }
        }

        // collect result        
        long n = 1;
        for (Factor f : factors) {
            long prod = (long) Math.pow(f.p, f.e);
            prod %= mod;
            n *= prod;
            n %= mod;
        }
        System.out.println(n);
    }

    class Factor implements Comparable<Factor> {

        int p, e;
        long cost;

        public Factor(int p1, int e1) {
            p = p1;
            e = e1;
            cost = (long) Math.pow(p, e + 1);
        }

        @Override
        public int compareTo(Factor o) {
            return o.cost - cost < 0 ? 1 : -1;
        }

    }

    /*
     *          2    2  {2=1}
     *          6    4  {2=1, 3=1}
     *         24    8  {2=3, 3=1}
     *        120   16  {2=3, 3=1, 5=1}
     *        840   32  {2=3, 3=1, 5=1, 7=1}
     *       7560   64  {2=3, 3=3, 5=1, 7=1}
     *      83160  128  {2=3, 3=3, 5=1, 7=1, 11=1}
     *    1081080  256  {2=3, 3=3, 5=1, 7=1, 11=1, 13=1}
     *   17297280  512  {2=7, 3=3, 5=1, 7=1, 11=1, 13=1}
     *  294053760 1024  {2=7, 3=3, 5=1, 7=1, 11=1, 13=1, 17=1}
     */
    void test() {
        int max = 2000000;
        Util.initPrimes(max);
        int d = 2;
        for (int n = 1; n < max; n++) {
            Map<Integer, Integer> dec = Util.factorize(n);
            int divs = 1;
            for (int p : dec.keySet()) {
                divs *= dec.get(p) + 1;
            }
            if (divs == d) {
                System.out.println(n + " " + divs + " " + dec);
                d *= 2;
            }
        }
    }

}
