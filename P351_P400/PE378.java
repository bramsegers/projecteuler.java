package P351_P400;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PE378 {

    /*
     * OEIS: A063440
     * a(2k) = A000005(k)*A000005(2k+1)
     * a(2k+1) = A000005(2k+1)*A000005(k+1)
     *
     * P(60000000)=147534623725724718
     * BUILD SUCCESSFUL (total time: 1 minute 18 seconds)
     */
    public static void main(String[] args) {
        new PE378().solve(60000000);
    }

    void solve(int n) {
        int[] dn = new int[n + 3];
        int[] dt = new int[n + 1];
        for (int i = 1; i <= n + 2; i++) {
            for (int j = i; j <= n + 2; j += i) {
                dn[j]++;
            }
        }
        for (int k = 0; k <= n / 2; k++) {
            dt[2 * k] = dn[k] * dn[2 * k + 1];
            if (k < n / 2) {
                dt[2 * k + 1] = dn[2 * k + 1] * dn[k + 1];
            }
        }
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> list = map.get(dt[i]);
            if (list == null) {
                list = new ArrayList<>();
                map.put(dt[i], list);
            }
            list.add(i);
        }
        int tright = 0;
        long sum = 0;
        long mod = (long) (1E18);
        List<Integer> list = new ArrayList<>(map.keySet());
        for (int k = 1; k < list.size() - 1; k++) {
            tright += map.get(list.get(k - 1)).size();
            long right = tright;
            long left = 0;
            int lasti = 0;
            int DT = list.get(k);
            for (int i : map.get(DT)) {
                for (int j = lasti + 1; j < i; j++) {
                    if (dt[j] > DT) {
                        left++;
                    } else {
                        right--;
                    }
                }
                sum += right * left;
                sum %= mod;
                lasti = i;
            }
        }
        System.out.format("P(%d)=%d%n", n, sum);
    }

}
