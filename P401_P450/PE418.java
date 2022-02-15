package P401_P450;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import util.Primes;
import util.Util;

public class PE418 {

    public static void main(String[] args) {
        new PE418().solve(43, 100);
    }

    TreeSet<Long> set1 = new TreeSet<>(), set2 = new TreeSet<>();

    void solve(int n, int size) {

        BigInteger N = Util.factorial(n);
        long crt = Util.nthRoot(N, 3).longValueExact();
        TreeMap<Long, Integer> map = new Primes(n).factorizeFac(n);

        System.out.println("num     = " + N);
        System.out.println("crt     = " + crt);
        System.out.println("factors = " + map);
        System.out.println("\n..searching for divisors");

        searchDivs(crt, 1, map.firstKey(), map, size);
        System.out.println("greatest " + size + " divisors < crt = " + set1);
        System.out.println("smallest " + size + " divisors > crt = " + set2 + "\n");

        ArrayList<Long> list = new ArrayList<>(set1);
        list.addAll(set2);
        double min = 2;
        long mina = 0, minb = 0, minc = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                long a = list.get(i);
                long b = list.get(j);
                BigInteger ab = BigInteger.valueOf(a).multiply(BigInteger.valueOf(b));
                if (N.mod(ab).signum() == 0) {
                    long c = N.divide(ab).longValue();
                    if (c > b && 1D * c / a < min) {
                        min = 1D * c / a;
                        mina = a;
                        minb = b;
                        minc = c;
                    }
                }
            }
        }
        System.out.format("minimum found: a,b,c = %d,%d,%d %n", mina, minb, minc);
        System.out.format("minimum found: a+b+c = %d %n%n", mina + minb + minc);
    }

    void searchDivs(long crt, long prod, Long p, TreeMap<Long, Integer> map, int size) {
        if (p == null) {
            if (prod < crt) {
                set1.add(prod);
                if (set1.size() > size) {
                    set1.pollFirst();
                }
            } else {
                set2.add(prod);
                if (set2.size() > size) {
                    set2.pollLast();
                }
            }
            return;
        }
        long pe = 1;
        for (int e = 0; prod <= Long.MAX_VALUE / pe && e <= map.get(p); e++) {
            searchDivs(crt, prod * pe, map.higherKey(p), map, size);
            pe *= p;
        }
    }

}
