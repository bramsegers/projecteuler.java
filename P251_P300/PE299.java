package P251_P300;

import static java.lang.Math.*;
import static util.Util.gcd;

public class PE299 {

    public static void main(String[] args) {
        new PE299().solve(100);
        new PE299().solve(100000);
        new PE299().solve(100000000);
    }

    void solve(long N) {
        long count = 2 * case1(N) + case2(N);
        System.out.format("T(%d)=%d%n", N, count);
    }

    long case1(long N) {
        long m, n, a, b, count = 0;
        for (m = 1; m * m <= N; m++) {
            for (n = (m & 1) + 1; n < m && c(m, n) <= N; n += 2) {
                if (gcd(m, n) == 1) {
                    a = m * m - n * n;
                    b = 2 * m * n;
                    count += (N - 1) / (a + b);
                }
            }
        }

        return count;
    }

    long case2(long N) {
        long m, n, c, count = 0;
        for (m = 1; 2 * m * m < N; m++) {
            for (n = (m & 1) + 1; n < m && (c = 2 * c(m, n)) < N; n += 2) {
                if (gcd(m, n) == 1) {
                    count += (N - 1) / c;
                }
            }
        }
        return count;
    }

    long c(long m, long n) {
        return m * m + n * n;
    }

    long brute1(long N) {
        long count = 0;
        for (long b = 3; b < N; b++) {
            for (long d = b + 1; b + d < N; d++) {
                long s2 = b * b + d * d;
                long s = (long) sqrt(s2);
                if (s * s == s2) {
                    for (long a = 2; a < min(b, d); a++) {
                        for (long p = 1; p < a; p++) {
                            if (p * p + (d - a + p) * (d - a + p) == (d - a) * s && (a - p) * (a - p) + (b - p) * (b - p) == (b - a) * s) {
                                count++;//1 solution
                                //break;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    long brute2(long N) {
        long count = 0;
        for (long b = 3; 2 * b < N; b++) {
            for (long a = 2; a < b; a++) {
                for (long p = 1; p < a; p++) {
                    if ((a - p) * (a - p) + (b - p) * (b - p) == 2 * b * (a - p) && p * p + (b - a + p) * (b - a + p) == 2 * b * p) {
                        count++;// 2 solutions
                        break;
                    }
                }
            }
        }
        return count;
    }

}
