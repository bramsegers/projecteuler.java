package P151_P200;

import java.math.BigInteger;

public class PE188 {

    public static void main(String[] args) {
        new PE188().solve(1777, 1855, 8);
    }

    void solve(int a, int b, int d) {
        long start = System.currentTimeMillis();
        BigInteger hyper = hyperexp(
                new BigInteger(String.valueOf(a)),
                new BigInteger(String.valueOf(b)),
                BigInteger.TEN.pow(d)
        );
        long end = System.currentTimeMillis();
        System.out.format("%d↑↑%d mod 10^%d = %d%nElapsed:%dms%n", a, b, d, hyper, end - start);
    }

    BigInteger hyperexp(BigInteger a, BigInteger b, BigInteger mod) {
        return b.equals(BigInteger.ONE) ? a : a.modPow(hyperexp(a, b.subtract(BigInteger.ONE), mod), mod);
    }

}
