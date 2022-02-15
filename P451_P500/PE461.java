package P451_P500;

import static java.lang.Math.E;
import static java.lang.Math.PI;
import java.util.Arrays;

public class PE461 {

    public static void main(String[] args) {
        new PE461().solve(10000);
    }

    double[] f;
    int lim;
    double mindiff = PI;
    double minab, mincd;
    int A, B, C, D;

    void solve(int n) {
        lim = (int) (n * Math.log(PI + 1) / Math.log(E));
        f = new double[lim + 1];
        for (int k = 1; k <= lim; k++) {
            f[k] = Math.pow(E, 1D * k / n) - 1;
        }
        double[] set = setAB(null);
        Arrays.sort(set);
        for (int j = 0; j < set.length; j++) {
            int i = -Arrays.binarySearch(set, PI - set[j]);
            if (i >= 2) {
                checkMinDiff(set[j], set[i - 2]);
                checkMinDiff(set[j], set[i - 1]);
            }
        }
        getABCD(minab, mincd);
        System.out.format("mindiff = %e%n", mindiff);
        System.out.format("a,b,c,d = %d,%d,%d,%d%n", A, B, C, D);
        System.out.format("g(%d) = %d%n", n, A * A + B * B + C * C + D * D);
    }

    void checkMinDiff(double ab, double cd) {
        double diff = Math.abs(ab + cd - PI);
        if (diff < mindiff) {
            mindiff = diff;
            minab = ab;
            mincd = cd;
        }
    }

    double[] setAB(double[] set) {
        int i = 0;
        for (int a = 1; a <= lim && 2 * f[a] < PI; a++) {
            for (int b = a; b <= lim && f[a] + f[b] < PI; b++) {
                if (set != null) {
                    set[i] = f[a] + f[b];
                }
                i++;
            }
        }
        return set != null ? set : setAB(new double[i]);
    }

    void getABCD(double ab, double cd) {
        for (int a = 1; a <= lim && 2 * f[a] < PI; a++) {
            for (int b = a; b <= lim && f[a] + f[b] < PI; b++) {
                if (f[a] + f[b] == ab) {
                    A = a;
                    B = b;
                }
                if (f[a] + f[b] == cd) {
                    C = a;
                    D = b;
                }
            }
        }
    }

}
