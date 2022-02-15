package P201_P250;

public class PE231 {

    public static void main(String[] args) {
        new PE231().solve(20000000, 15000000);
    }

    void solve(int n, int k) {
        long start = System.currentTimeMillis();
        boolean[] isComposite = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!isComposite[i]) {
                for (int j = i; i * j <= n; j++) {
                    isComposite[i * j] = true;
                }
            }
        }
        long[] divisors = new long[n + 1];
        setDivisors(divisors, isComposite, n, true);
        setDivisors(divisors, isComposite, k, false);
        setDivisors(divisors, isComposite, n - k, false);
        long sum = 0;
        for (int i = 2; i <= n; i++) {
            sum += i * divisors[i];
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d,%d)=%d%nElapsed:%dms%n", n, k, sum, end - start);
    }

    void setDivisors(long[] divisors, boolean[] isComposite, int nMax, boolean add) {
        for (int n = 2; n <= nMax; n++) {
            if (!isComposite[n]) {
                long sum = 0;
                long t = 1;
                while ((t *= n) <= nMax) {
                    sum += nMax / t;
                }
                divisors[n] += add ? sum : -sum;
            }
        }
    }

}
