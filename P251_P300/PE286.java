package P251_P300;
  
import java.util.Map;
import java.util.TreeMap;
import util.Util;

public class PE286 {

    public static void main(String[] args) {
        new PE286().solve(50, 20, 0.02D);
        new PE286().solve2(50, 20, 0.02D); 
    }

    void solve(int n, int r, double p) {
        Map<Integer, Double> map = new TreeMap<>();
        Map<Integer, Double> temp = new TreeMap<>();
        map.put(1, -1D);
        map.put(0, 1D);
        for (int i = 1; i < n; i++) {
            for (int k : map.keySet()) {
                temp.put(k + 1, ((i + 1) * -map.get(k)));
            }
            for (int k : temp.keySet()) {
                Double v = map.get(k);
                map.put(k, (v == null ? 0 : v) + temp.get(k));
            }
            temp.clear();
        }

        double[] f = new double[n + 1];
        f[0] = -p;
        double s = ((n - r) & 1) == 0 ? 1 : -1;
        for (int i = n - r; i <= n; i++) {
            f[i] = s * map.get(i) * Util.nChooseR(i, n - r);
        }

        //.. too much roundoff error.. :(

    }

    double eval(double[] f, double x) {
        double rv = f[0];
        for (int i = 1; i < f.length; i++) {
            rv += f[i] * Math.pow(x, -i);
        }
        return rv;
    }

    void solve2(int n, int r, double pr) {
        double q = n + 2, p = getP(q, n, r);
        double inc = 0.5, eps = 0.00000000000001;
        while (Math.abs(pr - p) > eps) {
            double cand = getP(q + inc, n, r);
            if (cand >= pr) {
                p = cand;
                q += inc;
            }
            inc /= 2.0;
        }
        System.out.println(q);
    }

    double getP(double q, int n, int r) {
        double[] prob = new double[n + 1];
        prob[0] = 1;
        for (int i = 1; i <= n; i++) {
            double[] nextProb = new double[n + 1];
            double pSuccess = 1 - i / q;
            double pFail = 1 - pSuccess;
            for (int j = 0; j < i; j++) {
                nextProb[j + 1] += prob[j] * pSuccess;
                nextProb[j] += prob[j] * pFail;
            }
            prob = nextProb;
        }
        return prob[r];
    }

}
