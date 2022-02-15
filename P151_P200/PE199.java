package P151_P200;

public class PE199 {

    public static void main(String[] args) {
        new PE199().solve(10);
    }

    void solve(int it) {
        double D = Math.sqrt(3) * 2 / 3 + 1;
        double d = 3;
        d += 1 * solve(it, 1, 1, 1);
        d += 3 * solve(it, -D, 1, 1);
        d /= D * D;
        System.out.format("P(%d) = %.8f %n", it, 1 - d);
    }

    double solve(int i, double a, double b, double c) {
        if (i == 0) {
            return 0;
        }
        double d = binsearch(0, c, a, b, c);
        double rv = d * d;
        rv += solve(i - 1, a, b, d);
        rv += solve(i - 1, a, c, d);
        rv += solve(i - 1, b, c, d);
        return rv;
    }

    double binsearch(double f, double t, double a, double b, double c) {
        double m = (f + t) / 2;
        double d = diff(m, a, b, c);
        return Math.abs(d) < (1e-10)
                ? m
                : d < 0
                        ? binsearch(f, m, a, b, c)
                        : binsearch(m, t, a, b, c);
    }

    double diff(double d, double a, double b, double c) {
        double x1 = ((a + b) * (a + b) + (a + c) * (a + c) - (b + c) * (b + c)) / (2 * (a + b));
        double y1 = Math.sqrt((a + c) * (a + c) - x1 * x1);
        double x2 = ((a - b) * (a + b + 2 * d)) / (2 * (a + b)) + (a + b) / 2;
        double y2 = Math.sqrt((a + d) * (a + d) - x2 * x2);
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) - (c + d) * (c + d);
    }

}
