package P251_P300;

import java.util.HashMap;
import java.util.TreeSet;

public class PE297 {

    public static void main(String[] args) {
        new PE297().solve(100000000000000000L);
    }

    TreeSet<Long> fib = new TreeSet<>();

    void solve(long max) {
        long f1 = 1;
        long f2 = 2;
        fib.add(f1);
        fib.add(f2);
        long f = 0;
        while ((f = f1 + f2) < max) {
            fib.add(f);
            f1 = f2;
            f2 = f;
        }

        System.out.println(fib);
        //System.out.println(sumIterative(max - 1));
        //System.out.println(sumRecursive(max - 1));
        System.out.println(sumRecursiveMemoized(max - 1));

    }

    long sumIterative(long max) {
        long total = 0;
        for (long n = 1; n <= max; n++) {
            long terms = 0;
            long i = n;
            while (i > 0) {
                i -= fib.floor(i);
                terms++;
            }
            total += terms;
        }
        return total;
    }

    long sumRecursive(long n) {
        if (n == 0) {
            return 0;
        }
        long floor = fib.floor(n);
        return sumRecursive(floor - 1) + sumRecursive(n - floor) + n - floor + 1;
    }

    HashMap<Long, Long> memo = new HashMap<>();

    long sumRecursiveMemoized(long n) {
        if (n == 0) {
            return 0;
        }
        long floor = fib.floor(n);
        if (memo.get(floor - 1) == null) {
            memo.put(floor - 1, sumRecursiveMemoized(floor - 1));
        }
        return memo.get(floor - 1) + sumRecursiveMemoized(n - floor) + n - floor + 1;
    }
}
