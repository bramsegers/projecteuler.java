package P451_P500;

public class PE485 {

    public static void main(String[] args) {
        new PE485().solve(1000, 10);
        new PE485().solve(100000000, 100000);
    }

    int curMax;
    int[] div;

    void solve(int u, int k) {
        curMax = 0;
        div = new int[u + 1];
        for (int i = 1; i <= u; i++) {
            int j = i;
            while (j <= u) {
                div[j]++;
                j += i;
            }
        }
        long s = S(u, k);
        System.out.println(s);
    }

    long S(int u, int k) {
        long s = 0;
        for (int n = 1; n <= u - k + 1; n++) {
            s += M(n, k);
        }
        return s;
    }

    int M(int n, int k) {
        curMax = div[n - 1] < curMax
                ? Math.max(curMax, div[n + k - 1])
                : 0;
        if (curMax == 0) {
            for (int j = n; j <= n + k - 1; j++) {
                curMax = Math.max(curMax, div[j]);
            }
        }
        return curMax;
    }

}
