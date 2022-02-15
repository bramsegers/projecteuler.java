package P451_P500;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import util.Primes;

public class PE451 {

    //S(20000000)=153651073760956
    //BUILD SUCCESSFUL (total time: 3 minutes 34 seconds)
    public static void main(String[] args) {
        new PE451().solve(20000000);
    }

    TreeMap<Long, Integer> m1, m2, m3;
    List<Integer> divs = new ArrayList<>();

    void solve(int N) {
        long[] arr = new long[N + 1];
        Primes pr = new Primes(N + 1);
        m1 = pr.factorize(1);
        m2 = pr.factorize(2);
        for (long i = 2; i <= N; i++) {
            m3 = pr.factorize(i + 1);
            divisors(i, N);
            for (int j : divs) {
                arr[j] = Math.max(arr[j], j - i);
            }
            m1 = m2;
            m2 = m3;
        }
        long sum = 0;
        for (int n = 3; n <= N; n++) {
            sum += arr[n];
        }
        System.out.format("S(%d)=%d%n", N, sum);
    }

    void divisors(long i, long N) {
        for (long k : m3.keySet()) {
            Integer v = m1.get(k);
            v = v == null ? 0 : v;
            m1.put(k, v + m3.get(k));
        }
        divs.clear();
        divisors(1, m1.firstKey(), i, N);
    }

    void divisors(long d, Long p, long i, long N) {
        if (d > N) {
            return;
        }
        if (p == null) {
            if (d > i) {
                divs.add((int) d);
            }
            return;
        }
        long pe = 1;
        for (int e = 0; e <= m1.get(p); e++) {
            divisors(d * pe, m1.higherKey(p), i, N);
            pe *= p;
        }
    }

}
