package P201_P250;

import util.Util;

public class PE243 {

    public static void main(String[] args) {
        new PE243().solve(15499, 94744);
    }

    void solve(long num, long den) {
        Util.initPrimes(10000000);
        int p = 0, i = 0;
        long n = 1, n2 = 1;
        boolean found = false;
        while (!found) {
            p = Util.primes.get(i++);
            n *= p;
            long t = Util.totient(n);
            found = den * t < num * (n - 1);
        }
        n /= p;
        i = 1;
        found = false;
        while (!found) {
            n2 = n * (++i);
            long t = Util.totient(n2);
            found = den * t < num * (n2 - 1);
        }
        System.out.println(n2); 
    }

}
