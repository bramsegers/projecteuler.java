package P201_P250;

public class PE226 {

    public static void main(String[] args) {
        new PE226().solve((long) 1e7);
    }

    void solve(long steps) {

        double px = solve(0, 0.25);
        double py = blanc(px);
        double area = 0;

        double dx = (0.50 - px) / steps;
        for (double x = px; x < 0.50; x += dx) {
            double y = blanc(x);
            area += dx * y;
        }

        double q = (0.25 - px) / 0.25;
        double a = Math.asin(q);
        a += Math.PI * 0.50;
        a *= 0.25 * 0.25 * 0.50;
        area += a;

        area -= (0.50 - px) * py;
        area -= 0.50 * (0.25 - px) * (0.50 - py);
        area -= 0.25 * (0.50 - py);

        System.out.format("%.8f%n", area);
    }

    double solve(double f, double t) {
        double m = (f + t) / 2;
        double b = blanc(m);
        double dist2 = (0.25 - m) * (0.25 - m) + (0.50 - b) * (0.50 - b);
        double diff2 = dist2 - 0.0625;
        return Math.abs(diff2) < 1e-12
                ? m
                : diff2 > 0
                        ? solve(m, t)
                        : solve(f, m);
    }

    double blanc(double x) {
        double sum = 0, i = 1;
        long p2 = 1;
        while (i > 1e-12) {
            i = s(x * p2) / p2;
            sum += i;
            p2 *= 2;
        }
        return sum;
    }

    double s(double d) {
        double e = Math.round(d);
        return e > d ? e - d : d - e;
    }

}
