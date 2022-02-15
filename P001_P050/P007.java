package P001_P050;

import java.util.ArrayList;
import java.util.List;

/*
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, 
we can see that the 6th prime is 13. What is the 10001st prime number?
 */
public class P007 {

    public static int solve(int nthPrime) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        int primeCandidate = 3;
        while (primes.size() < nthPrime) {
            boolean isPrime = true;
            for (int i = 0; i < primes.size() && isPrime; i++) { 
                isPrime = (primeCandidate % primes.get(i) > 0);
            }
            if (isPrime) {
                primes.add(primeCandidate); 
            }
            primeCandidate++;
        }
        return primes.get(nthPrime-1);
    }

    public static void main(String[] args) {
        System.out.println(P007.solve(10001));
    }
}
