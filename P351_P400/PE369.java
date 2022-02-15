package P351_P400;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

// https://enigmaticcode.wordpress.com/2012/02/06/project-euler-problem-369/
public class PE369 {

    public static void main(String[] args) {
        new PE369().solve(4, 13);
    }

    void solve(int min, int max) {
        long sum = count(0, 0, min, max, new ArrayList<>());
        System.out.format("P(%d,%d)=%d%n", min, max, sum);
    }

    long count(int cards, int p, int min, int max, List<Integer> list) {
        if (cards > max) {
            return 0;
        }
        long rv = 0;
        if (cards >= min && isBadugi(list)) {
            rv = 1L;
            int n = 13;
            Set<Integer> set = new HashSet<>(list);
            for (int i : set) {
                int k = 0;
                for (int j : list) {
                    k += (i == j) ? 1 : 0;
                }
                rv *= Util.choose(n, k);
                n -= k;
            }
        }
        for (int i = p; i < 15; i++) {
            List<Integer> list2 = new ArrayList<>(list);
            list2.add(i + 1);
            rv += count(cards + Integer.bitCount(i + 1), i, min, max, list2);
        }
        return rv;
    }

    boolean isBadugi(List<Integer> list) {
        int len = list.size();
        int suits = 0;
        for (int i : list) {
            suits |= i;
        }
        boolean c1 = suits < 15 || len < 4;
        for (int i = 0; i < len && !c1; i++) {
            boolean c2 = (list.get(i) & 1) == 0;
            for (int j = 0; j < len && !c2; j++) {
                boolean c3 = (j == i);
                c3 |= (list.get(j) & 0b0010) == 0;
                for (int k = 0; k < len && !c3; k++) {
                    boolean c4 = (k == i) || (k == j);
                    c4 |= (list.get(k) & 0b0100) == 0;
                    for (int m = 0; m < len && !c4; m++) {
                        boolean c5 = (m == i) || (m == j) || (m == k);
                        c5 |= (list.get(m) & 0b1000) == 0;
                        if (!c5) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
