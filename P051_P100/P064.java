package P051_P100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 Info: http://mathworld.wolfram.com/PellEquation.html
 How many continued fractions for N<=10000 have an odd period?
 */
public class P064 {

    public static int solve(int maxNum) {
        int sum = 0;
        for (int num = 2; num <= maxNum; num++) {
            int D = num;
            int a0 = (int) Math.sqrt(D);
            if (a0 * a0 < D) {
                String out = "√" + D + " = ";
                int lastP = 0;
                int lastQ = 1;
                int lastA = a0;
                int n = 0;
                Set<String> set = new HashSet<>();
                List<Integer> list = new ArrayList<>();
                while (!set.contains(lastP + "-" + lastQ + "-" + lastA)) {
                    set.add(lastP + "-" + lastQ + "-" + lastA);
                    list.add(lastA);
                    out += (lastA + ",");
                    n++;
                    int p = (n == 1) ? a0 : lastA * lastQ - lastP;
                    int q = (n == 1) ? D - (a0 * a0) : (D - p * p) / lastQ;
                    int a = (a0 + p) / q;
                    lastP = p;
                    lastQ = q;
                    lastA = a;
                }
                sum += --n % 2;
                System.out.println(out.replaceFirst(",", ";(") + "\b) " + n);                
                convergent(10, list);
            }
        }
        return sum;
    }

    public static void convergent(int steps, List<Integer> list) {
        int pLast1 = 0, pLast2 = 0, qLast1 = 0, qLast2 = 0;
        for (int n = 0; n < steps; n++) {
            int i = n - (((n - 1) / (list.size() - 1)) * (list.size() - 1));
            int p = (n == 0) ? list.get(0) : (n == 1) ? list.get(0) * list.get(1) + 1 : list.get(i) * pLast1 + pLast2;
            int q = (n == 0) ? 1 : (n == 1) ? list.get(1) : list.get(i) * qLast1 + qLast2;
            pLast2 = pLast1;
            qLast2 = qLast1;
            pLast1 = p;
            qLast1 = q;
            System.out.println("  p/q=" + p + "/" + q);
        }
    }

    public static void main(String[] args) {
        System.out.println(P064.solve(10000));
    }
}
