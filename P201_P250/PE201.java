package P201_P250;

public class PE201 {

    public static void main(String[] args) {
        new PE201().solve(100, 50);
    }

    void solve(int n, int k) {

        // init S
        int[] S = new int[n];
        for (int i = 1; i <= n; i++) {
            S[i - 1] = i * i;
        }

        // init maxSum and minSum
        int maxSum = 0;
        int minSum = 0;
        for (int i = 0; i < k; i++) {
            minSum += S[i];
            maxSum += S[n - i - 1];
        }

        // init x and y
        int[][] x = new int[k + 1][maxSum + 1];
        int[][] y = new int[k + 1][maxSum + 1];
        y[0][0] = 1;

        // iterate over n
        for (int i = 1; i <= n; i++) {
            x[0][0] = 1;
            for (int j = 1; j <= k; j++) {
                int e = S[i - 1];
                for (int s = 0; s < e; s++) {
                    x[j][s] = y[j][s];
                }
                for (int s = 0; s <= maxSum - e; s++) {
                    x[j][s + e] = y[j - 1][s] + y[j][s + e];
                }
            }
            int[][] t = x;
            x = y;
            y = t;
        }

        // sum of unique K-subset sums
        int sum = 0;
        for (int s = minSum; s <= maxSum; s++) {
            if (y[k][s] == 1) {
                sum += s;
            }
        }
        System.out.format("P(%d,%d)=%d%n", n, k, sum);
    }

}
