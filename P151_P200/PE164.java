package P151_P200;

public class PE164 {

    public static void main(String[] args) {
        new PE164().solve(20);
    }

    long[][][] m;

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <= 9; i++) {
            m = new long[10][10][nMax];
            sum += count(0, i, nMax - 1);
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);

    }

    long count(int d1, int d2, int remaining) {
        if (remaining == 0) {
            m[d1][d2][remaining] = 1;
        } else if (m[d1][d2][remaining] == 0) {
            for (int i = 0; i + d1 + d2 <= 9; i++) {
                m[d1][d2][remaining] += count(d2, i, remaining - 1);

            }
        }
        return m[d1][d2][remaining];

    }

}
