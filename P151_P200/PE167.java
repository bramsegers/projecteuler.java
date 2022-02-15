package P151_P200;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class PE167 {

    /* 
     https://oeis.org/A100729
     https://oeis.org/A007300
     http://mathworld.wolfram.com/UlamSequence.html
     http://dreslan.blogspot.nl/2014/05/project-euler-problem-167.html
     */
    public static void main(String[] args) {
        new PE167().solve(2, 10, 100000000000L);
    }

    ArrayList<Long> ulam = new ArrayList<>();
    ArrayList<Long> even = new ArrayList<>();
    TreeSet<Long> sum = new TreeSet<>();
    TreeSet<Long> dupsum = new TreeSet<>();
    int[] period = {0, 0, 32, 26, 444, 1628, 5906, 80, 126960, 380882, 2097152};
    boolean found;
    int offset;

    void solve(int min, int max, long limit) {
        long tsum = 0;
        for (int n = min; n <= max; n++) {
            long t = solve(n, limit);
            tsum += t;
            System.out.format("U(2,%d) = %d%n", 2 * n + 1, t);
        }
        System.out.format("%nΣ U = %d%n%n", tsum);
    }

    long solve(int n, long limit) {
        ulam.clear();
        even.clear();
        sum.clear();
        dupsum.clear();
        ulam.add(2L);
        ulam.add(2L * n + 1);
        sum.add(2L * n + 3);
        found = false;
        offset = 0;
        long u = 0;
        for (long i = ulam.size() + 1; i <= limit; i++) {
            u = next();
            if ((limit - i) % period[n] == 0 && hasPeriod(n)) {
                long before = ulam.get(ulam.size() - 1 - period[n]);
                long diff = u - before;
                u += diff * ((limit - i) / period[n]);
                return u;
            }
        }
        return u;
    }

    boolean hasPeriod(int n) {
        if (!found || ulam.size() < 2 * period[n] + offset) {
            return false;
        }
        int s = ulam.size() - period[n];
        for (int i = s; i < ulam.size(); i++) {
            long d1 = ulam.get(i) - ulam.get(i - 1);
            long d2 = ulam.get(i - period[n]) - ulam.get(i - period[n] - 1);
            if (d1 != d2) {
                return false;
            }
        }
        return true;
    }

    long next() {
        long S = sum.pollFirst();
        for (long n : found ? even : ulam) {
            long ns = n + S;
            if (sum.contains(ns)) {
                sum.remove(ns);
                dupsum.add(ns);
            } else if (!dupsum.contains(ns)) {
                sum.add(ns);
            }
            while (!dupsum.isEmpty() && dupsum.first() < S) {
                dupsum.pollFirst();
            }
        }
        ulam.add(S);
        if ((S & 1) == 0) {
            found = true;
            offset = ulam.size() + 1;
            even.clear();
            even.add(2L);
            even.add(S);
            clean(sum);
            clean(dupsum);
        }
        return S;
    }

    void clean(TreeSet<Long> set) {
        Iterator<Long> it = set.iterator();
        while (it.hasNext()) {
            if ((it.next() & 1) == 0) {
                it.remove();
            }
        }
    }

}
