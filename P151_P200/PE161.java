package P151_P200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PE161 {

    public static void main(String[] args) {
        new PE161().test(9, 2);
        new PE161().solve(12, 9);
    }

    int ROW, COL;
    int firstCol = 619;
    int lastCol = 663;
    int firstRow = 461;
    int lastRow = 318;
    int[] low = {560, 461, 461, 461, 461, 461, 2, 2, 461, 2};
    int[] left = {619, 619, 619, 400, 619, 4, 4, 619, 4, 619};

    long[][] count;
    Map<List<Integer>, Integer> index = new HashMap<>();

    void solve(int r, int c) {
        ROW = r;
        COL = c;
        Set<List<Integer>> rows = new HashSet<>();
        generateRows(0, 0, new ArrayList<>(), null, rows);
        for (List<Integer> list : rows) {
            index.put(list, index.size());
        }
        count = new long[ROW][index.size()];
        rows.clear();
        generateRows(0, 0, new ArrayList<>(), new ArrayList<>(), rows);
        long sum = 0;
        for (List<Integer> row : rows) {
            sum += count(0, row);
        }
        System.out.format("P(%d,%d)=%d%n", r, c, sum);
    }

    long count(int r, List<Integer> lower) {
        if (++r == ROW) {
            return 1;
        }
        int i = index.get(lower);
        if (count[r][i] == 0) {
            Set<List<Integer>> rows = new HashSet<>();
            generateRows(0, r, new ArrayList<>(), lower, rows);
            for (List<Integer> row : rows) {
                count[r][i] += count(r, row);
            }
        }
        return count[r][i];
    }

    void generateRows(int c, int r, List<Integer> row, List<Integer> lower, Set<List<Integer>> rows) {
        if (c == COL) {
            rows.add(row);
            return;
        }
        int poss = (1 << 10) - 1;
        poss &= (c == 0) ? firstCol : ((c == COL - 1) ? lastCol : poss);
        poss &= (c > 0) ? left[row.get(row.size() - 1)] : poss;
        if (lower != null) {
            poss &= (r == 0) ? firstRow : ((r == ROW - 1) ? lastRow : poss);
            poss &= (r > 0) ? low[lower.get(c)] : poss;
        }
        for (int i = 0; i < 10; i++) {
            if ((poss & 1) == 1) {
                List<Integer> list2 = new ArrayList<>(row);
                list2.add(i);
                generateRows(c + 1, r, list2, lower, rows);
            }
            poss >>= 1;
        }
    }

    void test(int r, int c) {
        ROW = r;
        COL = c;
        int sum = test(0, 0, new ArrayList<>(), new ArrayList<>());
        System.out.format("P(%d,%d)=%d%n", r, c, sum);
    }

    int test(int c, int r, List<Integer> row, List<List<Integer>> sol) {
        if (c == COL) {
            sol = new ArrayList<>(sol);
            sol.add(row);
            row = new ArrayList<>();
            c = 0;
            if (++r == ROW) {
                print(sol);
                return 1;
            }
        }
        int rv = 0;
        int poss = (1 << 10) - 1;
        poss &= (c == 0) ? firstCol : ((c == COL - 1) ? lastCol : poss);
        poss &= (r == 0) ? firstRow : ((r == ROW - 1) ? lastRow : poss);
        poss &= (c > 0) ? left[row.get(row.size() - 1)] : poss;
        poss &= (r > 0) ? low[sol.get(sol.size() - 1).get(c)] : poss;
        for (int i = 0; i < 10; i++) {
            if ((poss & 1) == 1) {
                List<Integer> list2 = new ArrayList<>(row);
                list2.add(i);
                rv += test(c + 1, r, list2, sol);
            }
            poss >>= 1;
        }
        return rv;
    }

    void print(List<List<Integer>> sol) {
        String[][] arr = {
            {"║ ║", "╔═╗", "══╗", "╔══", "══╗", "╔══", "║ ╚", "╝ ║", "═══", "║ ║"},
            {"╚═╝", "║ ║", "══╝", "╚══", "╗ ║", "║ ╔", "╚══", "══╝", "═══", "║ ║"}
        };
        StringBuilder s = new StringBuilder();
        for (int k = sol.size() - 1; k >= 0; k--) {
            List<Integer> row = sol.get(k);
            for (int j = 0; j < 2; j++) {
                for (int i : row) {
                    s.append(arr[j][i]);
                }
                s.append("\n");
            }
        }
        System.out.println(s.append("\n"));
    }

}
