package P051_P100;

import java.math.BigInteger;

/*
 If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
 Not all numbers produce palindromes so quickly. For example,
 349 +  943 = 1292
 1292 + 2921 = 4213
 4213 + 3124 = 7337
 That is, 349 took three iterations to arrive at a palindrome.

 Although no one has proved it yet, it is thought that some numbers, like 196, 
 never produce a palindrome. A number that never forms a palindrome through the 
 reverse and add process is called a Lychrel number. Due to the theoretical 
 nature of these numbers, and for the purpose of this problem, we shall assume 
 that a number is Lychrel until proven otherwise. In addition you are given that
 for every number below ten-thousand, it will either 
 (i)  become a palindrome in less than fifty iterations, or, 
 (ii) no one has managed so far to map it to a palindrome. 
 In fact, 10677 is the first number to be shown to require over fifty iterations
 before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).

 How many Lychrel numbers are there below ten-thousand?
 */
public class P055 {

    public static int solve(int num) {
        int sum = 0;
        for (int n = 1; n < num; n++) {
            sum += isLychrel(n) ? 1 : 0;
        }
        return sum;
    }

    private static boolean isLychrel(int n) {
        String nStr = "" + n;
        for (int i = 0; i < 50; i++) {
            nStr = "" + new BigInteger(nStr).add(new BigInteger(new StringBuffer(nStr).reverse().toString()));
            if (new StringBuffer(nStr).reverse().toString().equals(nStr)) {
                System.out.println(n + ": Iterations: " + (i + 1) + ", Result: " + nStr);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(P055.solve(10000));
    }
}
