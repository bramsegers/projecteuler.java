package P101_P150;

import util.Util;

/*
 * Consider the consecutive primes p1 = 19 and p2 = 23. It can be verified that 
 * 1219 is the smallest number such that the last digits are formed by p1 whilst 
 * also being divisible by p2. In fact, with the exception of p1 = 3 and p2 = 5, 
 * for every pair of consecutive primes, p2 > p1, there exist values of n for 
 * which the last digits are formed by p1 and n is divisible by p2. 
 * 
 * Let S be the smallest of these values of n.
 * Find Σ S for every pair of consecutive primes with 5 <= p1 <= 1000000.
 */
public class P134 {

    public static void main(String[] args) {
        //System.out.println(P134b.solve(5, 100000));
        System.out.println(P134.solve2(5, 1000000));
    }

    public static long solve(int nMin, int nMax) {
        long sum = 0;
        Util.initPrimes(nMax);
        Util.addNextPrime();
        for (int i = 2; i < Util.primes.size() - 1; i++) {
            long p1 = Util.primes.get(i);
            long p2 = Util.primes.get(i + 1);
            int d = (int) Math.pow(10, (int) Math.log10(p1) + 1);
            long n = p2, j = 2;
            while (((n += p2) % d) != p1) {
                j++;
            }
            sum += n;
            System.out.println(p1 + " " + p2 + " " + n);
        }
        return sum;
    }

    public static long solve2(int nMin, int nMax) {
        long sum = 0;
        Util.initPrimes(nMax);
        Util.addNextPrime();
        for (int i = 2; i < Util.primes.size() - 1; i++) {
            long p1 = Util.primes.get(i);
            long p2 = Util.primes.get(i + 1);
            int d = (int) Math.pow(10, (int) Math.log10(p1) + 1);
            long x = (p1 * solveLineairDiophantine(d, p2)) % d;
            x += x < 0 ? d : 0;
            x *= p2;
            sum += x;
        }
        return sum;
    }

    /**
     * Solves Ax+By=1 with A,x,B,y integer
     *
     * @param a A
     * @param b B 
     * @return x
     */
    public static long solveLineairDiophantine(long a, long b) {
        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        while (b != 0) {
            long q = a / b;
            long r = a % b;
            a = b;
            b = r;
            temp = x;
            x = lastx - q * x;
            lastx = temp;
            temp = y;
            y = lasty - q * y;
            lasty = temp;
        }
        // x = lastx
        // y = lasty
        return lasty;
    }

}
