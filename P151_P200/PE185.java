package P151_P200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PE185 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("5616185650518293,2");
        list.add("3847439647293047,1");
        list.add("5855462940810587,3");
        list.add("9742855507068353,3");
        list.add("4296849643607543,3");
        list.add("3174248439465858,1");
        list.add("4513559094146117,2");
        list.add("7890971548908067,3");
        list.add("8157356344118483,1");
        list.add("2615250744386899,2");
        list.add("8690095851526254,3");
        list.add("6375711915077050,1");
        list.add("6913859173121360,1");
        list.add("6442889055042768,2");
        list.add("2321386104303845,0");
        list.add("2326509471271448,2");
        list.add("5251583379644322,2");
        list.add("1748270476758276,3");
        list.add("4895722652190306,1");
        list.add("3041631117224635,3");
        list.add("1841236454324589,3");
        list.add("2659862637316867,2");
//        list.add("90342,2");
//        list.add("70794,0");
//        list.add("39458,2");
//        list.add("34109,1");
//        list.add("51545,2");
//        list.add("12531,1");
        new PE185().solve(list);
    }

    int size = 16;
    boolean found = false;
    List<String> ignore = new ArrayList<>();
    List<String> hints = new ArrayList<>();
    List<Integer> corr = new ArrayList<>();
    Map<Integer, List<Set<Integer>>> perms = new HashMap<>();

    void solve(List<String> list) {
        long start = System.currentTimeMillis();
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String t, String o) {
                return t.split(",")[1].compareTo(o.split(",")[1]);
            }
        });
        for (String s : list) {
            String[] t = s.split(",");
            int v = Integer.parseInt(t[1]);
            if (v == 0) {
                ignore.add(t[0]);
            } else {
                hints.add(t[0]);
                corr.add(v);
            }
        }
        int todo = 9 * size;
        boolean[][] code = new boolean[size][10];
        for (String s : ignore) {
            for (int i = 0; i < size; i++) {
                int v = s.charAt(i) - '0';
                if (!code[i][v]) {
                    code[i][v] = true;
                    todo--;
                }
            }
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            nums.add(i);
        }
        Set<Integer> set = new HashSet<>(corr);
        for (Integer s : set) {
            perms.put(s, getSubsets(nums, s));
        }
        for (Set<Integer> perm : perms.get(corr.get(0))) {
            solve(code, 0, perm, todo);
        }
        long end = System.currentTimeMillis();
        System.out.format("Elapsed:%dms%n%n", end - start);
    }

    void solve(boolean[][] lastCode, int hint, Set<Integer> perm, int todo) {
        if (found) {
            // PE says solution is unique
            return;
        }
        if (todo == 0) {
            found = true;
            print(lastCode);
            return;
        }
        String h = hints.get(hint);
        for (int i : perm) {
            if (lastCode[i][h.charAt(i) - '0']) {
                return;
            }
        }
        boolean[][] code = copyOf(lastCode);
        for (int i = 0; i < size; i++) {
            int v = h.charAt(i) - '0';
            if (perm.contains(i)) {
                for (int j = 0; j < 10; j++) {
                    if (j == v) {
                        code[i][j] = false;
                    } else if (!code[i][j]) {
                        code[i][j] = true;
                        todo--;
                    }
                }
            } else if (!code[i][v]) {
                code[i][v] = true;
                todo--;
                boolean open = false;
                for (int j = 0; j < 10 && !open; j++) {
                    open = open || !code[i][j];
                }
                if (!open) {
                    return;
                }
            }
        }
        for (int i = hint + 1; i < hints.size(); i++) {
            String comp = hints.get(i);
            int ok = 0;
            int ok_open = 0;
            int count = corr.get(i);
            for (int j = 0; j < size; j++) {
                int val = comp.charAt(j) - '0';
                if (!code[j][val]) {
                    int open = 0;
                    int num = 0;
                    for (int k = 0; k < 10 && open < 2; k++) {
                        if (!code[j][k]) {
                            open++;
                            num = k;
                        }
                    }
                    if (open == 1 && num == val) {
                        ok++;
                    } else {
                        ok_open++;
                    }
                }
            }
            if ((ok > count) || (ok + ok_open < count)) {
                return;
            } else if (ok_open == 1 && ok + 1 == count) {
                //fill in open
            }
        }
        for (Set<Integer> perm2 : perms.get(corr.get(hint + 1))) {
            solve(code, hint + 1, perm2, todo);
        }
    }

    List<Set<Integer>> getSubsets(List<Integer> list, int k) {
        List<Set<Integer>> res = new ArrayList<>();
        getSubsets(list, k, 0, new HashSet<Integer>(), res);
        return res;
    }

    void getSubsets(List<Integer> superSet, int k, int i, Set<Integer> curr, List<Set<Integer>> subsets) {
        if (curr.size() == k) {
            subsets.add(new HashSet<>(curr));
            return;
        }
        if (i == superSet.size()) {
            return;
        }
        Integer x = superSet.get(i);
        curr.add(x);
        getSubsets(superSet, k, i + 1, curr, subsets);
        curr.remove(x);
        getSubsets(superSet, k, i + 1, curr, subsets);
    }

    void print(boolean[][] m) {
        int x = m.length;
        int y = m[0].length;
        int[] sol = new int[size];
        String s = "";
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                s += String.format("%d ", m[i][j] ? 1 : 0);
                if (!m[i][j]) {
                    sol[i] = j;
                }
            }
            s += "\n";
        }
        System.out.print(s);
        System.out.println(Arrays.toString(sol));
    }

    boolean[][] copyOf(boolean[][] m) {
        int x = m.length;
        int y = m[0].length;
        boolean[][] rv = new boolean[x][y];
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                rv[i][j] = m[i][j];
            }
        }
        return rv;
    }

}
