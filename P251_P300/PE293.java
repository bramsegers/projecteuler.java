package P251_P300;

import java.util.HashSet;
import java.util.TreeMap;
import util.Util;

public class PE293 {

    public static void main(String[] args) {
        new PE293().solve(1000000000);
    }

    int limit;
    TreeMap<Long, String> map = new TreeMap<>();

    void solve(int nMax) {
        limit = nMax;
        solve(1, 2, "");
        map.remove(1L);
        HashSet<Long> pseud = new HashSet<>();
        for (long k : map.keySet()) {
            long p = k + 1;
            while (!Util.isPrime(++p)) {
            }
            pseud.add(p - k);
            System.out.println(k + "=" + map.get(k) + ", p=" + (p - k));
        }
        long sum = 0;
        for (long k : pseud) {
            sum += k;
        }
        System.out.println("Sum:" + sum);
    }

    void solve(long cur, int base, String path) {
        map.put(cur, path + "\b");
        int pow = 0;
        long p;
        while (cur * (p = (long) Math.pow(base, ++pow)) < limit) {
            solve(cur * p, (int) Util.nextPrime(base), path + base + "^" + pow + "*");
        }
    }

}
