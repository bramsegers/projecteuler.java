package P001_P050;

import java.util.ArrayList;
import java.util.List;

/*
 It was proposed by Christian Goldbach that every odd composite number 
 can be written as the sum of a prime and twice a square.
 9  =  7 + 2*1^2
 15 =  7 + 2*2^2
 21 =  3 + 2*3^2
 25 =  7 + 2*3^2
 27 = 19 + 2*2^2
 33 = 31 + 2*1^2

 It turns out that the conjecture was false.
 What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */
public class P046 {

    public static List<Integer> primes = new ArrayList<>();

    public static int solve() {
        primes.add(2);
        int i = 3;
        while (goldbachIsRight(i)) {
            i += 2;
        }
        return i;
    }

    public static boolean goldbachIsRight(int i) {
        if (isPrime(i)) {
            return true;
        }
        for (int j = 1; j < primes.size(); j++) {
            int p = primes.get(j);
            int aSquare = (i - p) / 2;
            int a = (int) Math.sqrt(aSquare);
            if (a * a == aSquare) {
                System.out.println(i + " = " + p + " + 2*" + a + "^2");
                return true;
            }
        }
        return false;
    }

    public static boolean isPrime(int i) {
        int j;
        int k = 0;
        while ((j = primes.get(k++)) <= Math.sqrt(i)) {
            if (i % j == 0) {
                return false;
            }
        }
        primes.add(i);
        return true;
    }

    public static void main(String[] args) {
        System.out.println(P046.solve());
    }
}
