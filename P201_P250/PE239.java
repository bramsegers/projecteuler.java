package P201_P250;

import java.math.BigDecimal;
import java.math.BigInteger;
import util.Util;

public class PE239 {

    /*
     * Inclusion/exclusion:
     *
     *               22
     * P = ( C(25,3) Σ (-1)^n C(22,n)(97-n)! ) / 100!
     *               n=0
     */
    public static void main(String[] args) {
        new PE239().solve(22, 100);
    }

    void solve(int deranged, int total) {
        int inplace = Util.getPrimes(total).size() - deranged;
        BigInteger c = choose(deranged + inplace, inplace);
        BigInteger f = fac(BigInteger.valueOf(100));
        BigInteger sum = BigInteger.ZERO;
        for (int n = 0; n <= deranged; n++) {
            BigInteger b = choose(deranged, n).multiply(fac(BigInteger.valueOf(total - inplace - n)));
            sum = sum.add((n & 1) == 0 ? b : b.negate());
        }
        BigDecimal P = new BigDecimal(c.multiply(sum), 12).divide(new BigDecimal(f, 12), BigDecimal.ROUND_HALF_UP);
        System.out.format("P(%d,%d)=%s%n", deranged, total, P);
    }

    BigInteger choose(int n, int k) {
        return fac(BigInteger.valueOf(n)).divide(fac(BigInteger.valueOf(k)).multiply(fac(BigInteger.valueOf(n - k))));
    }

    BigInteger fac(BigInteger n) {
        return n.signum() == 0 ? BigInteger.ONE : n.multiply(fac(n.subtract(BigInteger.ONE)));
    }

}
