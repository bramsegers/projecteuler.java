package P201_P250;

import util.Util;

public class PE214 {

    public static void main(String[] args) {
        new PE214().solve(40000000, 25);
    }

    int[] length;

    void solve(int pMax, int len) {
        long sum = 0;
        length = new int[pMax];
        length[1] = 1;
        Util.initPrimes(pMax);
        for (int p : Util.primes) {
            if (length(p) == len) {
                sum += p;
            }
        }
        System.out.format("S(%d,%d)=%d%n", pMax, len, sum);
    }

    int length(int n) {
        if (length[n] == 0) {
            length[n] = 1 + length(Util.totient(n));
        }
        return length[n];
    }

}
