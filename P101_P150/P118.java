package P101_P150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import util.Util;

/*
 * Using all of the digits 1 through 9 and concatenating them freely to form 
 * decimal integers, different sets can be formed. Interestingly with the set 
 * {2,5,47,89,631}, all of the elements belonging to it are prime.
 * 
 * How many distinct sets, containing each of the digits one through nine 
 * exactly once, contain only prime elements?
 */
public class P118 {

    public static int solve() {
        Set<Set<Integer>> primeSets = new HashSet<>();
        List<List<Integer>> part = new ArrayList<>();
        List<List<Integer>> perm = new ArrayList<>();
        Util.partitions(9, part);
        Util.permutations(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}), perm);
        for (List<Integer> pt : part) {
            Collections.sort(pt);
            for (List<Integer> pm : perm) {
                checkPrimeSet(pt, pm, primeSets);
            }
        }
        return primeSets.size();
    }

    private static void checkPrimeSet(List<Integer> pt, List<Integer> pm, Set<Set<Integer>> primeSets) {
        Set<Integer> set = new TreeSet<>();
        int p = 0, pi = 0, pc = 0;
        for (int i : pm) {
            p *= 10;
            p += i;
            pc++;
            if (pt.get(pi) == pc) {
                pc = 0;
                pi++;
                if (!Util.isPrime(p)) {
                    return;
                }
                set.add(p);
                p = 0;
            }
        }
        primeSets.add(set);
    }

    public static void main(String[] args) {
        System.out.println(P118.solve());
    }
}
