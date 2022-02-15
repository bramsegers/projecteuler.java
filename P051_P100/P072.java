package P051_P100;

import util.Util;


/*
 Consider the fraction, n/d, where n and d are positive integers. 
 If n<d and gcd(n,d)=1, it is called a reduced proper fraction.

 If we list the set of reduced proper fractions for d<=8 in ascending order of size, we get:
 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
 
 It can be seen that there are 21 elements in this set. 
 How many elements would be contained in the set of reduced proper fractions for d <= 1000000 ?
 */
public class P072 {

    public static long solve(int nMax) {
        long sum = 0;
        Util.initPrimes((int) Math.sqrt(nMax));
        for (int n = 2; n <= nMax; n++) {
            sum += Util.totient(n);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P072.solve(1000000));
    }

}
