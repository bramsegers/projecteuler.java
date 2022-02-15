package P401_P450;

import java.math.BigInteger;

public class PE401 {

    // https://oeis.org/A000330
    public static void main(String[] args) {
        new PE401().solve((long) 1E15, (long) 1E9);
    }

    void solve(long num, long mod) {
        BigInteger sum = BigInteger.ZERO;
        long a = 0, b = num, d = 1;
        boolean more = true;
        while (more) {
            a = num / (d + 1);
            sum = sum.add(
                    BigInteger.valueOf(d)
                    .multiply(sqSum(b)
                            .subtract(sqSum(a))));
            more = b > a + 1;
            b = a;
            d++;
        }
        for (long n = a; n > 0; n--) {
            sum = sum.add(
                    BigInteger.valueOf(num / n)
                    .multiply(sqSum(n)
                            .subtract(sqSum(n - 1))));
        }
        System.out.println(sum);
        System.out.format("P(%d,%d)=%s%n",
                num,
                mod,
                sum.mod(BigInteger.valueOf(mod)));
    }

    BigInteger sqSum(long n) {
        return BigInteger.valueOf(n)
                .multiply(BigInteger.valueOf(n + 1))
                .multiply(BigInteger.valueOf(2 * n + 1))
                .divide(BigInteger.valueOf(6));
    }

}
