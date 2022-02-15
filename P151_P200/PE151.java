package P151_P200;

public class PE151 {

    public static void main(String[] args) {
        new PE151().solve();
    }

    void solve() {
        long start = System.currentTimeMillis();
        f(1.0, 1, 1, 1, 1);
        long end = System.currentTimeMillis();
        System.out.format("P=%.6f%nElapsed:%dms%n", expected, end - start);
    }

    static double expected;

    static void f(double p, int a2, int a3, int a4, int a5) {
        int sum = a2 + a3 + a4 + a5;
        if (sum == 0) {
            return;
        }
        if (sum == 1 && a5 == 0) {
            expected += p;
        }
        if (a2 > 0) {
            f((p * a2) / sum, a2 - 1, a3 + 1, a4 + 1, a5 + 1);
        }
        if (a3 > 0) {
            f((p * a3) / sum, a2, a3 - 1, a4 + 1, a5 + 1);
        }
        if (a4 > 0) {
            f((p * a4) / sum, a2, a3, a4 - 1, a5 + 1);
        }
        if (a5 > 0) {
            f((p * a5) / sum, a2, a3, a4, a5 - 1);
        }
    }

}
