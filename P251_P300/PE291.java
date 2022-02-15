package P251_P300;

import java.math.BigInteger;

public class PE291 {

    // P(5000000000000000)=4037526
    // (total time: 3 minutes 24 seconds)
    public static void main(String[] args) {
        new PE291().solve((long) 5E15);
    }

    //p is of form: n^2 + (n+1)^2
    void solve(long max) {
        long count = 0, n = 1, m = 5;
        while (m < max) {
            long d = ++n % 10;
            count += BigInteger.valueOf(m).isProbablePrime(10) ? 1 : 0;
            n += (d == 1 || d == 3 || d == 6 || d == 8) ? 1 : 0;
            m = n * n + (n + 1) * (n + 1);
        }
        System.out.format("P(%d)=%d%n", max, count);
    }

}
