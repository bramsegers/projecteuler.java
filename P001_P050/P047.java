package P001_P050;

import util.Util;

/*
 The first two consecutive numbers to have two distinct prime factors are:
 14 = 2 x 7
 15 = 3 x 5
 
 The first three consecutive numbers to have three distinct prime factors are:
 644 = 2^2 x  7 x 23
 645 = 3   x  5 x 43
 646 = 2   x 17 x 19
  
 Find the first four consecutive integers to have four distinct prime factors.
 What is the first of these numbers?
 */
public class P047 {

    public static int solve(int nrFactors, int length) {
        Util.initPrimes(500000);
        int firstNum = 0;
        int sequence = 0;
        int num = 1;
        while (sequence < length) {
            int factors = 0;
            int i = 0;
            int n = ++num;
            while (!Util.isPrime[num] && n > 1 && factors <= nrFactors) {
                int p = Util.primes.get(i++);
                factors += n % p == 0 ? 1 : 0;
                while (n % p == 0) {
                    n /= p;
                }
            }
            if (factors >= nrFactors) {
                firstNum = (sequence == 0) ? num : firstNum;
                //System.out.println(num);
                sequence++;
            } else {
                sequence = 0;
            }
        }
        return firstNum;
    }

    public static void main(String[] args) {
        System.out.println(P047.solve(4, 4));
    }
}
