package P151_P200;

public class PE178 {

    public static void main(String[] args) {
        new PE178().solve(40);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int n = 10; n <= nMax; n++) {
            count = new long[10][n][1 << 10];
            for (int i = 1; i < 10; i++) {
                sum += count(i, n - 1, 1 << i);
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }

    long[][][] count;

    long count(int n, int pos, int state) {
        if (n < 0 || n > 9 || (pos == 0 && state < (1 << 10) - 1)) {
            return 0;
        } else if (pos == 0) {
            return 1;
        } else if (count[n][pos][state] == 0) {
            count[n][pos][state]
                    = count(n - 1, pos - 1, state | (1 << (n - 1)))
                    + count(n + 1, pos - 1, state | (1 << (n + 1)));
        }
        return count[n][pos][state];
    }

}
