package P201_P250;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import util.Util;

/*
 * test() => https://oeis.org/A046109
 *        => a(n) = 8*A046080(n) + 4
 *        => https://oeis.org/A046080
 *
 *        http://mathworld.wolfram.com/PythagoreanTriple.html
 *        => A046080(n) = 1/2( (2b1+1)*(2b2+1)*...*(2br+1) -1 ) 
 *        
 *        a(n)=420 => (2b1+1)*(2b2+1)*...*(2br+1)=105=3*5*7
 *        105=3*5*7 => b1,b2,b3 = 1,2,3  ok
 *        105=5*21  => b1,b2    = 2,10   ok
 *        105=7*15  => b1,b2    = 3,7    ok
 *        105=105   => b1       = 157    >MAX
 *        105=3*35  => b1,b2    = 1,17   >MAX
 *
 *        q1 * 13^2 * 5^3 <=10^11  => q1 max =  4733727
 *        17 * 13^2 * 5^3 = 359125 => p max = 278454
 */
public class PE233 {

    public static void main(String[] args) {
        new PE233().solve(100000000000L);
    }

    void solve(long nMax) {

        // generate valid q's
        Util.initPrimes(4733727);
        List<Integer> primesQ = new ArrayList<>();
        for (int p : Util.primes) {
            if (p % 4 == 1) {
                primesQ.add(p);
            }
        }

        // generate valid p1*p2*p3*...*pn's
        BitSet bs = new BitSet();
        bs.flip(1, 278455);
        for (int p : primesQ) {
            for (int n = 1; n * p <= 278455; n++) {
                bs.clear(n * p);
            }
        }

        // b1,b2,b3 = 1,2,3
        long p1, p2, sum = 0;
        for (int k = 0; k < primesQ.size(); k++) {
            for (int j = 0; j < primesQ.size() && (p1 = getLong(primesQ.get(k), primesQ.get(j), 2)) <= nMax; j++) {
                for (int i = 0; i < primesQ.size() && (p2 = getLong(p1, primesQ.get(i), 3)) <= nMax; i++) {
                    if (k != j && j != i && i != k) {
                        for (int b = bs.nextSetBit(0); b >= 0 && p2 * b <= nMax; b = bs.nextSetBit(b + 1)) {
                            sum += p2 * b;
                        }
                    }
                }
            }
        }

        // b1,b2 = 2,10 
        for (int j = 0; j < primesQ.size() && (p1 = getLong(1, primesQ.get(j), 2)) <= nMax; j++) {
            for (int i = 0; i < primesQ.size() && (p2 = getLong(p1, primesQ.get(i), 10)) <= nMax; i++) {
                if (j != i) {
                    for (int b = bs.nextSetBit(0); b >= 0 && p2 * b <= nMax; b = bs.nextSetBit(b + 1)) {
                        sum += p2 * b;
                    }
                }
            }
        }

        // b1,b2 = 3,7 
        for (int j = 0; j < primesQ.size() && (p1 = getLong(1, primesQ.get(j), 3)) <= nMax; j++) {
            for (int i = 0; i < primesQ.size() && (p2 = getLong(p1, primesQ.get(i), 7)) <= nMax; i++) {
                if (j != i) {
                    for (int b = bs.nextSetBit(0); b >= 0 && p2 * b <= nMax; b = bs.nextSetBit(b + 1)) {
                        sum += p2 * b;
                    }
                }
            }
        }

        System.out.println(sum);
    }

    long getLong(long a, long b, int e) {
        for (int i = 0; i < e; i++) {
            if (a >= Long.MAX_VALUE / b) {
                return Long.MAX_VALUE;
            }
            a *= b;
        }
        return a;
    }

    void test() {
        for (int n = 1; n <= 10000; n++) {
            int count = 0;
            int min = -1 + (int) ((1D - Math.sqrt(2)) * n / 2);
            int max = 1 + (int) ((1D + Math.sqrt(2)) * n / 2);
            for (int x = min; x < max; x++) {
                for (int y = min; y < max; y++) {
                    if (n * (x + y) == x * x + y * y) {
                        count++;
                    }
                }
            }
            System.out.println(n + " " + count);
        }
    }

}
