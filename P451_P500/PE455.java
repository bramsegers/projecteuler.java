package P451_P500;

//https://oeis.org/A165736
// a(n) = n^(n^(n^(n^(n^(n^(n^(n^(n^(n^n mod 10) mod 100) mod 1000) mod 10000) mod 100000) mod 1000000) mod 10000000) mod 100000000) mod 1000000000) mod 10000000000
import java.math.BigInteger;

public class PE455 {

    public static void main(String[] args) {
        new PE455().solve(1000000);
    }

    void solve(int m) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger[] pow10 = new BigInteger[12];
        for (int i = 0; i < 12; i++) {
            pow10[i] = BigInteger.TEN.pow(i);
        }
        for (int n = 2; n <= m; n++) {
            if (n % 10 > 0) {
                BigInteger N = BigInteger.valueOf(n);
                BigInteger a = N.modPow(N, pow10[1]);
                for (int i = 2; i < 12; i++) {
                    a = N.modPow(a, pow10[i]);
                }
                sum = sum.add(a.mod(pow10[9]));
            }
        }
        System.out.println(sum);
    }

}
