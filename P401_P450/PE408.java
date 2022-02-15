package P401_P450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import util.Util;

public class PE408 {

    public static void main(String[] args) {
        new PE408().solve((int) 1e3);
        new PE408().solve((int) 1e7);
    }

    List<Point> list = new ArrayList<>();
    HashMap<Point, Long> map = new HashMap<>();

    long mod = 1000000007;
    long[] fac, inv;

    void solve(int N) {

        // build cache
        fac = new long[2 * N + 1];
        inv = new long[2 * N + 1];
        for (int i = 0; i <= 2 * N; i++) {
            fac[i] = (i == 0) ? 1 : fac[i - 1] * i;
            fac[i] %= mod;
            inv[i] = Util.modInv(fac[i], mod);
        }

        // build set of points
        int a = 1;
        for (int x = 1; x <= N; x += a) {
            a += 2;
            int b = 1;
            for (int y = 1; y <= N; y += b) {
                b += 2;
                int sq = (int) Math.sqrt(x + y);
                if (sq * sq == x + y) {
                    list.add(new Point(x, y));
                }
            }
        }

        // calculate
        long f = f(new Point(N, N));

        // show
        System.out.format("P(%d) mod %d = %d %n", N, mod, f);

    }

    long f(Point p) {
        if (map.get(p) == null) {
            int n = p.x + p.y;
            int k = p.x;
            long v = choose(n, k);
            for (Point q : list) {
                if (q.compareTo(p) < 0 && q.y <= p.y) {
                    n = p.x - q.x + p.y - q.y;
                    k = p.x - q.x;
                    v -= (f(q) * choose(n, k)) % mod;
                    v %= mod;
                }
            }
            map.put(p, v);
        }
        return map.get(p);
    }

    long choose(int n, int k) {
        long rv = fac[n];
        rv *= inv[k];
        rv %= mod;
        rv *= inv[n - k];
        rv %= mod;
        return rv;
    }

    class Point implements Comparable<Point> {

        int x, y;

        public Point(int i, int j) {
            x = i;
            y = j;
        }

        @Override
        public int compareTo(Point o) {
            int dx = x - o.x;
            return dx == 0 ? y - o.y : dx;
        }
    }
}
