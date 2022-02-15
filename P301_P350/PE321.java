package P301_P350;

public class PE321 {

    /*
     * test() -> M(n) = n*(n+2) (https://oeis.org/A005563)
     * n*(n+2)=t*(t+1)/2 -> (n,t)=(0,0),(1,2),(3,5),(10,15),(22,32),(63,90),.. 
     * (https://oeis.org/A006451) -> t(n) = 6*t(n-2) - t(n-4) + 2
     */
    public static void main(String[] args) {
        //new Test().test(15);
        new PE321().solve(40);
    }

    void solve(int max) {
        long[] t = new long[max + 1];
        t[1] = 2;
        t[2] = 5;
        t[3] = 15;
        long sum = 14;
        for (int i = 4; i <= max; i++) {
            t[i] = 6 * t[i - 2] - t[i - 4] + 2;
            double sqrt = Math.sqrt(t[i] / 2D) * Math.sqrt(t[i]);
            long n = Math.round(sqrt) - 1;
            sum += n;
        }
        System.out.format("P(%d)=%d%n", max, sum);
    }

    void test(int max) {
        for (int n = 1; n <= max; n++) {
            int[] m = new int[2 * n + 1];
            int[] r = new int[2 * n + 1];
            for (int i = 0; i < n; i++) {
                m[i] = 1;
                m[2 * n - i] = -1;
                r[i] = -1;
                r[2 * n - i] = 1;
            }
            System.out.print(n + " ");
            search(m, r, 0, true);
        }
    }

    void search(int[] a, int[] r, int steps, boolean start) {
        if (java.util.Arrays.equals(a, r)) {
            System.out.println(steps);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            int[] b = a.clone();
            if (!start && a[i] == 1) {
                if (i < a.length - 1 && a[i + 1] == 0) {
                    b[i] = 0;
                    b[i + 1] = 1;
                    search(b, r, steps + 1, false);
                } else if (i < a.length - 2 && a[i + 1] != 0 && a[i + 2] == 0) {
                    b[i] = 0;
                    b[i + 2] = 1;
                    search(b, r, steps + 1, false);
                }
            } else if (a[i] == -1) {
                if (i > 0 && a[i - 1] == 0) {
                    b[i] = 0;
                    b[i - 1] = -1;
                    search(b, r, steps + 1, false);
                } else if (i > 1 && a[i - 1] != 0 && a[i - 2] == 0) {
                    b[i] = 0;
                    b[i - 2] = -1;
                    search(b, r, steps + 1, false);
                }
            }
        }
    }
}
