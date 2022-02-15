package P101_P150;

import java.util.ArrayList;
import java.util.List;

/*
 * Looking at the table below, it is easy to verify that the maximum possible sum 
 * of adjacent numbers in any direction (horizontal, vertical, diagonal or anti-diagonal) 
 * is 16 (= 8 + 7 + 1).
 * 
 *    -2    5    3    2
 *     9   -6    5    1
 *     3    2    7    3
 *    -1    8   -4    8
 * 
 * Now, let us repeat the search, but on a much larger scale. First, generate four million 
 * pseudo-random numbers using a specific form of what is known as a "Lagged Fibonacci Generator":
 * For 1 <= k <= 55, s(k) = [100003 - 200003k + 300007k^3] (modulo 1000000) - 500000.
 * For 56 <= k <= 4000000, sk = [s(k-24) + s(k-55) + 1000000] (modulo 1000000) - 500000.
 * 
 * Thus, s(10) = -393027 and s(100) = 86613.
 * 
 * The terms of s are then arranged in a 2000x2000 table, using the first 2000 numbers to fill 
 * the first row (sequentially), the next 2000 numbers to fill the second row, and so on.
 * 
 * Finally, find the greatest sum of (any number of) adjacent entries in any direction.
 *
 * Info: http://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
public class P149 {

    public static long solve(int nMax) {

        // init
        long max = 0;
        long[] s = new long[nMax * nMax + 1];
        List<Long> temp = new ArrayList<>();

        // generate lagged fibonacci nums
        for (int k = 1; k <= nMax * nMax; k++) {
            s[k] = k < 56
                    ? (100003L - 200003L * k + 300007L * k * k * k) % 1000000L - 500000L
                    : (s[k - 24] + s[k - 55] + 1000000L) % 1000000L - 500000L;
        }

        // generate matrix
        long[][] t = new long[nMax][nMax];
        for (int i = 0; i < nMax * nMax; i++) {
            t[i / nMax][i % nMax] = s[i + 1];
        }

        // check horizontal
        for (int j = 0; j < nMax; j++) {
            temp.clear();
            for (int i = 0; i < nMax; i++) {
                temp.add(t[j][i]);
            }
            max = Math.max(max, maxSubArray(temp));
        }

        // check vertical
        for (int i = 0; i < nMax; i++) {
            temp.clear();
            for (int j = 0; j < nMax; j++) {
                temp.add(t[j][i]);
            }
            max = Math.max(max, maxSubArray(temp));
        }

        // check diagonal 
        for (int i = 0; i < 2 * nMax - 1; i++) {
            temp.clear();
            int x = i < nMax - 1 ? 0 : i - nMax + 1;
            int y = i < nMax ? i : nMax - 1;
            int r = i < nMax ? i + 1 : 2 * nMax - 2 - i + 1;
            while (r-- > 0) {
                temp.add(t[y--][x++]);
            }
            max = Math.max(max, maxSubArray(temp));
        }

        // check anti-diagonal 
        for (int i = 0; i < 2 * nMax - 1; i++) {
            temp.clear();
            int x = i < nMax ? nMax - i - 1 : 0;
            int y = i < nMax - 1 ? 0 : i - nMax + 1;
            int r = i < nMax ? i + 1 : 2 * nMax - 2 - i + 1;
            while (r-- > 0) {
                temp.add(t[y++][x++]);
            }
            max = Math.max(max, maxSubArray(temp));
        }

        return max;
    }

    private static long maxSubArray(List<Long> list) {
        long maxSoFar = list.get(0);
        long maxEndingHere = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (maxEndingHere < 0) {
                maxEndingHere = list.get(i);
            } else {
                maxEndingHere += list.get(i);
            }
            if (maxEndingHere >= maxSoFar) {
                maxSoFar = maxEndingHere;
            }
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println(P149.solve(2000));
    }
}
