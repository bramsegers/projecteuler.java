package P351_P400;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Primes;

public class PE386 {

    // P(100000000)=528755790 (total time: 2 minutes 15 seconds)
    public static void main(String[] args) {
        new PE386().solve((long) 1E8);
    }

    void solve(long max) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        Primes p = new Primes(max);
        for (long n = 1; n <= max; n++) {
            List<Integer> key = signature(n, p);
            Integer val = map.get(key);
            map.put(key, val == null ? 1 : val + 1);
        }
        long sum = 0;
        for (List<Integer> k : map.keySet()) {
            sum += map.get(k) * maxlen(k);
        }
        System.out.format("P(%d)=%d%n", max, sum);
    }

    int maxlen(List<Integer> sig) {
        int sum = 0;
        for (int n : sig) {
            sum += n;
        }
        int[] len = new int[sum + 1];
        maxlen(0, 0, sig, len);
        int max = 0;
        for (int n : len) {
            max = Math.max(max, n);
        }
        return max;
    }

    void maxlen(int size, int pos, List<Integer> sig, int[] len) {
        if (pos == sig.size()) {
            len[size]++;
            return;
        }
        for (int i = 0; i <= sig.get(pos); i++) {
            maxlen(size + i, pos + 1, sig, len);
        }
    }

    List<Integer> signature(long n, Primes pr) {
        List<Integer> list = new ArrayList<>();
        long p = 0;
        while (n > 1) {
            p = pr.next(p);
            int e = 0;
            while (n % p == 0) {
                n /= p;
                e++;
            }
            if (e > 0) {
                list.add(e);
            }
            if (pr.contains(n)) {
                list.add(1);
                n = 1;
            }
        }
        //Collections.sort(list);
        return list;
    }

}
