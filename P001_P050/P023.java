package P001_P050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 A perfect number is a number for which the sum of its proper divisors is exactly 
 equal to the number. For example, the sum of the proper divisors of 28 would be 
 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

 A number n is called deficient if the sum of its proper divisors is less than n 
 and it is called abundant if this sum exceeds n.

 As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest 
 number that can be written as the sum of two abundant numbers is 24. 
 By mathematical analysis, it can be shown that all integers greater than 28123 
 can be written as the sum of two abundant numbers. However, this upper limit 
 cannot be reduced any further by analysis even though it is known that the 
 greatest number that cannot be expressed as the sum of two abundant numbers 
 is less than this limit.

 Find the sum of all the positive integers which cannot be written 
 as the sum of two abundant numbers.
 */
public class P023 {

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

    public static int solve(int nMax) {
        List<Integer> abundant = new ArrayList<>();
        for (int i = 1; i <= nMax; i++) {
            Set<Integer> set = getDivisors(i);
            int sum = -i;
            for (int div : set) {
                sum += div;
            }
            if (sum > i) {
                abundant.add(i);
            }
        }
        boolean[] abSum = new boolean[nMax];
        for (int i = 0; i < abundant.size(); i++) {
            int n1 = abundant.get(i);
            int n2 = 0;
            for (int j = 0; j < abundant.size() && (n2 = abundant.get(j)) + n1 < nMax; j++) {
                abSum[n1 + n2] = true;
            }
        }
        int sum = 0;
        for (int i = 0; i < nMax; i++) {
            sum += abSum[i] ? 0 : i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P023.solve(28123));
    }
}
