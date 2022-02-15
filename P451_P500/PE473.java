package P451_P500;

import java.util.TreeMap;

//https://en.wikipedia.org/wiki/Golden_ratio_base
//35856681704365
//BUILD SUCCESSFUL (total time: 4 minutes 30 seconds)
public class PE473 {

    public static void main(String[] args) {
        new PE473().solve((long) 1e10);
    }

    long max;
    double phi = (Math.sqrt(5) + 1) / 2;
    TreeMap<Integer, Root> roots = new TreeMap<>();

    void solve(long N) {
        max = N;
        int imax = (int) (Math.log(max) / Math.log(phi));
        Root r0 = new Root(0, 1, 0);
        Root r1 = new Root(1, 0, 1);
        roots.put(0, r0);
        roots.put(1, r1);

        for (int i = 2; i <= imax; i++) {
            Root r = new Root(i, r0.a + r1.a, r0.b + r1.b);
            roots.put(i, r);
            r0 = r1;
            r1 = r;
        }

        r0 = roots.get(0);
        r1 = roots.get(1);
        for (int i = -1; i >= -imax - 1; i--) {
            long a = Math.abs(r0.a) + Math.abs(r1.a);
            long b = Math.abs(r0.b) + Math.abs(r1.b);
            boolean odd = ((-i) & 1) == 1;
            Root r = new Root(i, odd ? -a : a, odd ? b : -b);
            roots.put(i, r);
            r1 = r0;
            r0 = r;
        }

        for (int i : roots.keySet()) {
            System.out.println(i + " " + roots.get(i));
        }

        long s = solve(0, imax, 0, 0, 0) + 1;
        System.out.format("S(%d)=%d%n", N, s);
    }

    long solve(int last, int i, double v, long a, long b) {
        if (v > max) {
            return 0;
        }
        if (i == 0) {
            return b == 0 ? a : 0;
        }
        long rv = 0;
        rv += solve(0, i - 1, v, a, b);
        if (last == 0) {
            Root r1 = roots.get(i);
            Root r2 = roots.get(-i - 1);
            v += r1.v + r2.v;
            a += r1.a + r2.a;
            b += r1.b + r2.b;
            rv += solve(1, i - 1, v, a, b);
        }
        return rv;
    }

    class Root {

        long a, b;
        double v;

        Root(long i, long a, long b) {
            this.a = a;
            this.b = b;
            this.v = Math.pow(phi, i);
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,%f)", a, b, v);
        }

    }

}
