package P401_P450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.Util;

public class PE424 {

    public static void main(String[] args) {
        new PE424().solve();
    }

    void solve() {
        long sum, tsum = 0, count = 0;
        List<String> list = Util.readText("files/P424.txt");
        for (String s : list) {
            s = s.replaceAll(",v", "_v");
            s = s.replaceAll("\\)", "").replaceAll("\\(", "");
            sum = solve(s.charAt(0) - '0', s.substring(2).split(","));
            System.out.println(++count + " " + sum);
            tsum += sum;
        }
        System.out.println("sum=" + tsum);
    }

    int size;
    Cell[][] kakuro;
    Map<Character, List<Integer>> options;

    long solve(int s, String[] k) {
        size = s;
        options = new HashMap<>();
        for (char ch = 'A'; ch <= 'J'; ch++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            options.put(ch, list);
        }
        kakuro = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                kakuro[i][j] = new Cell(i, j, k[i * size + j]);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = kakuro[i][j];
                if (cell.hor != null) {
                    int len = 0, m = j;
                    while (++m < size && !kakuro[i][m].grey) {
                        len++;
                    }
                    setPoss(cell.hor.c, len);
                }
                if (cell.ver != null) {
                    int len = 0, m = i;
                    while (++m < size && !kakuro[m][j].grey) {
                        len++;
                    }
                    setPoss(cell.ver.c, len);
                }
            }
        }

        List<List<Integer>> perm = new ArrayList<>();
        permutations(0, new ArrayList<>(), perm);
        for (List<Integer> p : perm) {
            if (valid(p) && solve(p)) {
                long sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum *= 10;
                    sum += p.get(i);
                }
                return sum;
            }
        }
        return 0;
    }

    boolean solve(List<Integer> p) {
        int[][] k = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = kakuro[i][j];
                k[i][j] = cell.getNum(p);
                if (cell.hor != null) {
                    cell.hor.num = cell.hor.getNum(p);
                }
                if (cell.ver != null) {
                    cell.ver.num = cell.ver.getNum(p);
                }
            }
        }
        return solve(0, k);
    }

    boolean solve(int p, int[][] k) {
        if (p == size * size) {
            return true;
        }
        int i = p / size;
        int j = p % size;
        if (kakuro[i][j].grey || k[i][j] > 0) {
            return solve(p + 1, k);
        } else {
            boolean rv = false;
            for (int n = 1; n < 10; n++) {
                if (valid(n, i, j, k)) {
                    int[][] k2 = clone(k);
                    k2[i][j] = n;
                    rv |= solve(p + 1, k2);
                }
            }
            return rv;
        }
    }

    boolean valid(int n, int i, int j, int[][] k) {
        Cell hor = kakuro[i][j].getHor();
        if (hor != null && hor.hor != null) {
            int checksum = hor.hor.num;
            int m = hor.pj;
            List<Integer> nums = new ArrayList<>();
            while (++m < size && !kakuro[i][m].grey) {
                nums.add(m == j ? n : k[i][m]);
                if (m != j && k[i][m] == n) {
                    return false;
                }
            }
            if (!validSum(nums, checksum)) {
                return false;
            }
        }

        Cell ver = kakuro[i][j].getVer();
        if (ver != null && ver.ver != null) {
            int checksum = ver.ver.num;
            int m = ver.pi;
            List<Integer> nums = new ArrayList<>();
            while (++m < size && !kakuro[m][j].grey) {
                nums.add(m == i ? n : k[m][j]);
                if (m != i && k[m][j] == n) {
                    return false;
                }
            }
            if (!validSum(nums, checksum)) {
                return false;
            }
        }

        return true;
    }

    int[][] clone(int[][] k) {
        int[][] rv = new int[k.length][];
        for (int r = 0; r < k.length; r++) {
            rv[r] = k[r].clone();
        }
        return rv;
    }

    boolean valid(List<Integer> perm) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = kakuro[i][j];

                if (cell.hor != null) {
                    int m = j;
                    List<Integer> nums = new ArrayList<>();
                    int checksum = cell.hor.getNum(perm);
                    while (++m < size && !kakuro[i][m].grey) {
                        nums.add(kakuro[i][m].getNum(perm));
                    }
                    if (!validSum(nums, checksum)) {
                        return false;
                    }
                }

                if (cell.ver != null) {
                    int m = i;
                    List<Integer> nums = new ArrayList<>();
                    int checksum = cell.ver.getNum(perm);
                    while (++m < size && !kakuro[m][j].grey) {
                        nums.add(kakuro[m][j].getNum(perm));
                    }
                    if (!validSum(nums, checksum)) {
                        return false;
                    }
                }

            }
        }
        return true;
    }

    boolean validSum(List<Integer> nums, int checksum) {
        boolean closed = true;
        while (nums.contains(0)) {
            closed = false;
            nums.remove(Integer.valueOf(0));
            int k = 0;
            while (nums.contains(++k)) {
            }
            nums.add(k);
        }
        int totsum = 0;
        for (int n : nums) {
            totsum += n;
        }
        return closed ? totsum == checksum : totsum <= checksum;
    }

    void permutations(int ch, List<Integer> list, List<List<Integer>> perm) {
        if (ch == 10) {
            if (list.size() == 10) {
                perm.add(list);
            }
            return;
        }
        for (int i : options.get((char) (ch + 'A'))) {
            if (!list.contains(i)) {
                List<Integer> list2 = new ArrayList<>(list);
                list2.add(i);
                permutations(ch + 1, list2, perm);
            }
        }
    }

    void setPoss(String c, int len) {
        char ch = c.charAt(0);
        if (c.length() == 1) {
            for (int i = 0; i < len * (len + 1) / 2; i++) {
                options.get(ch).remove(Integer.valueOf(i));
            }
        } else {
            options.get(ch).remove(Integer.valueOf(0));
            for (int i = Math.min(4, len); i < 10; i++) {
                options.get(ch).remove(Integer.valueOf(i));
            }
            if (len == 2) {
                options.get(c.charAt(1)).remove(Integer.valueOf(8));
                options.get(c.charAt(1)).remove(Integer.valueOf(9));
            }
        }
    }

    class Cell {

        String c;
        int pi, pj;
        boolean grey;
        Sum ver, hor;

        private Cell(int i, int j, String k) {
            pi = i;
            pj = j;
            grey = k.contains("X") || k.contains("v") || k.contains("h");
            if (!k.contains("X") && !k.contains("O")) {
                if (!k.contains("v") && !k.contains("h")) {
                    c = k;
                    options.get(c.charAt(0)).remove(Integer.valueOf(0));
                } else {
                    for (String s : k.split("_")) {
                        if (s.contains("v")) {
                            ver = new Sum(s.substring(1));
                        } else {
                            hor = new Sum(s.substring(1));
                        }
                    }
                }
            }
        }

        int getNum(List<Integer> perm) {
            return c == null ? 0 : perm.get(c.charAt(0) - 'A');
        }

        Cell getHor() {
            return grey ? this : (pj == 0 ? null : kakuro[pi][pj - 1].getHor());
        }

        Cell getVer() {
            return grey ? this : (pi == 0 ? null : kakuro[pi - 1][pj].getVer());
        }
    }

    class Sum {

        String c;
        int num;

        public Sum(String s) {
            c = s;
        }

        int getNum(List<Integer> perm) {
            int rv = perm.get(c.charAt(0) - 'A');
            if (c.length() == 2) {
                rv = 10 * rv + perm.get(c.charAt(1) - 'A');
            }
            return rv;
        }

    }

}
