package P151_P200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PE189 {

    public static void main(String[] args) {
        new PE189().solve(8);
    }

    long[][] count;
    Map<Long, Integer> index = new HashMap<>();

    void solve(int R) {
        int i = 0;
        index.put(0L, 0);
        for (int r = 0; r < R; r++) {
            Set<Long> rows = new HashSet<>();
            getRows(r, 0, -1, 3, rows);
            for (long row : rows) {
                index.put(row, ++i);
            }
        }
        count = new long[R][i];
        long sum = 3 * count(R, 0, 0);
        System.out.format("P(%d)=%d%n", R, sum);

    }

    long count(int R, int r, long lower) {
        if (++r == R) {
            return 1;
        }
        int i = index.get(lower);
        if (count[r][i] == 0) {
            Set<Long> rows = new HashSet<>();
            getRows(r, 0, lower, 3, rows);
            for (long row : rows) {
                count[r][i] += count(R, r, row);
            }
        }
        return count[r][i];
    }

    void getRows(int r, int p, long low, long row, Set<Long> rows) {
        if (p == 2 * r + 1) {
            rows.add(row);
        } else {
            long c1 = (low >> ((2 * r - p - 1) * 2)) & 3;
            long c2 = (p & 1) == 0 ? 3 : c1;
            for (int i = 0; i < 3; i++) {
                if (i != (row & 3) && i != c2) {
                    getRows(r, p + 1, low, (row << 2) + i, rows);
                }
            }
        }
    }

}
