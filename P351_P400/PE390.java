package P351_P400;

import java.math.BigInteger;
import util.Util;

public class PE390 {

    /*
     *  http://www.davdata.nl/driehoek.html
     *  4 k^2 m^2 + k^2 + m^2 = opp^2   (b,c)=(2*k,2*m)
     *  http://en.wikipedia.org/wiki/Pythagorean_quadruple
     *  ...
     */
    public static void main(String[] args) {
        //new PE390().test();
        new PE390().solve();
    }

    long max = (long) 1E10;
    double maxSQ = 1E20;
    double maxL = 1E16;
    long sqrt = (long) Math.sqrt(max / 2);

    void test() {

        long sum = 0;
        for (long k = 1; k <= sqrt; k++) {
            System.out.println(k);
            for (long m = k + 1;; m += (k & 1) == 1 ? 2 : 1) {
                double a = 4D * k * k * m * m + k * k + m * m;
                if (a > maxSQ) {
                    break;
                }
                long sq = a < maxL ? (long) Math.sqrt(a) : Util.sqrt(BigInteger.valueOf((long) a), BigInteger.valueOf(2 * k * m)).longValue();
                if (sq <= max && sq * sq == a) {
                    //System.out.println(k + " " + m + " " + (4 * k * m));
                    sum += sq;
                }
            }
        }
        System.out.println("s=" + sum);
    }

    int j = 0, m = 100000;
    long ans = 0, ml = m, n = ml * ml;

    void solve() {
        for (long t = 1; t < m; t++) {
            long b = 2 * t, g = b * b + 1, f = 2 * g - 1, h = 4 * t, d = t * f;
            if (d > n) {
                break;
            }
            snextp(t, g - 1, g, h, f, d);
        }
        System.out.println(ans);
    }

    void snextp(long t, long s, long g, long h, long f, long d) {
        ans += d;
        j++;
        snextm(s, t, d);
        long sn = s * f + h * d, dn = f * d + g * s * h;
        if ((dn > n) || (dn < 0)) {
            return; //sloppy way to deal with overflows
        }
        snextp(t, sn, g, h, f, dn);
    }

    void snextm(long t, long s, long d) {
        long g = 4 * t * t + 1, f = 2 * g - 1, h = 4 * t, sn = s * f - h * d, dn = f * d - g * s * h;
        if (sn < 0) {
            sn = -sn;
        }
        if (dn < 0) {
            dn = -dn;
        }
        if (dn > n) {
            return;
        }
        ans += dn;
        j++;
        snextm(sn, t, dn);
        long sn2 = sn * f + h * dn, dn2 = f * dn + g * sn * h;
        if ((dn2 <= n) && (dn2 > 0)) {
            snextp(t, sn2, g, h, f, dn2);
        }
        long sn3 = s * f + h * d, dn3 = f * d + g * s * h;
        if ((dn3 <= n) && (dn3 > 0)) {
            snextp(t, sn3, g, h, f, dn3);
        }
    }

}
