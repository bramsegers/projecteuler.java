package P201_P250;

public class PE211 {

    public static void main(String[] args) {
        new PE211().solve(64000000);
    }

    void solve(int nMax) {
        long sum = 0;
        long[] divSum = new long[nMax + 1];
        for (int n = 1; n < nMax; n++) {
            for (int m = n; m < nMax; m += n) {
                divSum[m] += 1L * n * n;
            }
        }
        for (int n = 1; n < nMax; n++) {
            long sqrt = (long) Math.sqrt(divSum[n]);
            if (sqrt * sqrt == divSum[n]) {
                sum += n;
            }
        }
        System.out.format("P(%d)=%d%n", nMax, sum);
    }

}
