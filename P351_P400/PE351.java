package P351_P400;

import util.Util;

public class PE351 {

    public static void main(String[] args) {
        new PE351().test(1000);
        new PE351().solve(100000000);
    }

    void solve(int n) {
        Util.initPrimes(n);
        long tsum=0;
        int[] totient = new int[n];
        for (int i = 1; i < n; i++) {
            totient[i] = Util.totient(i);
            tsum+=totient[i];
        }
        System.out.println("tsum: "+tsum);
        long a = n / 2 - 1;
        long b = n - 1;
        long c = 0;
        for (int i = 3; i <= n; i++) {
            if (i % 2 == 1) {
                c += (i - totient[i]) / 2;
            } else {
                c += i / 2 - totient[i / 2] - 1;
                c += (i % 4 == 2) ? totient[i / 2] / 2 : 0;
            }
        }
        long sum = (a + b + 2 * c) * 6;
        System.out.format("P(%d)=%d%n", n, sum);
    }

    void test(int n) {
        long a = n / 2 - 1;
        long b = n - 1;
        long c = 0;
        for (int i = 3; i <= n; i++) {
            for (int j = (i % 2 == 0) ? 2 : 3; j < i; j += 2) {
                int gcd = Util.gcd(i, j);
                if (gcd == 2) {
                    if ((i / 2 % 2) == (j / 2 % 2)) {
                        c++;
                    }
                } else if (gcd > 2) {
                    c++;
                }
            }
        }
        long rv = 6 * a + 6 * b + 12 * c;
        System.out.println(rv);
    }

}
