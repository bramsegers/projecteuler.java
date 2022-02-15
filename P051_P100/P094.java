package P051_P100;

import java.util.ArrayList;

/*
 * It is easily proved that no equilateral triangle exists with integral length 
 * sides and integral area. However, the almost equilateral triangle 5-5-6 
 * has an area of 12 square units.
 * 
 * We shall define an almost equilateral triangle to be a triangle for which 
 * two sides are equal and the third differs by no more than one unit.
 * 
 * Find the sum of the perimeters of all almost equilateral triangles with 
 * integral side lengths and area and whose perimeters do not exceed one billion.
 */
public class P094 {
    //	a(n) = 4*a(n-1) - a(n-2) - 2, with a(0)=1, a(1)=2.
    // then 
    public static long solve(long pMax) {
        long sum = 0;
        long nMax = (pMax + 2) / 6;
        long cSqMax = 3 * nMax * nMax + 4 * nMax + 1;
        long cSq;
        long k0=1;
        long k=4;
                        
        while ((cSq = k * k) < cSqMax) {
            long dSq = cSq * 3 + 1;
            long d = (long) Math.sqrt(dSq);
            long mod = d % 3;
            if (mod == 1) {
                long n = ((d + 2) / 3) << 1;
                sum += 3 * n - 2;
                System.out.format("(%d,%d,%d)%n", n, n - 1, n - 1);
            } else if (mod == 2) {
                long n = ((d - 2) / 3) << 1;
                sum += 3 * n + 2;
                System.out.format("(%d,%d,%d)%n", n, n + 1, n + 1);
            }            
            long t=k0;
            k0=k;
            k=4*k-t;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P094.solve(1000000000));
    }
}
