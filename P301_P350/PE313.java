package P301_P350;

import util.Util;

public class PE313 {

    // brute force for small m,n
    // find pattern with http://mypuzzle.org/sliding
    public static void main(String[] args) {
        new PE313().solve(1000000);
    }

    void solve(int max) {
        long count = 0;
        for (long p : Util.getPrimes(max)) {
            count += ((p * p + 11) % 8 == 0) ? 1 : 0;
            if ((p * p + 13) % 2 == 0) {
                long q = (p * p + 13) / 2;
                count += (q < 12) ? 2 : ((q / 3) - (q / 4) - 1) * 2;
            }
        }
        System.out.format("P(%d)=%d%n", max, count);
    }

    int S(int m, int n) {
        return (m < n) ? S(n, m) : m == n ? 8 * n - 11 : 6 * m + 2 * n - 13;
    }
}
