package P551_P600;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Primes;

//https://oeis.org/wiki/Sum_of_divisors_function
//http://math.stackexchange.com/questions/1844289/project-eulers-problem-565
public class PE565 {

    public static void main(String[] args) {
        new PE565().brute((int) 1e6, 2017);
        new PE565().solve((long) 1e11, 2017);
        /*
            init primes (397 ms)
            sum=2992480851924313898 (119900 ms) 
         */
    }

    Primes initPrimes() {
        long t = System.currentTimeMillis();
        long lim = (long) 1e9;
        Primes p = new Primes(lim);
        t = System.currentTimeMillis() - t;
        System.out.format("init primes (%d ms)%n", t);
        return p;
    }

    void solve(long n, int P) {

        // init
        Primes pr = initPrimes();
        List<Point> points = new ArrayList<>();
        long sum = 0;
        long t = System.currentTimeMillis();

        // p^1
        for (long k = 2; k * P <= n; k += 2) {
            long p = k * P - 1;
            if (isPrime(p, pr)) {
                points.add(new Point(p, p));
            }
        }

        // p^e, e>1
        for (int e = 2; e < 64; e++) {
            double pe;
            for (long p = 2; (pe = Math.pow(p, e)) <= n; p += (p == 2) ? 1 : 2) {
                long sigma = (long) ((pe * p - 1) / (p - 1));
                if (sigma % P == 0 && isPrime(p, pr)) {
                    points.add(new Point(p, (long) pe));
                }
            }
        }

        // sort 
        Collections.sort(points);

        // include
        for (Point pt : points) {
            for (long k = 1; k * pt.pe <= n; k++) {
                if (k % pt.p != 0) {
                    sum += k * pt.pe;
                }
            }
        }

        // exclude
        int i, j;
        for (long pe1 = points.get(i = 0).pe; pe1 * pe1 <= n; pe1 = points.get(++i).pe) {
            for (long pe2 = points.get(j = i + 1).pe; pe1 * pe2 <= n; pe2 = points.get(++j).pe) {
                for (long k = 1; k * pe1 * pe2 <= n; k++) {
                    sum -= k * pe1 * pe2;
                }
            }
        }

        // show result
        t = System.currentTimeMillis() - t;
        System.out.format("sum=%d (%d ms) %n", sum, t);
    }

    boolean isPrime(long p, Primes pr) {
        return p < pr.limit()
                ? pr.contains(p)
                : BigInteger.valueOf(p).isProbablePrime(10);
    }

    void brute(int n, int P) {

        long sum = 0;
        int[] arr = new int[n + 1];

        for (int d = 1; d <= n; d++) {
            for (int md = d; md <= n; md += d) {
                arr[md] += d;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (arr[i] % P == 0) {
                sum += i;
            }
        }

        System.out.println("sum=" + sum);
    }

    class Point implements Comparable<Point> {

        long p, pe;

        Point(long p, long pe) {
            this.p = p;
            this.pe = pe;
        }

        @Override
        public int compareTo(Point o) {
            long d = pe - o.pe;
            return d == 0 ? 0 : d < 0 ? -1 : 1;
        }

    }

}
