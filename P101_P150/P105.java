package P101_P150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.Util;

/*
 * Let S(A) represent the sum of elements in set A of size n. We shall call it a special sum set  
 * if for any two non-empty disjoint subsets B and C, the following properties are true:
 * I) S(B) != S(C); that is, sums of subsets cannot be equal.
 * II) If B contains more elements than C then S(B) > S(C).
 * For example, {81, 88, 75, 42, 87, 84, 86, 65} is not a special sum set because 
 * 65 + 87 + 88 = 75 + 81 + 84, whereas {157, 150, 164, 119, 79, 159, 161, 139, 158} 
 * satisfies both rules for all possible subset pair combinations and S(A) = 1286.
 * 
 * Using P105.txt, identify all the special sum sets, A1, A2, ..., Ak, 
 * and find the value of S(A1) + S(A2) + ... + S(Ak).
 */
public class P105 {

    public static int solve(List<String> list) {
        int sum = 0;
        for (String s : list) {
            Set<Integer> set = new HashSet<>();
            for (String n : s.split(",")) {
                set.add(Integer.parseInt(n));
            }
            if (isSpecialSumSet(set)) {
                System.out.println(set);
                for (int i : set) {
                    sum += i;
                }
            }
        }
        return sum;
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

    public static void main(String[] args)  {
        List<String> list = Util.readText("files/P105.txt");
        System.out.println(P105.solve(list));
    }
}
