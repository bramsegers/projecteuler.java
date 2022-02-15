/*

    195_overview.pdf
    ----------------
    Find all s1 = k(3pn+3n^2) and s2 = k(3pn+p^2) 
    with gcd(p,n)=1 and p not divisible by 3, 
    and n,p >= 1 so that s1 and s2 
    are smaller than sqrt(12)N
 
 */
package P151_P200;

import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;

public class PE195 {

    public static void main(String[] args) {
        new PE195().solve(100);
        new PE195().solve(1000);
        new PE195().solve(10000);
        new PE195().solve(1053779);
    }

    void solve(long N) {
        long count = 0;
        double lim = Math.sqrt(12) * N;
        for (long n = 1; 3 * n < lim; n++) {
            for (long p = 1; more(n, p, lim); p++) {
                if (p % 3 > 0 && gcd(n, p) == 1) {
                    count += lim / s1(n, p);
                    count += lim / s2(n, p);
                }
            }
        }
        System.out.format("T(%d)=%d%n", N, count);
    }

    boolean more(long n, long p, double lim) {
        return s1(n, p) < lim || s2(n, p) < lim;
    }

    long s1(long n, long p) {
        return 3 * p * n + 3 * n * n;
    }

    long s2(long n, long p) {
        return 3 * p * n + p * p;
    }

}
