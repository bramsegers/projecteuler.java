package P101_P150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Let S(A) represent the sum of elements in set A of size n. We shall call it a special sum set 
 * if for any two non-empty disjoint subsets B and C, the following properties are true:
 * I) S(B) != S(C); that is, sums of subsets cannot be equal.
 * II) If B contains more elements than C then S(B) > S(C).
 * 
 * If S(A) is minimised for a given n, we shall call it an optimum special sum set. 
 * The first six optimum special sum sets are given below.
 * n = 1: {1}
 * n = 2: {1, 2}
 * n = 3: {2, 3, 4}
 * n = 4: {3, 5, 6, 7}
 * n = 5: {6, 9, 11, 12, 13}
 * n = 6: {11, 18, 19, 20, 22, 25}
 * 
 * Given that A is an optimum special sum set for n = 7, find its set string.
 * Info: http://stackoverflow.com/questions/4640034/calculating-all-of-the-subsets-of-a-set-of-numbers
 */
public class P103 {

    public static String solve(int n1, int n2, int nMax) {
        for (int a = n1; a <= nMax; a++) {
            for (int b = n2 - n1 + a + 1; b <= nMax; b++) {
                for (int c = b + 1; c <= nMax; c++) {
                    for (int d = c + 1; d <= nMax; d++) {
                        for (int e = d + 1; e <= nMax; e++) {
                            for (int f = e + 1; f <= nMax; f++) {
                                for (int g = f + 1; g <= nMax; g++) {
                                    System.out.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g);
                                    Set<Integer> set = new HashSet<>();
                                    set.add(a);
                                    set.add(b);
                                    set.add(c);
                                    set.add(d);
                                    set.add(e);
                                    set.add(f);
                                    set.add(g);
                                    if (isSpecialSumSet(set)) {
                                        return "" + a + b + c + d + e + f + g;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "Not found in range";
    }

    private static boolean isSpecialSumSet(Set<Integer> set) {
        List<Set<Integer>> setList = powerSet(set);
        for (int i = 0; i < setList.size() - 1; i++) {
            for (int j = i + 1; j < setList.size() - 1; j++) {
                Set<Integer> set1 = setList.get(i);
                Set<Integer> set2 = setList.get(j);
                boolean isDisjoint = true;
                for (int e : set1) {
                    isDisjoint = isDisjoint && !set2.contains(e);
                }
                if (isDisjoint) {
                    int sum1 = 0;
                    int sum2 = 0;
                    for (int s1 : set1) {
                        sum1 += s1;
                    }
                    for (int s2 : set2) {
                        sum2 += s2;
                    }
                    if (sum1 == sum2) {
                        return false;
                    }
                    if (set1.size() > set2.size() && sum1 <= sum2) {
                        return false;
                    }
                    if (set2.size() > set1.size() && sum2 <= sum1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<Set<Integer>> powerSet(Set<Integer> originalSet) {
        List<Set<Integer>> setList = new ArrayList<>();
        if (originalSet.isEmpty()) {
            setList.add(new HashSet<Integer>());
            return setList;
        }
        List<Integer> list = new ArrayList<>(originalSet);
        Integer head = list.get(0);
        Set<Integer> rest = new HashSet<>(list.subList(1, list.size()));
        for (Set<Integer> set : powerSet(rest)) {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(head);
            newSet.addAll(set);
            setList.add(newSet);
            setList.add(set);
        }
        return setList;
    }

    public static void main(String[] args) {
        System.out.println(P103.solve(20, 30, 46));
    }
}
