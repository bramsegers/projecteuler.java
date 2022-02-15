package P351_P400;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PE359 {

    /*
     * test() =>   floor 4  => https://oeis.org/A135405
     *                      => a(n)=(n+2)*(n+1)/2+2*(-1)^n
     * 
     * modify, eg. floor 8  => a(n)=(n+6)*(n+5)/2+2*(-4)^n  
     */
    public static void main(String[] args) {
        //new Test().test();
        new PE359().solve();
    }

    void solve() {
        // 71328803586048 = 2^27 * 3^12
        long n = 71328803586048L, sum = 0;
        for (int e2 = 0; e2 <= 27; e2++) {
            for (int e3 = 0; e3 <= 12; e3++) {
                long f = (long) (Math.pow(2, e2) * Math.pow(3, e3));
                long r = n / f;
                sum += P(f, r);
                sum %= 100000000;
            }
        }
        System.out.println(sum);
    }

    long P(long f, long r) {
        r += (f == 1) ? 2 : (f % 2 == 0) ? 2 : 1;
        long d = (r % 2 == 0) ? f / 2 : -f / 2;
        //return (r + f - 2) * (r + f - 3) / 2 + d;
        //prevent overflow
        return BigInteger.valueOf(r + f - 2)
                .multiply(BigInteger.valueOf(r + f - 3))
                .shiftRight(1)
                .add(BigInteger.valueOf(d))
                .mod(BigInteger.valueOf(100000000))
                .longValue();
    }

    void test() {
        List<List<Integer>> fr = new ArrayList<>();
        for (int n = 1; n < 400; n++) {
            boolean found = false;
            for (int i = 0; i < fr.size() && !found; i++) {
                List<Integer> f = fr.get(i);
                int r = f.get(f.size() - 1);
                int sq = (int) Math.sqrt(r + n);
                if (sq * sq == r + n) {
                    f.add(n);
                    found = true;
                }
            }
            if (!found) {
                List<Integer> f = new ArrayList<>();
                f.add(n);
                fr.add(f);
            }
        }
        for (List<Integer> f : fr) {
            System.out.println(f);
        }
        for (int f = 1; f < 20; f++) {
            String s = "";
            for (int n = 1; n < 30; n++) {
                s += P(f, n) + ", ";
            }
            System.out.println(s);
        }
    }

}
