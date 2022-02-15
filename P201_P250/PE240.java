package P201_P250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PE240 {

    public static void main(String[] args) {
        new PE240().solve(6, 5, 3, 15);
        new PE240().solve(12, 20, 10, 70);
    }

    void solve(int maxD, int total, int top, int targetSum) {
        long sum = 0;

        List<List<Integer>> topList = getTop(maxD, top, targetSum);
        Map<Integer, List<List<Integer>>> bottomMap = new HashMap<>();

        for (List<Integer> listT : topList) {
            int max = listT.get(listT.size() - 1);
            List<List<Integer>> bottomList = bottomMap.get(max);
            if (bottomList == null) {
                bottomList = getBottom(max, total - top);
                bottomMap.put(max, bottomList);
            }
            for (List<Integer> listB : bottomList) {
                sum += countPerm(listT, listB, maxD);
            }
        }
        System.out.format("P(%d,%d,%d,%d)=%d%n", maxD, total, top, targetSum, sum);
    }

    List<List<Integer>> getTop(int maxD, int length, int targetSum) {
        List<List<Integer>> topList = new ArrayList<>();
        for (int i = 1; i <= maxD; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            countTop(i, targetSum - i, length - 1, list, topList);
        }
        return topList;
    }

    void countTop(int d, int todo, int stepsTodo, List<Integer> list, List<List<Integer>> topList) {
        if (stepsTodo < 0 || todo < 0 || todo > stepsTodo * d) {
            return;
        }
        if (stepsTodo == 0 && todo == 0) {
            topList.add(list);
        } else {
            for (int i = 1; i <= d; i++) {
                List<Integer> list2 = new ArrayList<>(list);
                list2.add(i);
                countTop(i, todo - i, stepsTodo - 1, list2, topList);
            }
        }
    }

    List<List<Integer>> getBottom(int maxD, int length) {
        List<List<Integer>> bottomList = new ArrayList<>();
        for (int i = 1; i <= maxD; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            countBottom(i, length - 1, list, bottomList);
        }
        return bottomList;
    }

    void countBottom(int d, int stepsTodo, List<Integer> list, List<List<Integer>> bottomList) {
        if (stepsTodo == 0) {
            bottomList.add(list);
        } else {
            for (int i = 1; i <= d; i++) {
                List<Integer> list2 = new ArrayList<>(list);
                list2.add(i);
                countBottom(i, stepsTodo - 1, list2, bottomList);
            }
        }
    }

    long countPerm(List<Integer> top, List<Integer> bottom, int maxD) {
        int[] c = new int[maxD];
        int tot = 0;
        for (int t : top) {
            c[t - 1]++;
            tot++;
        }
        for (int b : bottom) {
            c[b - 1]++;
            tot++;
        }
        // assumes tot! < Long.MAX_VALUE
        long rv = 1;
        for (int n = 2; n <= tot; n++) {
            rv *= n;
        }
        for (int d : c) {
            for (int n = 2; n <= d; n++) {
                rv /= n;
            }
        }
        return rv;
    }

}
