package util;

/*
 * Written By: Gregory Owen
 * Date: 10/10/11
 * Finds a single congruence equivalent to multiple given congruences
 * (assuming that one exists) via the Chinese Remainder Theorem
 */
public class CRT {
    /*
     * performs the Euclidean algorithm on a and b to find a pair of coefficients
     * (stored in the output array) that correspond to x and y in the equation
     * ax + by = gcd(a,b)
     * constraint: a > b
     */

    public static int[] euclidean(int a, int b) {
        if (b > a) {
            //reverse the order of inputs, run through this method, then reverse outputs
            int[] coeffs = euclidean(b, a);
            int[] output = {coeffs[1], coeffs[0]};
            return output;
        }

        int q = a / b;
        //a = q*b + r --> r = a - q*b
        int r = a - q * b;

        //when there is no remainder, we have reached the gcd and are done
        if (r == 0) {
            int[] output = {0, 1};
            return output;
        }

        //call the next iteration down (b = qr + r_2)
        int[] next = euclidean(b, r);

        int[] output = {next[1], next[0] - q * next[1]};
        return output;
    }

    public static long[] euclidean(long a, long b) {
        if (b > a) {
            //reverse the order of inputs, run through this method, then reverse outputs
            long[] coeffs = euclidean(b, a);
            long[] output = {coeffs[1], coeffs[0]};
            return output;
        }

        long q = a / b;
        //a = q*b + r --> r = a - q*b
        long r = a - q * b;

        //when there is no remainder, we have reached the gcd and are done
        if (r == 0) {
            long[] output = {0, 1};
            return output;
        }

        //call the next iteration down (b = qr + r_2)
        long[] next = euclidean(b, r);

        long[] output = {next[1], next[0] - q * next[1]};
        return output;
    }

    //finds the least positive integer equivalent to a mod m
    public static long leastPosEquiv(long a, long m) {
        //a eqivalent to b mod -m <==> a equivalent to b mod m
        if (m < 0) {
            return leastPosEquiv(a, -1 * m);
        }
        //if 0 <= a < m, then a is the least positive integer equivalent to a mod m
        if (a >= 0 && a < m) {
            return a;
        }

        //for negative a, find the least negative integer equivalent to a mod m
        //then add m
        if (a < 0) {
            return -1 * leastPosEquiv(-1 * a, m) + m;
        }

        //the only case left is that of a,m > 0 and a >= m
        //take the remainder according to the Division algorithm
        long q = a / m;

        /*
         * a = qm + r, with 0 <= r < m
         * r = a - qm is equivalent to a mod m
         * and is the least such non-negative number (since r < m)
         */
        return a - q * m;
    }

    /**
     * E.g. solve(new long[]{c1,c2,c3},new long[]{m1,m2,m3}) Finds a number x such
     * that: x = c1 mod m1, x = c2 mod m2, x = c3 mod m3. Note that the values
     * in mods must be mutually prime. I.e. solves Diophantine equation
     *
     * @param constraints
     * @param mods
     * @return 
     */
    public static long solve(long[] constraints, long[] mods) {

        //constraints = new long[]{13 * 17}; //put modular contraints here
        //mods = new int[]{100}; //put moduli here
        //M is the product of the mods
        long M = 1;
        for (int i = 0; i < mods.length; i++) {
            M *= mods[i];
        }

        long[] multInv = new long[constraints.length];

        /*
         * this loop applies the Euclidean algorithm to each pair of M/mods[i] and mods[i]
         * since it is assumed that the various mods[i] are pairwise coprime,
         * the end result of applying the Euclidean algorithm will be
         * gcd(M/mods[i], mods[i]) = 1 = a(M/mods[i]) + b(mods[i]), or that a(M/mods[i]) is
         * equivalent to 1 mod (mods[i]). This a is then the multiplicative
         * inverse of (M/mods[i]) mod mods[i], which is what we are looking to multiply
         * by our constraint constraints[i] as per the Chinese Remainder Theorem
         * euclidean(M/mods[i], mods[i])[0] returns the coefficient a
         * in the equation a(M/mods[i]) + b(mods[i]) = 1
         */
        for (int i = 0; i < multInv.length; i++) {
            multInv[i] = euclidean(M / mods[i], mods[i])[0];
        }

        long x = 0;

        //x = the sum over all given i of (M/mods[i])(constraints[i])(multInv[i])
        for (int i = 0; i < mods.length; i++) {
            x += (M / mods[i]) * constraints[i] * multInv[i];
        }

        x = leastPosEquiv(x, M);

        //System.out.println("x is equivalent to " + x + " mod " + M);
        return x;

    }
    
    /**
     * Returns the smallest non-negative solution x to the system<br>
     * x = a mod n<br>
     * x = b mod m<br>
     * if such a solution exists, otherwise 0<br>
     * @param a
     * @param b
     * @param n
     * @param m
     * @return x
     */
    public static long solve(long a,long b,long n,long m){
        long g=Util.gcd(n,m);
        if((a-b)%g!=0) return 0;
        long[] mi=euclidean(m,n);
        long x=m*a*mi[0]+n*b*mi[1];
        return leastPosEquiv(x,n*m)/g;
    }
    
}
