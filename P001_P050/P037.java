package P001_P050;

import java.util.ArrayList;
import java.util.List;

/*
 The number 3797 has an interesting property. Being prime itself, it is possible 
 to continuously remove digits from left to right, and remain prime at each stage: 
 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

 Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class P037 {

    public static int solve(int num) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        int primeCandidate = 3;
        while (primeCandidate < num) {
            boolean isPrime = true;
            for (int i = 0; i < primes.size() && isPrime && primes.get(i) <= Math.sqrt(primeCandidate); i++) {
                isPrime = (primeCandidate % primes.get(i) > 0);
            }
            if (isPrime) {
                primes.add(primeCandidate);
            }
            primeCandidate += 2;
        }
        int sum = 0;
        int cnt = 0;
        for (int p : primes) {
            if (p > 9 && isTruncatable(p, primes)) {
                System.out.println(++cnt + ": " + p);
                sum += p;
            }
        }
        return sum;
    }

    private static boolean isTruncatable(int p, List<Integer> primes) {
        String pStr = String.valueOf(p);
        for (int i = 0; i < pStr.length(); i++) {
            int trunc1 = Integer.parseInt(pStr.substring(i));
            int trunc2 = Integer.parseInt(pStr.substring(0, i + 1));
            if (!(primes.contains(trunc1) && primes.contains(trunc2))) {
                return false;
            }
        }
        return true;
    }

    public static int solve2() {
        boolean[] isComposite = new boolean[1000000];
        isComposite[0] = true;
        isComposite[1] = true;
        for (int n = 2; n * n < isComposite.length; n++) {
            if (!isComposite[n]) {
                for (int j = n; n * j < isComposite.length; j++) {
                    isComposite[n * j] = true;
                }
            }
        }
        int count = 0, sum = 0, d = 10, n = 1;
        while (count < 11) {
            if (++n % d == 0) {
                d *= 10;
            }
            if (n >= 10 && isTruncPrime(n, d, isComposite)) {
                count++;
                sum += n;
                System.out.println(count+": "+n);
            }

        }
        return sum;
    }

    private static boolean isTruncPrime(int p, int d, boolean[] comp) {
        //right trunc
        int p2 = p;
        while (p2 > 0) {
            if (comp[p2]) {
                return false;
            }
            p2 /= 10;
        }
        //left trunc
        while ((d /= 10) > 1) {
            p2 = p - ((p / d) * d);
            if (comp[p2]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //System.out.println(P037.solve(1000000));
        System.out.println(P037.solve2());
    }
}
