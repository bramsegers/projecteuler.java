package P301_P350;

import java.math.BigInteger;

public class PE303 {

    public static void main(String[] args) {
        new PE303().solve(10000);
    }

     void solve(int nMax) {
        long sum = 0;
        for (int n = 1; n <= nMax; n++) {
            min = Long.MAX_VALUE;
            for (int i = 1; i < 10; i++) {
                if ((n * i) % 10 < 3) {
                    min(i, n, 10);
                }
            }
            sum += min;
            System.out.println(n + " " + min);
        }
        System.out.println(sum);
    }

    long min;

    void min(long i, long n, long d) {
        if (d > 10000000000000000L || i > min) {
            return;
        }
        if (isValid(n, i, d)) {
            min = i;
        } else {
            for (long j = 0; j < 10; j++) {
                long k = j * d + i;
                if (((n * k) % (10 * d)) / d < 3) {
                    min(k, n, 10 * d);
                }
            }
        }
    }

    boolean isValid(long n, long i, long d) {
        return n < Long.MAX_VALUE / i ? isValid1(n * i / d) : isValid2(n, i, d);
    }

    boolean isValid1(long i) {
        while (i > 0) {
            long t = i % 10;
            if (t > 2) {
                return false;
            }
            i /= 10;
        }
        return true;
    }

    boolean isValid2(long n, long i, long d) {
        BigInteger bi = new BigInteger("" + n).multiply(new BigInteger("" + i));
        String s = bi.toString();
        for (int j = 3; j < 10; j++) {
            if (s.contains("" + j)) {
                return false;
            }
        }
        return true;
    }

}
