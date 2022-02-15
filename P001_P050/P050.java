package P001_P050;

import util.Util;

/*
 The prime 41, can be written as the sum of six consecutive primes:
 41 = 2 + 3 + 5 + 7 + 11 + 13. 
 
 This is the longest sum of consecutive primes that adds to a prime below 
 one-hundred. The longest sum of consecutive primes below one-thousand that 
 adds to a prime, contains 21 terms, and is equal to 953.

 Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
public class P050 {

    static String solve(int nMax) {
        String out = null;
        Util.initPrimes(nMax);
        int maxFrom = 0, maxTo = 0, from = 0, sum = 0;
        while (from * (maxTo - maxFrom + 1) < nMax) {
            for (int to = from; (sum += Util.primes.get(to)) < nMax; to++) {
                if (Util.isPrime[sum] && to - from > maxTo - maxFrom) {
                    maxFrom = from;
                    maxTo = to;
                    out = String.format(
                            "%d:%d+..+%d=%d",
                            maxTo - maxFrom + 1,
                            Util.primes.get(maxFrom),
                            Util.primes.get(maxTo),
                            sum);
                    System.out.println(out);
                }
            }
            sum -= Util.primes.get(from);
            from++;
            sum = 0;
        }
        return "Max found: " + out;
    }

    public static void main(String[] args) {
        System.out.println(P050.solve(1000000));
    }
}
