package P551_P600;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE581 {

    public static void main(String[] args) {
        new PE581().solve();
    }

    int smooth = 47;
    long limit = (long) 1e13;
    Set<Long> set = new HashSet<>();
    List<Integer> primes = Util.getPrimes(47);

    void solve() {
        search(1, 1);
        long sum = 0;
        for (long s : set) {
            long t = s;
            while ((t *= 2) < limit) {
                sum += set.contains(t - 1) ? t - 1 : 0;
                sum += set.contains(t + 1) ? t : 0;
            }
        }
        System.out.println(sum);
    }

    void search(long prod, int pos) {
        if (pos == primes.size()) {
            set.add(prod);
            return;
        }
        while (prod < limit) {
            search(prod, pos + 1);
            prod *= primes.get(pos);
        }

    }
}
