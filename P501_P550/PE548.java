package P501_P550;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Primes;

public class PE548 {

    /*
    found: [1] 1
    found: [12, 1, 1] 454656
    found: [4, 4, 1] 29808
    found: [4, 1] 48
    found: [8, 1] 1280
    found: [30, 1, 1] 583041810432
    found: [12, 1] 28672
    found: [20, 1] 11534336
    found: [24, 1] 218103808
    found: [32, 1] 73014444032
    found: [36, 1] 1305670057984
    found: [14, 1, 1] 2342912
    found: [44, 1] 404620279021568
    found: [40, 1, 1] 1014849232437248
    found: [6, 1, 1] 2496
    found: [32, 1, 1] 2624225017856
    found: [34, 1, 1, 1] 467515780104192
    found: [42, 1, 1] 4446425022726144
    found: [18, 1, 1, 1, 1] 34753216512
    found: [18, 1, 1] 57409536
    found: [28, 1, 1, 1, 1, 1] 5806013294837760
    sum=12144044603581281
    BUILD SUCCESSFUL (total time: 4 minutes 55 seconds)
     */
    public static void main(String[] args) {
        new PE548().solve();
    }

    long max = (long) 1e16;
    Primes prm = new Primes((long) 1e8);
    Map<List<Integer>, Long> map1 = new HashMap<>();
    Map<List<Integer>, Long> map2 = new HashMap<>();

    void solve() {

        long sum = 0;
        solve(1, 0, 100, new ArrayList<>());

        for (List<Integer> list : map2.keySet()) {
            long n = map2.get(list);
            if (n > 0) {
                List<Integer> ps = primeSig(n);
                if (n == 1 || list.equals(ps)) {
                    System.out.println("found: " + list + " " + n);
                    sum += n;
                }
            }
        }

        System.out.println("sum=" + sum);
    }

    void solve(long p, long np, int maxe, List<Integer> list) {

        long len = chainLen(list);
        if (len > 0) {
            map2.put(list, len);
        }

        np = prm.next(np);
        int e = 0;
        while (p * np <= max && ++e <= maxe) {
            p *= np;
            List<Integer> list2 = new ArrayList<>(list);
            list2.add(e);
            solve(p, np, e, list2);
        }
    }

    long chainLen(List<Integer> list) {
        if (map1.get(list) != null) {
            return map1.get(list);
        }
        List<List<Integer>> lists = new ArrayList<>();
        split(0, list, new ArrayList<>(), lists);
        long rv = 0;
        for (List<Integer> q : lists) {
            if (!q.equals(list)) {
                rv += q.isEmpty() ? 1 : chainLen(q);
                if (rv > max) {
                    return -1;
                }
            }
        }
        map1.put(list, rv);
        return rv;
    }

    void split(int i, List<Integer> list, List<Integer> list2, List<List<Integer>> lists) {
        if (i == list.size()) {
            lists.add(list2);
            return;
        }
        for (int j = 0; j <= list.get(i); j++) {
            List<Integer> list3 = new ArrayList<>(list2);
            if (j > 0) {
                list3.add(j);
            }
            split(i + 1, list, list3, lists);
        }
    }

    List<Integer> primeSig(long n) {
        List<Integer> rv = new ArrayList<>();
        long p = 0;
        while (n > 1) {
            p = prm.next(p);
            if (p < 0) {
                rv.add(1);
                n = 1;
            } else {
                int e = 0;
                while (n % p == 0) {
                    n /= p;
                    e++;
                }
                if (e > 0) {
                    rv.add(e);
                }
                if (prm.contains(n)) {
                    rv.add(1);
                    n = 1;
                }
            }
        }
        Collections.sort(rv);
        Collections.reverse(rv);
        return rv;
    }

}
