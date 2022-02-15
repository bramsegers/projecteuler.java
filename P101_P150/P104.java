package P101_P150;

import java.util.HashSet;
import java.util.Set;

/*
 * The Fibonacci sequence is defined by the recurrence relation:
 * Fn = Fn1 + Fn2, where F1 = 1 and F2 = 1.
 * 
 * It turns out that F541, which contains 113 digits, is the first Fibonacci number 
 * for which the last nine digits are 1-9 pandigital (contain all the digits 1 to 9, 
 * but not necessarily in order). And F2749, which contains 575 digits, is the first 
 * Fibonacci number for which the first nine digits are 1-9 pandigital.
 * 
 * Given that Fk is the first Fibonacci number for which the first nine digits 
 * AND the last nine digits are 1-9 pandigital, find k.
 * Info: http://www.mathblog.dk/project-euler-104-fibonacci-pandigital/
 */
public class P104 {

    public static int solve() {
        double a = Math.log10((1 + Math.sqrt(5)) / 2);
        double b = Math.log10(Math.sqrt(5));
        Set<Long> pandigits = new HashSet<>();
        permutation("", "123456789", pandigits);
        long fn2 = 1;
        long fn1 = 1;
        long fn;
        long tailcut = 1000000000;
        int n = 2;
        boolean found = false;
        while (!found) {
            n++;
            fn = (fn1 + fn2) % tailcut;
            fn2 = fn1;
            fn1 = fn;
            if (pandigits.contains(fn)) {
                double t = n * a - b;
                found = pandigits.contains((long) Math.pow(10, t - (long) t + 8));
            }
        }
        return n;
    }

    private static void permutation(String pre, String s, Set<Long> pandigits) {
        int n = s.length();
        if (n == 0) {
            pandigits.add(Long.parseLong(pre));
        } else {
            for (int i = 0; i < n; i++) {
                permutation(pre + s.charAt(i), s.substring(0, i) + s.substring(i + 1), pandigits);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P104.solve());
    }
}
