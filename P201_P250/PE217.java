package P201_P250;

import java.math.BigInteger;

public class PE217 {

    public static void main(String[] args) {
        new PE217().test(5);
        new PE217().solve(47, (long) Math.pow(3, 15));
    }

    long mod;
    BigInteger[] fac;
    long[] totL, totR;
    long[][] sumdigL, sumdigR;
    long[][][] sumdigL2;

    void solve(int N, long M) {
        mod = M;
        fac = new BigInteger[N / 2 + 1];
        fac[0] = BigInteger.ONE;
        for (int i = 1; i <= N / 2; i++) {
            fac[i] = fac[i - 1].multiply(BigInteger.valueOf(i));
        }
        long totsum = 0;
        for (int n = 1; n <= N; n++) {
            long sum = solve(n);
            totsum = (totsum + sum) % mod;
            System.out.println(n + " " + sum);
        }
        System.out.println("tot=" + totsum);
    }

    long solve(int m) {
        if (m < 4) {
            return (new long[]{0, 45, 495, 49500}[m]) % mod;
        }
        int n = m / 2;
        if (m % 2 == 0) {
            totL = new long[9 * n + 1];
            totR = new long[9 * n + 1];
            sumdigL2 = new long[9 * n + 1][10][10];
            sumdigR = new long[9 * n + 1][10];
            sumdigL = new long[9 * n + 1][10];
            search(new int[10], 0, 0, n, n, fac[n]);
        }
        int e = m % 2;
        long M1 = mod(n - 1, e == 0 ? n : n + 2);
        long M2 = mod(1, e == 0 ? 2 * n - 1 : 2 * n + 1);
        long M3 = mod(n, e == 0 ? 0 : 1);
        long M4 = e == 0 ? 0 : mod(1, n);
        long sum = 0;
        for (int s = 0; s <= 9 * n; s++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 1; j < 10; j++) {
                    sum = (sum + ((((M1 * i) % mod * sumdigL2[s][i][j]) % mod * totR[s]) % mod)) % mod;
                }
                sum = (sum + ((((M2 * i) % mod * sumdigL[s][i]) % mod * totR[s]) % mod)) % mod;
                sum = (sum + ((((M3 * i) % mod * sumdigR[s][i]) % mod * totL[s]) % mod)) % mod;
                sum = (sum + ((((M4 * i) % mod * totL[s]) % mod * totR[s]) % mod)) % mod;
            }
        }
        return sum;
    }

    long mod(int n1, int n0) {
        long m = 0;
        for (int n = 0; n < n1; n++) {
            m = (m * 10 + 1) % mod;
        }
        for (int n = 0; n < n0; n++) {
            m = (m * 10) % mod;
        }
        return m;
    }

    void search(int[] state, int digit, int sum, int pos, int posleft, BigInteger f) {
        if (digit == 10) {
            calc(state, sum, pos, f);//f.longValue() % mod);
            return;
        }
        for (int d = (digit == 9) ? posleft : 0; d <= posleft; d++) {
            state[digit] = d;
            search(state, digit + 1, sum + (d * digit), pos, posleft - d, f.divide(fac[d]));
            state[digit] = 0;
        }
    }

    void calc(int[] state, int sum, int pos, BigInteger perm) {
        for (int dig = 0; dig < 10; dig++) {
            if (state[dig] > 0) {
                BigInteger P = perm
                        .multiply(BigInteger.valueOf(state[dig]))
                        .divide(BigInteger.valueOf(pos));
                long p = P.mod(BigInteger.valueOf(mod)).longValue();
                totL[sum] = (totL[sum] + (dig == 0 ? 0 : p)) % mod;
                totR[sum] = (totR[sum] + p) % mod;
                state[dig]--;
                for (int dig2 = 0; dig2 < 10; dig2++) {
                    if (state[dig2] > 0) {
                        long p2 = P
                                .multiply(BigInteger.valueOf(state[dig2]))
                                .divide(BigInteger.valueOf(pos - 1))
                                .mod(BigInteger.valueOf(mod))
                                .longValue();
                        sumdigR[sum][dig2] = (sumdigR[sum][dig2] + p2) % mod;
                        sumdigL[sum][dig] = (sumdigL[sum][dig] + (dig == 0 ? 0 : p2)) % mod;
                        sumdigL2[sum][dig2][dig] = (sumdigL2[sum][dig2][dig] + (dig == 0 ? 0 : p2)) % mod;
                    }
                }
                state[dig]++;
            }
        }
    }

    void test(int n) {
        long sum = 45;
        int max = (int) Math.pow(10, n);
        for (int i = 10; i < max; i++) {
            String str = String.valueOf(i);
            int len = str.length() / 2;
            int a = Integer.valueOf(str.substring(0, len));
            int b = Integer.valueOf(str.substring(str.length() - len));
            if (digsum(a) == digsum(b)) {
                sum += i;
            }
        }
        System.out.println(n + " " + sum);
    }

    int digsum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

}
