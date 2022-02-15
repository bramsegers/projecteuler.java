package P401_P450;

public class PE412 {

    /*
     * Shapes and Hook Numbers - Numberphile
     * https://www.youtube.com/watch?v=vgZhrEs4tuk    
     *
     * Young tableau
     * https://en.wikipedia.org/wiki/Young_tableau#Dimension_of_a_representation
     */
    public static void main(String[] args) {
        new PE412().solve(10, 5, 76543217);
        new PE412().solve(10000, 5000, 76543217);
    }

    void solve(int m, int n, int mod) {
        long f = modFact(m * m - n * n, mod);
        f = divHooks(f, mod, 1, 2 * m - 1, m - n, m - n);
        f = divHooks(f, mod, 2, m - 1, m - n, n);
        System.out.format("LC(%d,%d) mod %d = %d %n", m, n, mod, f);
    }

    long modFact(int n, int mod) {
        long f = 1;
        for (int i = 2; i <= n; i++) {
            f *= i;
            f %= mod;
        }
        return f;
    }

    long divHooks(long f, int mod, int m, int a, int r, int c) {
        for (int p = 1; p < r + c; p++) {
            int x = Math.min(r + c - p, c);
            int y = Math.min(p, r);
            int z = Math.min(x, y);
            long im = invMod(a, mod);
            for (int i = 0; i < z * m; i++) {
                f *= im;
                f %= mod;
            }
            a--;
        }
        return f;
    }

    long invMod(long k, long m) {
        boolean neg = true;
        long p1 = 1, p2 = 0, k1 = k, m1 = m, q, r, temp;
        while (k1 > 0) {
            q = m1 / k1;
            r = m1 % k1;
            temp = q * p1 + p2;
            p2 = p1;
            p1 = temp;
            m1 = k1;
            k1 = r;
            neg = !neg;
        }
        return neg ? m - p2 : p2;
    }
}
