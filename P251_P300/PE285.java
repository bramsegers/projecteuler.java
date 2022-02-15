package P251_P300;

public class PE285 {

    public static void main(String[] args) {
        new PE285().solve(100000);
    }

    void solve(int turns) {
        double sum = 0;
        for (long k = 1; k <= turns; k++) {
            double xk1 = Math.sqrt((2 * k - 1) * (2 * k - 1) - 4) / 2 - 1;
            double xk2 = Math.sqrt((2 * k + 1) * (2 * k + 1) - 4) / 2 - 1;
            double F1 = k == 1 ? 0 : F1(xk1, k) - F1(0, k);
            double F2 = F2(xk2, k) - F2(0, k);
            sum += (F2 - F1) * k;
        }
        System.out.format("P(%d)=%.5f%n", turns, sum);
    }

    double F1(double kx, double k) {
        double a = kx + 1;
        a = 2 * a * Math.sqrt(4 * k * k - 4 * a * a - 4 * k + 1);
        a /= 4 * kx * kx - 4 * k * k + 8 * kx + 4 * k + 3;
        a = (2 * k - 1) * (2 * k - 1) * Math.atan(a);
        double sq = Math.sqrt(-4 * kx * kx + 4 * k * k - 8 * kx - 4 * k - 3);
        return (2 * (kx * (sq - 4) + sq) - a) / (8 * k * k);
    }

    double F2(double kx, double k) {
        double a = kx + 1;
        a = 2 * a * Math.sqrt(4 * k * k - 4 * a * a + 4 * k + 1);
        a /= 4 * kx * kx - 4 * k * k + 8 * kx - 4 * k + 3;
        a = (2 * k + 1) * (2 * k + 1) * Math.atan(a);
        double sq = Math.sqrt(-4 * kx * kx + 4 * k * k - 8 * kx + 4 * k - 3);
        return (2 * (kx * (sq - 4) + sq) - a) / (8 * k * k);
    }

}
