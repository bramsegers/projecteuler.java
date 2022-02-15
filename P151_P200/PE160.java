package P151_P200;

public class PE160 {

    /*
     * For any N, let f(N) be the last five digits before the trailing zeroes in N!.
     * For example,
     * 9! = 362880 so f(9)=36288
     * 10! = 3628800 so f(10)=36288
     * 20! = 2432902008176640000 so f(20)=17664
     * Find f(1,000,000,000,000)
     *
     * Info: https://github.com/nayuki/Project-Euler-solutions/blob/master/p160.java
     * Info: http://www.exploringbinary.com/patterns-in-the-last-digits-of-the-positive-powers-of-two/
     */
    public static void main(String[] args) {
        new PE160().solve(1000000000000L);
    }

    void solve(long nMax) {
        long twos = countFactors(nMax, 2) - countFactors(nMax, 5);
        // We can reduce 'twos' because there is a cycle: 2^5 = 2^2505 = 32 mod 100000
        if (twos >= 2505) {
            twos = (twos - 5) % 2500 + 5;
        }
        long prod = factorialish(nMax) * powMod(2, (int) twos, 100000) % 100000;
        System.out.format("P(%d)=%d%n", nMax, prod);
    }

    int powMod(int n, int e, int mod) {
        int rv = 1;
        for (int i = 0; i < e; i++) {
            rv *= n;
            rv %= mod;
        }
        return rv;
    }

    // Equal to n! but with all factors of 2 and 5 removed and then modulo 10^5.
    // The identity factorialIsh(n) = oddFactorialish(n) * evenFactorialish(n) (mod 10^5) 
    // is true by definition.
    long factorialish(long n) {
        return evenFactorialish(n) * oddFactorialish(n) % 100000;
    }

    // The product of {all even numbers from 1 to n}, but with all factors of 
    // 2 and 5 removed and then modulo 10^5. For example, evenFactorialish(9) 
    // only considers the numbers {2, 4, 6, 8}. Divide each number by 2 
    // to get {1, 2, 3, 4}. Thus evenFactorialish(9) = factorialish(4).
    long evenFactorialish(long n) {
        if (n == 0) {
            return 1;
        } else {
            return factorialish(n / 2);
        }
    }

    // The product of {all odd numbers from 1 to n}, but with all factors of 2 
    // and 5 removed and then modulo 10^5. By definition, oddFactorialish() never 
    // considers any number that has a factor of 2. The product of the numbers 
    // that not a multiple of 5 are accumulated by factorialCoprime().
    // Those that are a multiple of 5 are handled recursively by oddFactorialish(),
    // noting that they are still odd after dividing by 5.
    long oddFactorialish(long n) {
        if (n == 0) {
            return 1;
        } else {
            return oddFactorialish(n / 5) * factorialCoprime(n) % 100000;
        }
    }

    // The product of {all numbers from 1 to n that are coprime with 10}, modulo 10^5.
    // The input argument can be taken modulo 10^5 because factorialoid(10^5) = 1, 
    // and each block of 10^5 numbers behaves the same.
    long factorialCoprime(long n) {
        n %= 100000;
        long product = 1;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0 && i % 5 != 0) {
                product = i * product % 100000;
            }
        }
        return product;
    }

    // Counts the number of factors of n in the set of integers {1, 2, ..., end}.
    // For example, countFactors(25, 5) = 6 because {5, 10, 15, 20} each has 
    // one factor of 5, and 25 has two factors of 5.
    long countFactors(long end, long n) {
        if (end == 0) {
            return 0;
        } else {
            return end / n + countFactors(end / n, n);
        }
    }

}
