package P001_P050;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 If d(a) = b and d(b) = a, where a  b, then a and b are an amicable pair and each of a and b are called amicable numbers.

 For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; 
 therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

 Evaluate the sum of all the amicable numbers under 10000.
 */
public class P021 {

    public static Map<Integer, Integer> decompile(int num) {
        Map<Integer, Integer> map = new TreeMap<>();
        if (num == 1) {
            map.put(1, 1);
        }
        int div = 2;
        while (num > 1) {
            while (num % div == 0) {

                num /= div;
                Integer i = map.get(div);
                map.put(div, i == null ? 1 : i + 1);
            }
            div++;
        }
        return map;
    }

    public static Set<Integer> getDivisors(int num) {
        Set<Integer> divisors = new TreeSet<>();
        Map<Integer, Integer> map = decompile(num);
        int[] from = new int[map.size()];
        int[] to = new int[map.size()];
        int i = 0;
        for (Integer n : map.values()) {
            to[i++] = n;
        }
        divisors.add(1);
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
            divisors.add(div);
        }
        return divisors;
    }

    public static int solve(int num) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 1; i <= num; i++) {
            Set<Integer> set = getDivisors(i);
            int sum = -i;
            for (int div : set) {
                sum += div;
            }
            map.put(i, sum);
        }
        int sum = 0;
        for (Integer i : map.keySet()) {
            Integer j = map.get(i);
            Integer k = map.get(j);
            if (!i.equals(j) && i.equals(k)) {
                //System.out.println(i + " " + j);
                sum += i;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P021.solve(10000));
        System.out.println(P021.solve2(10000));
    }

    private static long solve2(int nMax) {
        int num[] = new int[nMax * 3];
        for (int n = 1; n <= nMax; n++) {
            int sum = 1;
            for (int i = 2; i < n; i++) {
                sum += n % i == 0 ? i : 0;
            }
            num[n] = sum;
        }
        long rv = 0;
        for (int i = 0; i < nMax; i++) {
            if (num[i] != i && num[num[i]] == i) {
                rv += num[i];
                System.out.println(num[i]);
            }
        }
        return rv;
    }
}
