package P151_P200;

public class PE163 {

    // https://oeis.org/A210687
    public static void main(String[] args) {
        new PE163().solve(36);
    }

    void solve(long n) {
        long p = (1678 * n * n * n
                + 3117 * n * n
                + 88 * n
                - 345 * (n % 2)
                - 320 * (n % 3)
                - 90 * (n % 4)
                - 288 * ((n * n * n - n * n + n) % 5))
                / 240;
        System.out.format("P(%d)=%d%n", n, p);
    }
}
