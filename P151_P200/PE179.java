package P151_P200;

public class PE179 {

    public static void main(String[] args) {
        new PE179().solve(10000000);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long sum = 0;
        int[] divisors = new int[nMax];
        for (int n = 2; n < nMax; n++) {
            for (int i = n; i < nMax; i += n) {
                divisors[i]++;
            }
        }
        for (int i = 2; i < nMax; i++) {
            sum += divisors[i] == divisors[i - 1] ? 1 : 0;
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }

}
