package P451_P500;

public class PE458 {

    // Nabb's post
    public static void main(String[] args) {
        new PE458().solve((long) 1e12, (long) 1e9);
    }

    void solve(long N, long mod) {
        long[][] M = {
            {1, 1, 1, 1, 1, 1},
            {6, 1, 1, 1, 1, 1},
            {0, 5, 1, 1, 1, 1},
            {0, 0, 4, 1, 1, 1},
            {0, 0, 0, 3, 1, 1},
            {0, 0, 0, 0, 2, 1}
        };
        long[][] X = {
            {1, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1}
        };
        N--;
        while (N > 0) {
            X = (N & 1) == 0 ? X : mult(X, M, mod);
            M = mult(M, M, mod);
            N >>= 1;
        }
        long sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += 7 * X[i][0];
            sum %= mod;
        }
        System.out.println(sum);
    }

    long[][] mult(long[][] X, long[][] M, long mod) {
        long[][] rv = new long[6][6];
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                for (int i = 0; i < 6; i++) {
                    rv[r][c] += X[r][i] * M[i][c];
                    rv[r][c] %= mod;
                }
            }
        }
        return rv;
    }
}
