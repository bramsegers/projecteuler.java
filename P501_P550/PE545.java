package P501_P550;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import util.Primes;

//https://en.wikipedia.org/wiki/Faulhaber%27s_formula
//https://en.wikipedia.org/wiki/Bernoulli_number
//http://mathworld.wolfram.com/BernoulliNumber.html
//https://oeis.org/A027642
public class PE545 {

    public static void main(String[] args) {
        new PE545().solve(100000);
    }

    Primes pr = new Primes(1000000000);
    TreeMap<Long, Integer> map = pr.factorize(308);
    ArrayList<Long> list = new ArrayList<>();

    void solve(int N) {
        list.add(1L);
        long p = 11;
        while (list.size() < N) {
            p = pr.next(p);
            map.put(p, 1);
            if (valid(1, 2L)) {
                list.add(p);
            }
            map.remove(p);
        }
        long q;
        for (int i = 1; (q = list.get(i)) * q <= p; i++) {
            long r = q;
            for (int e = 1; q <= p; e++) {
                map.put(r, e);
                search(e, q, p, i + 1);
                map.remove(r);
                q *= r;
            }
        }
        Collections.sort(list);
        long F = list.get(N - 1) * 308;
        System.out.format("F(%d)=%d%n", N, F);

    }

    boolean valid(long d, Long p) {
        if (p == null) {
            return d == 1
                    || d == 2
                    || d == 4
                    || d == 22
                    || d == 28
                    || !pr.contains(d + 1);
        }
        long pe = 1;
        for (int e = 0; e <= map.get(p); e++) {
            if (!valid(d * pe, map.higherKey(p))) {
                return false;
            }
            pe *= p;
        }
        return true;
    }

    void search(int tot, long p, long max, int i) {
        long q = list.get(i), r = q;
        if (p * q > max) {
            if (tot > 1 && valid(1, 2L)) {
                list.add(p);
            }
            return;
        }
        search(tot, p, max, i + 1);
        int e = 1;
        while (p * q <= max) {
            map.put(r, e);
            search(tot + e, p * q, max, i + 1);
            map.remove(r);
            q *= r;
            e++;
        }
    }

}
