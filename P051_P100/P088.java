package P051_P100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * A natural number, N, that can be written as the sum and product of a given set 
 * of at least two natural numbers {a1,a2, ... ,ak} is called a product-sum number: 
 * N = a1 + a2 + ... + ak = a1 * a2 * ... * ak.
 * For example, 6 = 1 + 2 + 3 = 1 * 2 * 3.
 * 
 * For a given set of size, k, we shall call the smallest N with this property a 
 * minimal product-sum number. The minimal product-sum numbers for sets of size
 * k = 2, 3, 4, 5, and 6 are as follows.
 * k=2:     4 = 2 * 2 = 2 + 2
 * k=3:     6 = 1 * 2 * 3 = 1 + 2 + 3
 * k=4:     8 = 1 * 1 * 2 * 4 = 1 + 1 + 2 + 4
 * k=5:     8 = 1 * 1 * 2 * 2 * 2 = 1 + 1 + 2 + 2 + 2
 * k=6:    12 = 1 * 1 * 1 * 1 * 2 * 6 = 1 + 1 + 1 + 1 + 2 + 6
 * 
 * Hence for 2 <= k <= 6, the sum of all the minimal product-sum numbers is 
 * 4 + 6 + 8 + 12 = 30. Note that 8 is only counted once in the sum.
 * In fact, as the complete set of minimal product-sum numbers for 2 <= k <= 12 
 * is {4, 6, 8, 12, 15, 16}, the sum is 61.
 * 
 * What is the sum of all the minimal product-sum numbers for 2 <= k <= 12000 ?
 */
public class P088 {

    private static Set<Integer> permSet;
    private static List<Integer> primes = new ArrayList<>();
    private static Set<Integer> primesSet = new HashSet<>();
    private static Set<Integer> resultSet = new HashSet<>();
    private static Map<Integer, Integer> resultMap = new TreeMap<>();

    public static int solve(int maxNum, int numRange) {
        primes.add(2);
        primesSet.add(2);
        int p = 1;
        int q;
        while ((p = p + 2) <= numRange) {
            int k = 0;
            while ((q = primes.get(k++)) <= Math.sqrt(p) && p % q != 0) {
            }
            if (p % q != 0) {
                primes.add(p);
                primesSet.add(p);
            }
        }

        for (int n = 2; n <= numRange; n++) {
            Set<Integer> set = getDivisors(n);
            if (!primesSet.contains(n)) {
                List<Integer> list = new ArrayList<>(set);
                Collections.reverse(list);
                permSet = new HashSet<>();
                for (int i = 0; i < list.size(); i++) {
                    getPermutation(n, 0, 0, 1, i, list);
                }
                for (int i : permSet) {
                    if (i <= maxNum && resultMap.get(i) == null) {
                        resultMap.put(i, n);
                        resultSet.add(n);
                    }
                }
                //System.out.println(n + ": " + permSet);
            }
        }
        
        int sum = 0;
        if (resultMap.size() + 1 == maxNum) {
            System.out.println(resultMap);
            for (int i : resultSet) {
                sum += i;
            }
        } else {
            System.out.println("Range too small");
        }
        return sum;
    }

    private static void getPermutation(int num, int sum, int fac, int prod, int i, List<Integer> list) {
        sum += list.get(i);
        prod *= list.get(i);
        fac++;
        if (prod == num) {
            permSet.add(fac + prod - sum);
            return;
        }
        if (prod < num) {
            for (int j = i; j < list.size(); j++) {
                getPermutation(num, sum, fac, prod, j, list);
            }
        }
    }

    private static Set<Integer> getDivisors(int num) {
        Set<Integer> divisors = new TreeSet<>();
        Map<Integer, Integer> map = decompile(num);
        int[] from = new int[map.size()];
        int[] to = new int[map.size()];
        int i = 0;
        for (Integer n : map.values()) {
            to[i++] = n;
        }
        while (!Arrays.equals(to, from)) {
            from[0]++;
            int j = 0;
            while (from[j] > to[j]) {
                from[j] = 0;
                from[++j]++;
            }
            int div = 1;
            int k = 0;
            for (int p : map.keySet()) {
                int n = from[k++];
                div *= Math.pow(p, n);
            }
            if (div != num) {
                divisors.add(div);
            }
        }
        return divisors;
    }

    private static Map<Integer, Integer> decompile(int num) {
        Map<Integer, Integer> map = new TreeMap<>();
        int index = 0;
        while (num > 1) {
            int div = primes.get(index++);
            while (num % div == 0) {
                num /= div;
                Integer i = map.get(div);
                map.put(div, i == null ? 1 : i + 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(P088.solve(12000, 12500));
    }
}
