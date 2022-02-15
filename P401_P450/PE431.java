package P401_P450;

public class PE431 {

    public static void main(String[] args) {
        new PE431().solve();
        /*        
        (V,x)=(400,1.609758500902)
        (V,x)=(441,2.806011946623)
        (V,x)=(484,3.678327915606)
        (V,x)=(529,4.426403291775)
        (V,x)=(576,5.109420207443)
        (V,x)=(625,5.756107189484)
        ∑x=23.386029052
        BUILD SUCCESSFUL (total time: 17 seconds)
         */
    }

    double r = 6;
    double a = 40;

    double steps = 1e5;
    double dif = 1e-8;

    void solve() {
        double x, sum = 0;
        long vmin = (long) V(0.01);
        long vmax = (long) V(r);
        for (long v = vmin; v <= vmax; v++) {
            long sqv = (long) Math.sqrt(v);
            if (sqv * sqv == v) {
                x = binsearch(v, 0, r);
                sum += x;
                System.out.format("(V,x)=(%d,%.12f)%n", v, x);
            }
        }
        System.out.format("∑x=%.9f%n", sum);
    }

    double binsearch(double v, double f, double t) {
        double m = (f + t) / 2;
        double v2 = V(m);
        double d = Math.abs(v - v2);
        return d < dif
                ? m
                : v < v2
                        ? binsearch(v, f, m)
                        : binsearch(v, m, t);
    }

    double V(double x) {

        double pi = Math.PI;
        double tana = Math.tan(a * pi / 180);
        double h1, h2, r1, x1, A, H, sum;

        h1 = (r + x) * tana;
        h2 = (r - x) * tana;
        sum = pi * r * r * h1;
        sum -= pi * (r - x) * (r - x) * h2 / 3;

        for (long i = 1; i < 2 * steps; i += 2) {
            r1 = r - x + (x * i / steps);
            x1 = (x * x + r * r - r1 * r1) / (2 * x);
            A = intg(r1, x1 - x) - intg(r1, -r1);
            A += intg(r, r) - intg(r, x1);
            H = x * (i + 1) / steps;
            H -= x * (i - 1) / steps;
            sum -= tana * A * H;
        }

        return sum;
    }

    double intg(double r, double x) {
        return Math.sqrt(r * r - x * x) * x + r * r * Math.asin(x / r);
    }

}
