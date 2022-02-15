package P301_P350;

import java.util.HashSet;
import java.util.Set;

public class PE346 {

    public static void main(String[] args) {
        new PE346().solve(1000000000000L);
    }

    void solve(long nMax) {
        long n;
        Set<Long> set = new HashSet<>();
        for (long b = 2; (n = b * b + b + 1) <= nMax; b++) {
            long m = b * b;
            while (n < nMax) {
                set.add(n);
                m *= b;
                n += m;
            }
        }
        long sum = 1;
        for (long i : set) {
            sum += i;
        }
        System.out.println(sum);
    }

}
