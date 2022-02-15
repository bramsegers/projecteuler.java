package P351_P400;

//https://en.wikipedia.org/wiki/B%C3%A9zier_curve#Cubic_B.C3.A9zier_curves
public class PE363 {

    public static void main(String[] args) {
        new PE363().solve();
    }

    void solve() {
        long time = System.currentTimeMillis();
        System.out.format("v   = %.10f %n", binsearch(0, 1));
        System.out.format("len = %.10f %n", len);
        System.out.format("diff= %.10f %n", 100 * (len - Math.PI / 2) / (Math.PI / 2));
        System.out.format("time= %d ms %n", System.currentTimeMillis() - time);
    }

    double len;

    double binsearch(double lo, double hi) {

        double v = (lo + hi) / 2;
        double lastx = 1, lasty = 0;
        double area = 0;
        len = 0;

        int steps = (int) 1e6;
        for (int i = 1; i <= steps; i++) {
            double t = 1D * i / steps;
            double it = 1 - t;
            double bx = it * it * it + 3 * t * it * it + 3 * t * t * it * v;
            double by = 3 * t * it * it * v + 3 * t * t * it + t * t * t;
            len += dist(lastx, lasty, bx, by);
            area += area(lastx, lasty, bx, by);
            lastx = bx;
            lasty = by;
        }

        double diff = area - 0.25 * Math.PI;
        return Math.abs(diff) < (1e-12)
                ? v
                : diff < 0
                        ? binsearch(v, hi)
                        : binsearch(lo, v);
    }

    double dist(double x1, double y1, double x2, double y2) {
        x1 -= x2;
        y1 -= y2;
        return Math.sqrt(x1 * x1 + y1 * y1);
    }

    double area(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * y1 + (x1 - x2) * (y2 - y1) / 2;
    }

}
