package P551_P600;

import java.util.ArrayList;
import java.util.List;
import util.Primes;

public class PE569 {

    public static void main(String[] args) {
        new PE569().solve(100, 30);       //       227, 00m00s
        new PE569().solve(2500000, 35000);//  21025060, 13m14s 
    }

    void solve(int m, int trace) {
        
        // init primes
        Primes pr = null;
        int size = 1;
        int M = 2 * m - 1;
        while (pr == null || pr.size() < M) {
            size <<= 1;
            pr = new Primes(size);
        }

        // populate points
        long x = 0, y = 0;
        List<Point> pts = new ArrayList<>();
        long p = 0;
        for (int i = 0; i < M; i++) {
            p = pr.next(p);
            x += p;
            if ((i & 1) == 0) {
                y += p;
                pts.add(new Point(x, y));
            } else {
                y -= p;
            }
        }

        // solve
        long sum = 0;
        for (int i = 1; i < m; i++) {
            double slope = 1;
            Point p1 = pts.get(i);
            int t = 0;
            for (int j = i - 1; j >= 0 && ++t < trace; j--) {
                Point p2 = pts.get(j);
                double s2 = (double) (p1.y - p2.y) / (double) (p1.x - p2.x);
                if (s2 < slope) {
                    slope = s2;
                    sum++;
                }
            }
        }
        
        // result
        System.out.println("sum = " + sum);
        
    }

    class Point {

        long x, y;

        public Point(long i, long j) {
            x = i;
            y = j;
        }

    }

}
