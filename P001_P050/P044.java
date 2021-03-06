package P001_P050;

import java.util.ArrayList;
import java.util.List;

/*
 Pentagonal numbers are generated by the formula, P(n) = n(3n-1)/2. 
 The first ten pentagonal numbers are:
 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 It can be seen that P(4) + P(7) = 22 + 70 = 92 = P(8). 
 However, their difference, 70 - 22 = 48, is not pentagonal.

 Find the pair of pentagonal numbers, P(j) and P(k), for which their 
 sum and difference are pentagonal and D = |P(k)-P(j)| is minimised.
 What is the value of D?
 */
public class P044 {

    static String solve() {
        List<Long> pentas = new ArrayList<>();
        long i = 0, p1 = 0, p2 = 0;
        boolean found = false;
        while (!found) {
            i++;
            p2 = (i * ((3 * i) - 1) / 2);
            for (int j = 0; j < pentas.size() && !found; j++) {
                p1 = pentas.get(j);
                found = isPentagonal(p2 + p1) && isPentagonal(p2 - p1);
            }
            pentas.add(p2);
        }
        return "P(j)=" + p1 + ", P(k)=" + p2 + ", P(k)-P(j)=" + (p2 - p1);
    }

    static boolean isPentagonal(long x) {
        return ((0.5D + Math.sqrt(0.25D + 6 * x)) / 3) % 1 == 0;
    }

    public static void main(String[] args) {
        System.out.println(P044.solve());
    }
}
