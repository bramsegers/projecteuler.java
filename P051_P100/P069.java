package P051_P100;
 
import util.Util;

/*
 Euler's Totient function, φ(n), called the phi function, is used to determine 
 the number of numbers less than n which are relatively prime to n. For example, 
 1,2,4,5,7 and 8 are all less than 9 and relatively prime to 9, so φ(9)=6.

 n	Relatively Prime	φ(n)	n/φ(n)
 2	1                       1	2
 3	1,2                     2	1.5
 4	1,3                     2	2
 5	1,2,3,4                 4	1.25
 6	1,5                     2	3
 7	1,2,3,4,5,6             6	1.1666...
 8	1,3,5,7                 4       2
 9	1,2,4,5,7,8             6	1.5
 10	1,3,7,9                 4       2.5
 
 It can be seen that n=6 produces a maximum n/φ(n) for n<=10.
 Find the value of n<=1,000,000 for which n/φ(n) is a maximum.
 */
public class P069 {

    public static long solve(int nMax) {
        int maxN = 1;
        int maxPhi = 1;
        Util.initPrimes(nMax);
        for (int n = 2; n <= nMax; n++) {
            int phi=Util.totient(n);
            if (maxPhi * n > phi * maxN) {
                maxN = n;
                maxPhi = phi;
                System.out.println("φ(" + n + ")=" + phi);
            }
        }
        return maxN;
    }

    public static int solve2(int nMax) {
        int i = 0, maxN = 0;
        while ((1 << ++i) < nMax) {
        }
        Util.initPrimes(i);
        int n = 1, j = 0;
        while ((n *= Util.primes.get(j++)) < nMax) { 
            int phi=Util.totient(n);
            System.out.println("φ(" + n + ")=" + phi + " -> " + (1D * n / phi));
            maxN = n;
        }
        return maxN;
    }

    public static void main(String[] args) {
        //System.out.println(P069.solve(1000000));
        System.out.println(P069.solve2(1000000));
    }

}
