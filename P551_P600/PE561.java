package P551_P600;

import java.math.BigInteger;

public class PE561 {

    public static void main(String[] args) {
        new PE561().test(904961, 8);
        new PE561().solve(904961, (long) 1e12);
    }

    void solve(long m, long n) {
        long sum = 0;
        long a = 1;
        long b = 0;
        long c = n / 4;
        while (a > 0) {
            a = (c + 1) / 2;
            b += 1;
            c -= a;
            sum += a * b;
        }
        sum *= m + 1;
        System.out.println(sum);
    }

    void test(int M, long N) {
        long sum = 0;
        for (long n = 1; n <= N; n++) {
            if (n % 4 == 1 || n % 4 == 2) {
                continue;
            }
            long tn = (n + 2) * (n + 1) / 2;
            BigInteger A = BigInteger.valueOf(tn);
            BigInteger B = BigInteger.valueOf(n + 1);
            sum += A.pow(M).subtract(B.pow(M)).getLowestSetBit();
        }
        System.out.println(sum);
    }

}
