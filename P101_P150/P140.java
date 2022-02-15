package P101_P150;

import java.util.Set;
import java.util.TreeSet;

/*
 * Consider the infinite polynomial series AG(x) = xG1 + x^2G2 + x^3G3 + ...
 * where Gk is the kth term of the second order recurrence relation 
 * Gk = Gk1 + Gk2, G1 = 1, G2 = 4; that is, 1, 4, 5, 9, 14, 23, ...
 * 
 * For this problem we shall be concerned with values of x for which AG(x) is a positive integer.
 * The corresponding values of x for the first five natural numbers are shown below.
 * 
 *      x            AG(x)
 *      (√5-1)/4        1
 *      2/5     	2
 *      (√22-2)/6 	3
 *      (√37-5)/14	4
 *      1/2     	5
 * We shall call AG(x) a golden nugget if x is rational, because they become 
 * increasingly rarer. For example, the 20th golden nugget is 211345365.
 * 
 * Find the sum of the first thirty golden nuggets.
 *
 * Info:http://www.mathblog.dk/project-euler-140-modified-fibonacci-golden-nuggets/
 */
public class P140 {

    public static void main(String[] args) {
        System.out.println(P140.solve(30));
    }

    public static long solve(int num) {
        Set<Long> set = new TreeSet<>();
        long[] kStart = new long[]{0, 0, -3, -3, -4, -4, 2, 2};
        long[] bStart = new long[]{-1, 1, -2, 2, -5, 5, -7, 7};
        for (int j = 0; j < kStart.length; j++) {
            long k = kStart[j];
            long b = bStart[j];
            for (int i = 0; i < num; i++) {
                long knew = -9 * k + -4 * b + -14;
                long bnew = -20 * k + -9 * b + -28;
                k = knew;
                b = bnew;
                if (k > 0) {
                    set.add(k);
                }
            }
        }
        int c = 0;
        long sum = 0;
        for (long i : set) {
            System.out.println(i);
            sum += i;
            if (++c == num) {
                break;
            }
        }
        return sum;
    }
}
