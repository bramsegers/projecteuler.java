package P301_P350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import util.Util;

public class PE309 {

    /*
     * 1. Generate all pythagorean triplets (a,b,c) with c < limit
     * 2. Include all multiples of (a,b,c) with c < limit
     * 3. Check which can share the same base w (=a or b)
     * 4. Check which combinations result in integer heights h for intersection
     */
    public static void main(String[] args) {
        new PE309().solve(1000000);
    }

    void solve(int limit) {
        long count = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<int[]> pyt = Util.pythTriplets(limit - 1);
        for (int[] p : pyt) {
            for (int i = 0; i < 2; i++) {
                int m = 0, a = p[i], b = p[1 - i], c = p[2];
                while (++m * c < limit) {
                    List<Integer> list = map.get(m * a);
                    if (list == null) {
                        list = new ArrayList<>();
                        map.put(m * a, list);
                    }
                    list.add(m * b);
                }
            }
        }
        for (int w : map.keySet()) {
            List<Integer> list = map.get(w);
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    long q1 = 1L * list.get(i) * list.get(j);
                    long q2 = list.get(i) + list.get(j);
                    count += (q1 % q2 == 0) ? 1 : 0;
                }
            }
        }
        System.out.format("P(%d)=%d%n", limit, count);
    }

}
