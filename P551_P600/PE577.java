package P551_P600;

public class PE577 {

    public static void main(String[] args) {
        new PE577().solve2(12345);
    } 

    void solve2(int N) {
        long sum = 0;
        for (int n = 1; n <= N; n++) {
            long s = solve(n);
            sum += s;
            System.out.println(n + " " + s + " " + sum);
        }
        System.out.println(sum);
    }

    long solve(long n) {
        long x, sum = 0;
        boolean more = true;
        for (long r = 1; r <= n && more; r++) {
            more = false;
            for (long y = 0; (x = r - 2 * y) > 0; y++) {
                long y2 = r + 2 * x + y;
                if (y2 <= n) {
                    long t = n - y2 + 1;
                    long t2 = t * (t + 1) / 2;
                    sum += t2;
                    more = true;
                }
            }
        }
        return sum;
    }
}
