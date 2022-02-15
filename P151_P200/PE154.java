package P151_P200;

public class PE154 {

    public static void main(String[] args) {
        new PE154().solve(200000);
    }

    void solve(int n) {
        long start = System.currentTimeMillis();
        long count = 0;
        int[] factors2 = new int[n + 1];
        int[] factors5 = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            factors2[i] = factors(2, i);
            factors5[i] = factors(5, i);
        }
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= (n - i) / 2; j++) {
                int k = n - i - j;
                int factors_2 = factors2[n] - factors2[i] - factors2[j] - factors2[k];
                int factors_5 = factors5[n] - factors5[i] - factors5[j] - factors5[k];
                count += factors_5 < 12 || factors_2 < 12 ? 0 : i == k ? 1 : i == j || j == k ? 3 : 6;
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("%d%nElapsed:%dms%n", count, end - start);
    }

    int factors(int f, int n) {
        int sum = 0;
        int t = 1;
        while ((t *= f) <= n) {
            sum += n / t;
        }
        return sum;
    }

}
