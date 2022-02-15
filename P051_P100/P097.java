package P051_P100;

/*
 * The first known prime found to exceed one million digits was discovered in 1999, 
 * and is a Mersenne prime of the form 2^6972593-1. It contains exactly 2098960 digits. 
 * Subsequently other Mersenne primes, of the form 2^p-1, have been found which contain more digits.
 * 
 * However, in 2004 there was found a massive non-Mersenne prime which contains 2357207 digits: 
 * 28433 * 2^7830457 + 1. 
 * 
 * Find the last ten digits of this prime number.
 */
public class P097 {

    // last d digits of a * 2^b + c
    public static long solve(int a, int b, int c, int d) {
        long mod = 1;
        for (int i = 0; i < d; i++) {
            mod *= 10;
        }
        long n = 1;
        for (int i = 0; i < b; i++) {
            n <<= 1;
            n %= mod;
        }
        n *= a;
        n += c;
        n %= mod;
        return n;
    }

    public static void main(String[] args) {
        System.out.println(P097.solve(28433, 7830457, 1, 10));
    }
}
