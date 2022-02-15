package P101_P150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import util.Util;

/*
 * The radical of n, rad(n), is the product of distinct prime factors of n. 
 * For example, 504 = 2^3 x 3^2 x 7, so rad(504) = 2 x 3 x 7 = 42.
 * 
 * We shall define the triplet of positive integers (a, b, c) to be an abc-hit if:
 * GCD(a,b) = GCD(a,c) = GCD(b,c) = 1
 * a < b
 * a + b = c
 * rad(abc) < c
 * 
 * It turns out that abc-hits are quite rare and there are only thirty-one abc-hits 
 * for c < 1000, with ∑ c = 12523.
 * 
 * Find ∑ c for c < 120000.
 */
public class P127 {

    public static long solve(int cMax) {
        long sum = 0;
        Util.initPrimes(cMax);
        int[] rad = new int[cMax];
        List<int[]> rads = new ArrayList<>();
        for (int n = 1; n < cMax; n++) {
            int r = Util.radical(n);
            rad[n] = r;
            rads.add(new int[]{r, n});
        }
        Collections.sort(rads, new Comparator<int[]>() {
            @Override
            public int compare(int[] r1, int[] r2) {
                return r1[0] == r2[0] ? r1[1] - r2[1] : r1[0] - r2[0];
            }
        });
        for (int c = 1; c < cMax; c++) {
            int radb, i = 0;
            while ((radb = rads.get(++i)[0]) * rad[c] < c) {
                int b = rads.get(i)[1];
                int a = c - b;
                if (b < c && a < b && 1L * rad[a] * radb * rad[c] < c && gcd(a, b) == 1) {
                    System.out.format("(a,b,c)=(%d,%d,%d)%n", a, b, c);
                    sum += c;
                }
            }
        }
        return sum;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(P127.solve(120000));
    }
}
