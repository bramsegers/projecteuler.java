package P051_P100;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 Consider quadratic Diophantine equations of the form:
 x^2 – Dy^2 = 1
 For example, when D=13, the minimal solution in x is 649^2 – 13x180^2 = 1.
 It can be assumed that there are no solutions in positive integers when D is square.

 By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:
 3^2 – 2x2^2 = 1
 2^2 – 3x1^2 = 1
 9^2 – 5x4^2 = 1
 5^2 – 6x2^2 = 1
 8^2 – 7x3^2 = 1
 Hence, by considering minimal solutions in x for D<=7, the largest x is obtained when D=5.
 
 Find the value of D<=1000 in minimal solutions of x for which the largest value of x is obtained.
 */
public class P066 {

    static BigInteger maxX = BigInteger.ZERO;
    static BigInteger maxY = BigInteger.ZERO;

    public static String solve(int maxNum) {
        int maxD = 0;
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
                System.out.println(out.replaceFirst(",", ";(") + "\b) " + --n);
                if (convergent(n % 2 == 0 ? n : 2 * n, list)) {
                    maxD = num;
                }
            }
        }
        return "D="+maxD+" -> (x,y)=("+maxX + "," + maxY + ")";
    }

    public static boolean convergent(int steps, List<Integer> list) {
        BigInteger pLast1 = BigInteger.ZERO;
        BigInteger pLast2 = BigInteger.ZERO;
        BigInteger qLast1 = BigInteger.ZERO;
        BigInteger qLast2 = BigInteger.ZERO;
        BigInteger list0 = new BigInteger(list.get(0).toString());
        BigInteger list1 = new BigInteger(list.get(1).toString());
        for (int n = 0; n < steps; n++) {
            int i = n - (((n - 1) / (list.size() - 1)) * (list.size() - 1));
            BigInteger p =
                    (n == 0) ? list0
                    : (n == 1) ? (list0.multiply(list1)).add(BigInteger.ONE)
                    : (new BigInteger(list.get(i).toString()).multiply(pLast1)).add(pLast2);
            BigInteger q =
                    (n == 0) ? BigInteger.ONE
                    : (n == 1) ? list1
                    : (new BigInteger(list.get(i).toString()).multiply(qLast1)).add(qLast2);
            pLast2 = pLast1;
            qLast2 = qLast1;
            pLast1 = p;
            qLast1 = q;
        }
        System.out.println("  (x,y) = (" + pLast1 + "," + qLast1 + ")");
        if (pLast1.compareTo(maxX) > 0) {
            maxX = pLast1;
            maxY = qLast1;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(P066.solve(1000));
    }
}
