package P551_P600;

import java.util.Arrays;
import java.util.TreeMap;
import util.Primes;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;
import static util.Util.modPow;

public class PE593 {

    public static void main(String[] args) {
        new PE593().solve(100, 10);
        new PE593().solve(100000, 10000);
        new PE593().solve(10000000, 100000);
    }

    TreeMap<Long, Long>//
            HS,
            LHS = new TreeMap<>(),
            RHS = new TreeMap<>();

    void solve(int n, int k) {

        // 1. build S1, S2
        long[] S1 = new long[n + 1];
        long[] S2 = new long[n + 1];
        Primes pr = new Primes(20L * n);
        for (int i = 1; i <= n; i++) {
            S1[i] = modPow(pr.next(), i, 10007);
            S2[i] = S1[i] + S1[i / 10000 + 1];
        }

        // 2. build LHS, RHS
        long[] t = Arrays.copyOf(S2, k);
        Arrays.sort(t);
        for (int i = 0; i < k; i++) {
            HS = i < k / 2 ? LHS : RHS;
            Long v = HS.get(t[i]);
            HS.put(t[i], v == null ? 1 : v + 1);
        }

        // 3. roll to n
        long sum = 0;
        for (int i = 0; i <= n - k; i++) {

            // 4. update LHS, RHS
            boolean remLHS = LHS.containsKey(S2[i]);
            boolean addLHS = LHS.lastKey() > S2[i + k];
            if (remLHS && addLHS) {
                rem(LHS, S2[i]);
                add(LHS, S2[i + k]);
            } else if (!remLHS && !addLHS) {
                rem(RHS, S2[i]);
                add(RHS, S2[i + k]);
            } else if (remLHS && !addLHS) {
                rem(LHS, S2[i]);
                add(RHS, S2[i + k]);
                long move = RHS.firstKey();
                rem(RHS, move);
                add(LHS, move);
            } else if (!remLHS && addLHS) {
                rem(RHS, S2[i]);
                add(LHS, S2[i + k]);
                long move = LHS.lastKey();
                rem(LHS, move);
                add(RHS, move);
            }

            // 5. update sum
            sum += LHS.lastKey() + RHS.firstKey();
        }

        // 6. show result
        System.out.format("F(%d,%d)=%.1f%n", n, k, sum / 2.0);

    }

    void rem(TreeMap<Long, Long> HS, long k) {
        long i = HS.get(k);
        if (i == 1) {
            HS.remove(k);
        } else {
            HS.put(k, i - 1);
        }
    }

    void add(TreeMap<Long, Long> HS, long k) {
        Long v = HS.get(k);
        HS.put(k, v == null ? 1 : v + 1);
    }
}
