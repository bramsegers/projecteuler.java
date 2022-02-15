package P201_P250;

import java.util.ArrayList;
import java.util.List;

public class PE209 {

    /*
     * Info: http://garethrees.org/2013/05/15/euler/
     *       https://oeis.org/A000032
     */
    public static void main(String[] args) {
        new PE209().solve(6);
    }

    void solve(int n) {
        List<Integer> nums = new ArrayList<>();
        int[] input = new int[1 << n];
        for (int t = 0; t < (1 << n); t++) {
            int a = t & 1;
            int b = (t >> 1) & 1;
            int c = (t >> 2) & 1;
            int t2 = ((a ^ (b & c)) << (n - 1)) + (t >> 1);
            input[t] = t2;
            nums.add(t);
        }
        long total = 1;
        while (!nums.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int t = nums.get(0);
            while (!list.contains(t)) {
                list.add(t);
                nums.remove(Integer.valueOf(t));
                t = input[t];
            }
            long count = lucas(list.size());
            System.out.format("Cycle length:%d\tCount:%d%n", list.size(), count);
            total *= count;
        }
        System.out.format("P(%d)=%d%n", n, total);
    }

    long lucas(int n) {
        if (n == 1) {
            return 1;
        }
        long[] lucas = new long[n + 1];
        lucas[1] = 1;
        lucas[2] = 3;
        for (int i = 3; i <= n; i++) {
            lucas[i] = lucas[i - 2] + lucas[i - 1];
        }
        return lucas[n];
    }
}
