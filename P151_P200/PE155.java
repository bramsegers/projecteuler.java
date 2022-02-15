package P151_P200;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import util.Util;

public class PE155 {

    /*
     * Let S(n) be the set of all possible capacitances which can be made by using 
     * exactly n capacitors of unit capacitance. Then S(1)={1}, S(2)={½,2}. 
     * Every element of S(n) for n>2 can be found as either the parallel combination 
     * or the series combination of some capacitance in S(j) and some capacitance 
     * in S(n-j) for 0<j<n
     *
     * Series is: http://oeis.org/A153588
     */
    public static void main(String[] args) {
        new PE155().solve(18);
    }

    void solve(int nMax) {
        Map<Integer, Set<Cap>> map = new HashMap<>();
        Set<Cap> all = new HashSet<>();
        for (int n = 1; n <= nMax; n++) {
            Set<Cap> set = new HashSet<>();
            if (n == 1) {
                set.add(new Cap(1, 1));
            } 
            for (int j = 1; j <= n / 2; j++) {
                for (Cap c1 : map.get(j)) {
                    for (Cap c2 : map.get(n - j)) {
                        set.add(parallel(c1, c2));
                        set.add(series(c1, c2));
                    }
                }
            }            
            all.addAll(set);
            map.put(n, set);
            System.out.println(n + " " + all.size());
        }

    }

    Cap parallel(Cap c1, Cap c2) {
        int p = c1.P * c2.Q + c2.P * c1.Q;
        int q = c1.Q * c2.Q;
        int gcd = Util.gcd(p, q);
        return new Cap(p / gcd, q / gcd);
    }

    Cap series(Cap c1, Cap c2) {
        int q = c1.Q * c2.P + c2.Q * c1.P;
        int p = c1.P * c2.P;
        int gcd = Util.gcd(p, q);
        return new Cap(p / gcd, q / gcd);
    }

    private static class Cap {

        int P, Q;

        public Cap(int p, int q) {
            P = p;
            Q = q;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 47 * hash + P;
            hash = 47 * hash + Q;
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Cap
                    && P == ((Cap) o).P
                    && Q == ((Cap) o).Q;
        }

    }

}
