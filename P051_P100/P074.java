package P051_P100;

import java.util.HashSet;
import java.util.Set;

/*
 The number 145 is well known for the property that the sum of the factorial 
 of its digits is equal to 145: 1! + 4! + 5! = 1 + 24 + 120 = 145

 Perhaps less well known is 169, in that it produces the longest chain of numbers 
 that link back to 169; it turns out that there are only three such loops that exist:
 169 -> 363601 -> 1454 -> 169
 871 -> 45361 -> 871
 872 -> 45362 -> 872

 It is not difficult to prove that EVERY starting number will eventually 
 get stuck in a loop. For example,
 69 -> 363600 -> 1454 -> 169 -> 363601 -> (1454)
 78 -> 45360 -> 871 -> 45361 -> (871)
 540 -> 145 -> (145)

 Starting with 69 produces a chain of five non-repeating terms, but the longest 
 non-repeating chain with a starting number below one million is sixty terms.

 How many chains, with a starting number below one million, contain exactly 
 sixty non-repeating terms?
 */
public class P074 {

    static int[] fac, len;

    public static int solve(int nMax, int lMax) {
        int sum = 0;
        fac = new int[10];
        fac[0] = 1;
        for (int i = 1; i < 10; i++) {
            fac[i] = i * fac[i - 1];
        }
        for (int n = 1; n <= nMax; n++) {
            Set<Integer> set = new HashSet<>();
            int i = n;
            while (!set.contains(i)) {
                set.add(i);
                i = facSum(i);
            }
            sum += set.size() == lMax ? 1 : 0;
        }
        return sum;
    }

    public static int solve2(int nMax, int lMax) {
        len = new int[3000000];
        len[169] = 3;
        len[871] = 2;
        len[872] = 2;
        fac = new int[10];
        fac[0] = 1;
        for (int i = 1; i < 10; i++) {
            fac[i] = i * fac[i - 1];
        }
        int sum = 0;
        for (int n = 1; n <= nMax; n++) {
            sum += len(n) == lMax ? 1 : 0;
        }
        return sum;
    }

    private static int len(int n) {
        if (len[n] == 0) {
            if (facSum(n) == n) {
                len[n] = 1;
            } else {
                len[n] = 1 + len(facSum(n));
            }
        }
        return len[n];
    }

    private static int facSum(int n) {
        int facSum = 0;
        while (n > 0) {
            facSum += fac[n % 10];
            n /= 10;
        }
        return facSum;
    }

    public static void main(String[] args) {
        //System.out.println(P074.solve(1000000, 60));
        System.out.println(P074.solve2(1000000, 60));
    }
}
