package P151_P200;

import util.Util;

public class PE194 {

    public static void main(String[] args) {
        new PE194().solve(25, 75, 1984, 100000000);
    }

    void solve(int A, int B, int C, long M) {

        long a = 0, b = 0;
        for (int p = 0; p < C; p++) {
            for (int q = 0; q < C; q++) {
                if (p != q) {
                    a = (a + count(0, C, p, q, 0, 1)) % M;
                    b = (b + count(1, C, p, q, 0, 1)) % M;
                }
            }
        }

        long nCr = Util.chooseModB(A + B, B, M);
        long sum = (C * (C - 1) * nCr) % M;
        for (int i = 0; i < A + B; i++) {
            sum = (sum * (i < A ? a : b)) % M;
        }

        System.out.format("N(%d,%d,%d) mod %d = %d%n", A, B, C, M, sum);
    }

    long count(int AB, int C, int p, int q, int i, int j) {
        if (p == i || i == j || (q == j && AB == 0)) {
            return 0;
        }
        long c = C - 2;
        long n = c * c * c + c;
        if (q == j) {
            return n + c * c - 1;
        }
        if (p == j && q == i) {
            return n;
        }
        if (p == j || q == i) {
            return n - 1;
        }
        return n - 2;
    }

}
