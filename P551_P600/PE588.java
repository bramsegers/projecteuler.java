package P551_P600;

public class PE588 {

    // https://oeis.org/A035343
    // https://oeis.org/A247649
    // https://oeis.org/A247647
    // S(18)=11651930052
    // BUILD SUCCESSFUL (total time: 16 minutes 39 seconds)
    public static void main(String[] args) {
        new PE588().solve(18);
    }

    void solve(int m) {
        long tsum = 0;
        long t = 1;
        for (int i = 0; i < m; i++) {
            t *= 10;
            String s = Long.toBinaryString(t);
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }
            while (s.contains("000")) {
                s = s.replace("000", "00");
            }
            long sum = 1;
            for (String z : s.split("00")) {
                int n = Integer.parseInt(z, 2);
                sum *= A035343(n);
            }
            tsum += sum;
            System.out.format("Q(%d)=%d %n", t, sum);
        }
        System.out.format("S(%d)=%d %n", m, tsum);
    }

    long A035343(int k) {
        long sum = 0;
        for (int i = 0; i <= 4 * k; i++) {
            long t = 0;
            for (int j = 0; j <= i / 5; j++) {
                if (oddbin(k, j) && oddbin(k + i - 1 - 5 * j, k - 1)) {
                    t = 1 - t;
                }
            }
            sum += t;
        }
        return sum;
    }

    boolean oddbin(long n, long k) {
        return factors(2, n)
                - factors(2, k)
                - factors(2, n - k) == 0;
    }

    long factors(long p, long fac) {
        return fac == 0
                ? 0
                : fac / p + factors(p, fac / p);
    }

}
