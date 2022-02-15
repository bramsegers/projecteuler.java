package P051_P100;

import util.Util;

/*
 Starting with 1 and spiralling anticlockwise in the following way, 
 a square spiral with side length 7 is formed.
 
 37 36 35 34 33 32 31
 38 17 16 15 14 13 30
 39 18  5  4  3 12 29
 40 19  6  1  2 11 28
 41 20  7  8  9 10 27
 42 21 22 23 24 25 26
 43 44 45 46 47 48 49

 It is interesting to note that the odd squares lie along the bottom right 
 diagonal, but what is more interesting is that 8 out of the 13 numbers lying 
 along both diagonals are prime; that is, a ratio of 8/13 = 62%.

 If one complete new layer is wrapped around the spiral above, a square spiral 
 with side length 9 will be formed. If this process is continued, what is the 
 side length of the square spiral for which the ratio of primes along both 
 diagonals first falls below 10%?
 */
public class P058 {

    public static int solve(int perc) {
        int total = 1;
        int primes = 0;
        int n = 1;
        int dn = 2;
        int layer = 0;
        while (primes == 0 || perc * total < 100 * primes) {
            for (int i = 0; i < 4; i++) {
                total++;
                n += dn;
                primes += Util.isPrime(n) ? 1 : 0;
            }
            dn += 2;
            layer++;
            System.out.format("Size: %d: (p/t)=(%d,%d)=%f%n", 2 * layer + 1, primes, total, 1D * primes / total);
        }
        return 2 * layer + 1;
    }

    public static void main(String[] args) {
        System.out.println(P058.solve(10));
    }
}
