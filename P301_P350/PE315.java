package P301_P350;

import util.Util;

public class PE315 {

    public static void main(String[] args) {
        new PE315().solve(10000000, 20000000);
    }

    int[] sam, max;

    void solve(int pMin, int pMax) {

        Util.initPrimes(pMax);

        sam = new int[]{6, 2, 5, 5, 4, 5, 6, 4, 7, 6};
        max = new int[]{
            0b1110111, 0b0010010, 0b1011101, 0b1011011, 0b0111010,
            0b1101011, 0b1101111, 0b1110010, 0b1111111, 0b1111011, 0};

        int sumSam = 0, sumMax = 0;
        for (int N : Util.primes) {
            int n = N, lastN = 0;
            boolean more = N >= pMin;
            while (more) {
                if (n == lastN) {
                    more = false;
                    n = 0;
                }
                sumSam += getSam(lastN) << 1;
                sumMax += getMax(lastN, n);
                lastN = n;
                n = digRoot(n);
            }
        }
        System.out.format("Max:%d%nSam:%d%nDiff:%d%n", sumSam, sumMax, sumSam - sumMax);
    }

    int digRoot(int n) {
        int rv = 0;
        while (n > 0) {
            rv += n % 10;
            n /= 10;
        }
        return rv;
    }

    int getSam(int n) {
        int rv = 0;
        while (n > 0) {
            rv += sam[n % 10];
            n /= 10;
        }
        return rv;
    }

    int getMax(int n, int m) {
        if (n == 0) {
            return getSam(m);
        }
        if (m == 0) {
            return getSam(n);
        }
        int rv = 0;
        int n2 = n, m2 = m;
        while (n2 > 0) {
            int d1 = n2 % 10;
            int d2 = m2 == 0 ? 10 : (m2 % 10);
            int mx = max[d1] ^ max[d2];
            for (int i = 0; i < 7; i++) {
                rv += ((mx >> i) & 1) == 1 ? 1 : 0;
            }
            n2 /= 10;
            m2 /= 10;
        }
        return rv;
    }

}
