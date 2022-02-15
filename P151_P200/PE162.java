package P151_P200;

public class PE162 {

    public static void main(String[] args) {
        new PE162().solve(16);
    }

    void solve(int limit) {
        long start = System.currentTimeMillis();
        long count = 0;
        for (int n = 3; n <= limit; n++) {
            nMax = n;
            m = new long[16][n][2][2][2];
            for (int i = 1; i < 16; i++) {
                count += count(i, 1, i == 0 ? 1 : 0, i == 1 ? 1 : 0, i == 10 ? 1 : 0);
            }
        }
        String hex = Long.toHexString(count).toUpperCase();
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%s%nElapsed:%dms%n", limit, hex, end - start);
    }

    long[][][][][] m;
    int nMax;

    long count(int n, int pos, int zero, int one, int ten) {
        if (pos == nMax && zero + one + ten < 3) {
            return 0;
        } else if (pos == nMax) {
            return 1;
        } else if (m[n][pos][zero][one][ten] == 0) {
            long rv = 0;
            for (int i = 0; i < 16; i++) {
                rv += count(i, pos + 1, i == 0 ? 1 : zero, i == 1 ? 1 : one, i == 10 ? 1 : ten);
            }
            m[n][pos][zero][one][ten] = rv;
        }
        return m[n][pos][zero][one][ten];
    }

}
