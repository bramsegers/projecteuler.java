package P201_P250;

public class PE235 {

    /*
     http://en.wikipedia.org/wiki/Newton%27s_method
     */
    public static void main(String[] args) {
        new PE235().solve();
    }

    void solve() {
        // r must be just over 1
        // r must stay within range of double
        // works for r0 somewhere in range [1.01 - 1.1]
        double r = 1.1;
        double lastr = 0;
        while (lastr != r) {
            double f = 600000000000D;
            double fderv = 0;
            for (int k = 1; k <= 5000; k++) {
                f += (900D - 3 * k) * Math.pow(r, k - 1);
                fderv += (k == 1) ? 0 : (900D - 3 * k) * (k - 1) * Math.pow(r, k - 2);
            }
            System.out.format("r=%.12f, off by %f%n", r, f);
            lastr = r;
            r = r - (f / fderv);
        }
    }

}
