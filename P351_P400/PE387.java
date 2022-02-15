package P351_P400;

import util.Util;

public class PE387 {

    public static void main(String[] args) {
        new PE387().solve(14);
    }

    void solve(int len) {
        for (int rd = 0; rd < 10; rd++) {
            solve(rd, rd, 1, len);
        }
        System.out.format("P(%d)=%d%n", len, total);
    }

    long total = 0;

    void solve(long cur, int sum, int pos, int len) {
        if (sum > 0 && cur % sum != 0) {
            return;
        }
        if (++pos == len) {
            if (sum > 0 && Util.isPrime(cur / sum)) {
                for (int j : new int[]{1, 3, 7, 9}) {
                    long p = 10 * cur + j;
                    if (Util.isPrime(p)) {
                        System.out.println(p);
                        total += p;
                    }
                }
            }
            return;
        }
        for (int i = 0; i < 10; i++) {
            solve(cur * 10 + i, sum + i, pos, len);
        }
    }

}
