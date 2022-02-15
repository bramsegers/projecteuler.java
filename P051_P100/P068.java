package P051_P100;

import java.util.ArrayList;
import java.util.List;
import util.Util;

public class P068 {

    static long solve(int nMax) {
        long max = 0;
        List<List<Integer>> choose = new ArrayList<>();
        Util.choose(nMax << 1, nMax, choose);
        for (List<Integer> list1 : choose) {
            List<Integer> list2 = new ArrayList<>();
            for (int i = 1; i <= nMax << 1; i++) {
                if (!list1.contains(i)) {
                    list2.add(i);
                }
            }
            Integer i = list1.get(0);
            list1.remove(i);
            List<List<Integer>> perm1 = new ArrayList<>();
            List<List<Integer>> perm2 = new ArrayList<>();
            Util.permutations(list1, perm1);
            Util.permutations(list2, perm2);
            for (List<Integer> p1 : perm1) {
                for (List<Integer> p2 : perm2) {
                    long m = check(i, p1, p2);
                    max = m == 0 ? max : m;
                }
            }
        }
        return max;
    }

    static long check(int i, List<Integer> p, List<Integer> p2) {
        List<Integer> p1 = new ArrayList<>(p);
        p1.add(0, i);
        int last = 0;
        for (i = 0; i < p1.size(); i++) {
            int curr = p1.get(i) + p2.get(i) + p2.get((i + 1) % p2.size());
            if (last != 0 && last != curr) {
                return 0;
            }
            last = curr;
        }
        System.out.println(p1);
        System.out.println(p2);
        String s = "";
        for (i = 0; i < p1.size(); i++) {
            s += p1.get(i) + "" + p2.get(i) + "" + p2.get((i + 1) % p2.size());
        }
        System.out.println(s + "\n");
        return Long.parseLong(s);
    }

    public static void main(String[] args) {
        System.out.println(P068.solve(5));
    }

}
