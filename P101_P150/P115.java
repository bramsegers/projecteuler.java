package P101_P150;

/*
 * A row measuring n units in length has red blocks with a minimum length of 
 * m units placed on it, such that any two red blocks (which are allowed to be 
 * different lengths) are separated by at least one black square.
 * Let the fill-count function, F(m, n), represent the number of ways that 
 * a row can be filled. For example, F(3, 29) = 673135 and F(3, 30) = 1089155.
 * That is, for m = 3, it can be seen that n = 30 is the smallest value 
 * for which the fill-count function first exceeds one million.
 * 
 * For m = 50, find the least value of n for which the fill-count function first exceeds one million.
 */
public class P115 {

    public static long solve(int m, int mSum) {
        long sum = 0;
        int n = m;
        while (sum < mSum) {
            n++;
            sum = 0;
            count = new long[n][2];
            for (int i = 1; i <= n; i++) {
                sum += count(n - i, 0, m);
            }
            for (int i = m; i <= n; i++) {
                sum += count(n - i, 1, m);
            }
        }
        return n;
    }

    private static long[][] count;

    private static long count(int pos, int br, int m) {
        if (pos < 0) {
            return 0;
        } else if (pos == 0) {
            return 1;
        } else if (count[pos][br] == 0) {
            long rv = 0;
            if (br == 1) {
                for (int i = 1; i <= pos; i++) {
                    rv += count(pos - i, 0, m);
                }
            } else {
                for (int i = m; i <= pos; i++) {
                    rv += count(pos - i, 1, m);
                }
            }
            count[pos][br] = rv;
        }
        return count[pos][br];
    }

    public static void main(String[] args) {
        System.out.println(P115.solve(50, 1000000));
    }
}
