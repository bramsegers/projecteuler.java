package P251_P300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

public class PE259 {

    /*
     Numbers reached :72585
     Sum: 20101196798
     BUILD SUCCESSFUL (total time: 4 minutes 58 seconds)
     */
    public static void main(String[] args) {
        new PE259().solve();
    }

    void solve() {
        List<List<Integer>> parts = new ArrayList<>();
        List<List<Double>> partNums = new ArrayList<>();
        Util.partitions(9, parts);
        for (List<Integer> part : parts) {
            System.out.println("*" + part);
            List<List<Integer>> perms = new ArrayList<>();
            Util.permutations(part, perms);
            for (List<Integer> perm : perms) {
                List<Double> partNum = new ArrayList();
                int n = 987654321;
                for (int i : perm) {
                    int num = 0;
                    for (int r = 0; r < i; r++) {
                        num *= 10;
                        num += n % 10;
                        n /= 10;
                    }
                    partNum.add((double) num);
                }
                partNums.add(partNum);
            }
        }

        for (List<Double> partNum : partNums) {
            System.out.println(partNum);
            solve(partNum);
        }

        long sum = 0;
        for (int i : set) {
            sum += i;
        }
        System.out.println("Numbers reached :" + set.size());
        System.out.println("Sum: " + sum);
    }

    Set<Integer> set = new HashSet<>();

    void solve(List<Double> part) {
        if (part.size() == 1) {
            add(part.get(0));
            return;
        }
        for (int i = 0; i < part.size() - 1; i++) {
            double e1 = part.get(i);
            double e2 = part.get(i + 1);
            for (int op = 0; op < 4; op++) {
                List<Double> part2 = new ArrayList<>(part);
                if (op == 0) {
                    part2.set(i, e1 + e2);
                    part2.remove(i + 1);
                    solve(part2);
                } else if (op == 1) {
                    part2.set(i, e1 - e2);
                    part2.remove(i + 1);
                    solve(part2);
                } else if (op == 2) {
                    part2.set(i, e1 * e2);
                    part2.remove(i + 1);
                    solve(part2);
                } else if (e2 != 0) {
                    part2.set(i, e1 / e2);
                    part2.remove(i + 1);
                    solve(part2);
                }
            }
        }
    }

    void add(double E) {
        double e = Math.rint(E);
        if (e > 0 && e < 1000000000D && Math.abs(E - e) < 0.0000001D) {
            set.add((int) e);
        }
    }

}
