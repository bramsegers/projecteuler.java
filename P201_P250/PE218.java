package P201_P250;

public class PE218 {

    public static void main(String[] args) {
        new PE218().solve(10000000000000000L);
    }

    void solve(long cMax) {
        long sum = 0;
        long cSq = (long) Math.sqrt(cMax);
        long c, mSq;
        for (long m = 1; (mSq = m * m) <= cSq; m++) {
            for (long n = 1; n < m && (c = n * n + mSq) <= cSq; n++) {
                if ((m - n) % 2 == 1 && gcd(m, n) == 1) {
                    long a = (mSq - n * n);
                    long b = 2 * m * n;
                    long M = Math.max(a, b);
                    long N = Math.min(a, b);
                    long A = M * M - N * N;
                    long B = 2 * M * N;
                    //long AREA = A * B / 2;
                    sum += !isDiv_6_28(A, B / 2) ? 1 : 0;
                }
            }
        }
        System.out.format("P(%d)=%d%n", cMax, sum);
    }

    boolean isDiv_6_28(long a, long b) {
        return (a % 3 == 0 || b % 3 == 0)
                && (a % 7 == 0 || b % 7 == 0)
                && (a % 4 == 0 || b % 4 == 0 || (a % 2 == 0 && b % 2 == 0));
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
