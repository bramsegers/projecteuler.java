package P051_P100;

import util.Util;

/*
It is possible to write ten as the sum of primes in exactly five different ways:

7 + 3
5 + 5
5 + 3 + 2
3 + 3 + 2 + 2
2 + 2 + 2 + 2 + 2

What is the first value which can be written as 
the sum of primes in over five thousand different ways?
 */
public class P077 {

    public static long solve(int nMax) {
        long sum = 0;
        Util.initPrimes(1000);
        int n = 0;
        while (sum < nMax) {
            n++;
            sum = 0;
            count = new long[n][n];
            int p, i = 0;
            while ((p = Util.primes.get(i++)) < n) {
                sum += count(p, n - p);
            }
            System.out.println(n + " " + sum);
        }
        return n;
    }

    static long[][] count;

    private static long count(int take, int left) {
        if (left < 0) {
            return 0;
        } else if (left == 0) {
            count[take][left] = 1;
        } else if (count[take][left] == 0) {
            int p, i = 0, rv = 0;
            while ((p = Util.primes.get(i++)) <= take) {
                rv += count(p, left - p);
            }
            count[take][left] = rv;
        }
        return count[take][left];
    }

    public static void main(String[] args) {
        System.out.println(P077.solve(5000));
    }
}
