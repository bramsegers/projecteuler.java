package P301_P350;

public class PE341 {

    /*
     http://en.wikipedia.org/wiki/Golomb_sequence
     */
    public static void main(String[] args) {
        new PE341().solve();
    }

    void solve() {

        // build golomb sequence
        long m = (long) 1E6;
        int i = 0;
        while (S[i] < m * m * m) {
            g(++i);
        }

        // collect sum
        long sum = 0;
        int lastn = 1;
        for (long nd = 1; nd < m; nd++) {
            long need = nd * nd * nd;
            for (int n = lastn;; n++) {
                if (S[n] >= need) {
                    long dif = need - S[n - 1];
                    long stp = dif / n;
                    long F = S[n] == need
                            ? s[n]
                            : (s[n - 1] + stp
                            + (dif % n == 0
                                    ? 0
                                    : 1));
                    sum += F;
                    lastn = n;
                    break;
                }
            }

        }
        System.out.println(sum);
    }

    int max = 11000000;
    int[] g = new int[max + 1];
    long[] s = new long[max + 1];
    long[] S = new long[max + 1];

    long g(int n) {
        if (g[n] == 0) {
            g[n] = (n == 1)
                    ? 1
                    : 1 + g[n - g[g[n - 1]]];
        }
        s[n] = (long) g[n] + s[n - 1];
        S[n] = (long) n * g[n] + S[n - 1];
        return g[n];
    }

}
