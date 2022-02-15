package P051_P100;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
 Let p(n) represent the number of different ways in which n coins can be separated 
 into piles. For example, five coins can separated into piles in exactly seven 
 different ways, so p(5)=7.
 OOOOO
 OOOO O
 OOO OO
 OOO O O
 OO OO O
 OO O O O
 O O O O O
 Find the least value of n for which p(n) is divisible by one million.
 
 info: http://mathworld.wolfram.com/PartitionFunctionP.html
 info: http://en.wikipedia.org/wiki/Partition_
 info: https://www.google.nl/search?q=partition+function+divisor 
 */
public class P078 {

    public static long solve(int div) {
        BigInteger d = new BigInteger(String.valueOf(div));
        List<BigInteger> p = new ArrayList<>();
        p.add(BigInteger.ONE);
        boolean divFound = false;
        long n = 0;
        while (!divFound) {
            n++;
            long t = 0;
            long k = 1;
            long g;
            boolean m = true;
            BigInteger rv = BigInteger.ZERO;
            while ((g = ((3 * k * k) - k) / 2) <= n) {
                if (m) {
                    rv = rv.add(p.get((int) (n - g)));
                } else {
                    rv = rv.subtract(p.get((int) (n - g)));
                }
                k = -k;
                if (++t == 2) {
                    t = 0;
                    k++;
                    m = !m;
                }
            }
            p.add(rv);
            System.out.println(n + ": " + rv);
            divFound = rv.mod(d).equals(BigInteger.ZERO);
        }
        return n;
    }

    public static long solve2(int mod) {
        List<Integer> p = new ArrayList<>();
        p.add(1);
        boolean divFound = false;
        int n = 0;
        while (!divFound) {
            n++;
            int t = 0;
            int k = 1;
            int g;
            boolean m = true;
            int rv = 0;
            while ((g = ((3 * k * k) - k) / 2) <= n) {
                rv += m ? p.get(n - g) : -p.get(n - g);
                rv %= mod;
                k = -k;
                if (++t == 2) {
                    t = 0;
                    k++;
                    m = !m;
                }
            }
            p.add(rv);
            divFound = rv % mod == 0;
        }
        return n;
    }

    public static void main(String[] args) {
        //System.out.println(P078.solve(1000000));
        System.out.println(P078.solve2(1000000));
    }
}
