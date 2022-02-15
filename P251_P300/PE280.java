package P251_P300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import util.Matrix;

/*
 https://luckytoilet.wordpress.com/2010/03/06/project-euler-280/
 */
public class PE280 {

    public static void main(String[] args) {
        //new PE280().printAllWalks();
        //new PE280().simulate(100000);
        new PE280().solve(); // 430.0882467166889  (total time: 7 minutes 38 seconds)
    }

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int stateSeedStart = (1 << 5) - 1;
    int stateSeedEnd = stateSeedStart << 20;
    int stateStart = (((stateSeedStart << 5) + (5 * 2 + 2)) << 1);

    Map<Integer, List<Integer>> map = new TreeMap<>();

    void simulate(long reps) {
        map.put(0, Arrays.asList(0));
        walk(stateStart);
        Random r = new Random();
        long steps = 0;
        for (long i = 1; i <= reps; i++) {
            int state = stateStart;
            while (state != 0) {
                List<Integer> list = map.get(state);
                state = list.get(r.nextInt(list.size()));
                steps++;
            }
        }
        System.out.println(1D * steps / reps);
    }

    void printAllWalks() {
        map.put(0, Arrays.asList(0));
        walk(stateStart);
        for (int i : map.keySet()) {
            print(i, true);
            for (int j : map.get(i)) {
                print(j, false);
            }
        }
    }

    void solve() {
        map.put(0, Arrays.asList(0));
        walk(stateStart);
        Set<Integer> keySet = map.keySet();
        Map<Integer, Integer> stateToPos = new HashMap<>();
        Iterator<Integer> iterator = keySet.iterator();
        int pos = 0;
        while (iterator.hasNext()) {
            stateToPos.put(iterator.next(), pos++);
        }
        double[][] matrix = new double[map.size()][map.size()];
        Iterator<List<Integer>> it = map.values().iterator();
        for (int r = 0; r < matrix.length; r++) {
            List<Integer> row = it.next();
            double fill = 1D / row.size();
            matrix[r][r] = 1;
            for (int st : row) {
                if (st != 0) {
                    int col = stateToPos.get(st);
                    matrix[r][col] = -fill;
                }
            }
        }
        Matrix bigMatrix = new Matrix(matrix);
        Matrix onesColumn = new Matrix(map.size(), 1, 1);
        Matrix sums = bigMatrix.solve(onesColumn);
        double d = sums.get(stateToPos.get(stateStart), 0);
        System.out.println(d);
    }

    void walk(int state) {
        int carrying = state & 1;
        int px = ((state >> 1) & 31) % 5;
        int py = ((state >> 1) & 31) / 5;
        int stateSeed = state >> 6;
        List<Integer> list = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int px2 = px + dx[d];
            int py2 = py + dy[d];
            boolean valid = px2 >= 0 && py2 >= 0 && px2 < 5 && py2 < 5;
            if (valid) {
                int stateSeed2 = stateSeed;
                int statep = getStateAt(px2, py2, stateSeed2);
                int carrying2 = carrying;
                if (carrying2 == 1) {
                    if (py2 == 0 && statep == 0) {//can drop
                        stateSeed2 = setStateAt(px2, py2, stateSeed2, 1);//add seed to p 
                        carrying2 = 0;
                    }
                } else if (py2 == 4 && statep == 1) {//can pickup
                    stateSeed2 = setStateAt(px2, py2, stateSeed2, 0);//remove seed from p
                    carrying2 = 1;
                }
                int state2 = (((stateSeed2 << 5) + (5 * py2 + px2)) << 1) + carrying2;
                list.add(stateSeed2 == stateSeedEnd ? 0 : state2);
            }
        }
        map.put(state, list);
        for (int i : list) {
            if (!map.containsKey(i)) {
                walk(i);
            }
        }
    }

    int getStateAt(int px, int py, int stateSeed) {
        int shift = 24 - (5 * py) - px;
        return (stateSeed >> shift) & 1;
    }

    int setStateAt(int px, int py, int stateSeed, int i) {
        int rv = 0;
        for (int j = 0; j < 25; j++) {
            rv = (rv << 1) + ((5 * py + px != j)
                    ? ((stateSeed >> (24 - j)) & 1)
                    : i);
        }
        return rv;
    }

    void print(int state, boolean from) {
        int carrying = state & 1;
        int px = ((state >> 1) & 31) % 5;
        int py = ((state >> 1) & 31) / 5;
        int stateSeed = state >> 6;
        int p = 24;
        String s = from ? "" : "    ";
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                int n = (stateSeed >> p--) & 1;
                n += (px == x && py == y) ? (4 + carrying) : 0;
                s += n;
            }
            s += from ? "\n" : "\n    ";
        }
        System.out.println(s + carrying + "\n");
    }
}
