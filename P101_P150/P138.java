package P101_P150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Consider the isosceles triangle with base length, b = 16, and legs, L = 17.
 * By using the Pythagorean theorem it can be seen that the height of the triangle, 
 * h = √(17² - 8²) = 15, which is one less than the base length.
 * 
 * With b = 272 and L = 305, we get h = 273, which is one more than the base length, 
 * and this is the second smallest isosceles triangle with the property that h = b ± 1.
 * 
 * Find ∑ L for the 12 smallest isosceles triangles for which h = b ± 1 and b, L are positive integers.
 */
public class P138 {

    public static void main(String[] args) {
        System.out.println("       |\\                           ");
        System.out.println("       | \\                          ");
        System.out.println("       |  \\                         ");
        System.out.println("     h |   \\ L          h = b ± 1   ");
        System.out.println("       |    \\           x² + h² = L²");
        System.out.println("       |     \\                      ");
        System.out.println("       --------                      ");
        System.out.println("        ½b = x                       ");
        System.out.println();
        P138.solve(12);
    }

    private static void solve(int num) {
        int D = 5;
        int a0 = (int) Math.sqrt(D);
        if (a0 * a0 < D) {
            long lastP = 0;
            long lastQ = 1;
            long lastA = a0;
            int n = 0;
            Set<String> set = new HashSet<>();
            List<Long> list = new ArrayList<>();
            while (!set.contains(lastP + "-" + lastQ + "-" + lastA)) {
                set.add(lastP + "-" + lastQ + "-" + lastA);
                list.add(lastA);
                n++;
                long p = (n == 1) ? a0 : lastA * lastQ - lastP;
                long q = (n == 1) ? D - (a0 * a0) : (D - p * p) / lastQ;
                long a = (a0 + p) / q;
                lastP = p;
                lastQ = q;
                lastA = a;
            }
            convergent(num, list);
        }
    }

    public static void convergent(int solutions, List<Long> list) {
        long sum = 0;
        long pLast1 = 0, pLast2 = 0, qLast1 = 0, qLast2 = 0;
        int n = 0;
        int solutionsFound = 0;
        while (solutionsFound < solutions) {
            int i = n - (((n - 1) / (list.size() - 1)) * (list.size() - 1));
            long p = (n == 0) ? list.get(0) : (n == 1) ? list.get(0) * list.get(1) + 1 : list.get(i) * pLast1 + pLast2;
            long q = (n == 0) ? 1 : (n == 1) ? list.get(1) : list.get(i) * qLast1 + qLast2;
            pLast2 = pLast1;
            qLast2 = qLast1;
            pLast1 = p;
            qLast1 = q;
            if (++n % 2 == 1) {
                boolean sol1 = (p - 2) % 5 == 0;
                boolean sol2 = (p + 2) % 5 == 0;
                if (p > 2 && (sol1 || sol2)) {
                    long x = sol1 ? (p - 2) / 5 : (p + 2) / 5;
                    solutionsFound++;
                    sum += q;
                    System.out.println("(x,L) = (" + x + "," + q+")");
                }
            }
        }
        System.out.println("∑ L = " + sum);
    }
}
