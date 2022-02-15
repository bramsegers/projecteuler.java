package P501_P550;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

public class PE517 {

    public static void main(String[] args) {
        //new Test().test();
        new PE517().solve();
    }

    int N1 = 10000000;
    int N2 = 10010000;
    int MOD = 1000000007;

    void solve() { 
        long sum = 0; 
        BitSet bs = Util.getPrimesBS(N2);
        for (int n = bs.nextSetBit(N1); n >= 0; n = bs.nextSetBit(n + 1)) {
            System.out.println(n);
            sum++;
            double sq = Math.sqrt(n);
            for (int a = 0; a <= sq - 1; a++) {
                int b = (int) (n - sq * (a + 1));
                long c = choose_mod_one(a + b + 1, a + 1, MOD); 
                sum = (sum + c) % MOD;
            }
        }
        System.out.println("**=" + sum); 
    }

    long choose_mod_one(long n, long k, long p) {
        if (k < p) {
            return choose_mod_two(n, k, p);
        }
        long q_n, r_n, q_k, r_k, choose;
        q_n = n / p;
        r_n = n % p;
        q_k = k / p;
        r_k = k % p;
        choose = choose_mod_two(r_n, r_k, p);
        choose *= choose_mod_one(q_n, q_k, p);
        return choose % p;
    }

    // Preconditions: 0 <= k <= min(n,p-1); p > 1 prime
    long choose_mod_two(long n, long k, long p) {
        n %= p;
        if (n < k) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        if (k > n / 2) {
            k = n - k;
        }
        long num = n, den = 1;
        for (n = n - 1; k > 1; --n, --k) {
            num = (num * n) % p;
            den = (den * k) % p;
        }
        den = invert_mod(den, p);
        return (num * den) % p;
    }

    long invert_mod(long k, long m) {
        if (m == 0) {
            return (k == 1 || k == -1) ? k : 0;
        }
        if (m < 0) {
            m = -m;
        }
        k %= m;
        if (k < 0) {
            k += m;
        }
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

    void test() {
        long sum = 0;
        for (N = N1; N <= N2; N++) {
            if (!Util.isPrime(N)) {
                continue;
            }
            SUM = 0;
            spotPattern();
            sum = (sum + SUM) % MOD;
        }
        System.out.println(sum);
    }

    long N, SUM;

    void spotPattern() {
        Map<G, Long> map = new HashMap<>();
        map.put(new G(0, 0), 1L);
        while (!map.isEmpty()) {
            List<G> list = new ArrayList<>(map.keySet());
            Collections.sort(list, new Comparator<G>() {
                @Override
                public int compare(G o1, G o2) {
                    if (o1.i == o2.i) {
                        return o1.sqrtn > o2.sqrtn ? 1 : -1;
                    }
                    return o1.i > o2.i ? 1 : -1;
                }
            });
            String s = "";
            for (G g : list) {
                s += " " + g + "" + map.get(g);
            }
            System.out.println(s);
            map = next(map);
        }
        System.out.println(SUM);
    }

    Map<G, Long> next(Map<G, Long> map) {
        Map<G, Long> map2 = new HashMap<>();
        for (G g : map.keySet()) {
            for (int j = 0; j < 2; j++) {
                G g2 = (j == 0)
                        ? new G(g.sqrtn, g.i - 1)
                        : new G(g.sqrtn - 1, g.i);
                if (g2.isOne) {
                    SUM = (SUM + map.get(g)) % MOD;
                } else {
                    map2.put(g2, map2.get(g2) == null
                            ? map.get(g)
                            : (map2.get(g2) + map.get(g)) % MOD);
                }
            }
        }
        return map2;
    }

    private class G {

        long sqrtn, i;
        boolean isOne;

        public G(long sqrtn, long i) {
            this.sqrtn = sqrtn;
            this.i = i;
            isOne = (double) N + (double) (sqrtn - 1) * Math.sqrt(N) + i < 0;
        }

        @Override
        public String toString() {
            return "(" + sqrtn + "," + i + ")";
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + (int) (this.sqrtn ^ (this.sqrtn >>> 32));
            hash = 79 * hash + (int) (this.i ^ (this.i >>> 32));
            hash = 79 * hash + (this.isOne ? 1 : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final G other = (G) obj;
            if (this.sqrtn != other.sqrtn) {
                return false;
            }
            if (this.i != other.i) {
                return false;
            }
            if (this.isOne != other.isOne) {
                return false;
            }
            return true;
        }

    }

}
