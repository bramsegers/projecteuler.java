package P101_P150;

/*
 * The palindromic number 595 is interesting because it can be written as the sum 
 * of consecutive squares: 6^2 + 7^2 + 8^2 + 9^2 + 10^2 + 11^2 + 12^2.
 * 
 * There are exactly eleven palindromes below one-thousand that can be written 
 * as consecutive square sums, and the sum of these palindromes is 4164.
 * 
 * Find the sum of all the numbers less than 10^8 that are both palindromic 
 * and can be written as the sum of consecutive squares.
 */
public class P125 {

    public static long solve(int num) {
        long sum = 0;
        for (int n = 1; n < num; n++) {
            sum += isPalindromeNum(n) && isConsecSquareNum(n) ? n : 0;
        }
        return sum;
    }

    private static boolean isPalindromeNum(int n) {
        int m = n, k = 0;
        while (m > 0) {
            k *= 10;
            k += m % 10;
            m /= 10;
        }
        return n == k;
    }

    private static boolean isConsecSquareNum(int n) {
        int sqrtEnd = (int) Math.sqrt(n);
        int squareSum = sqrtEnd * sqrtEnd;
        if (squareSum == n) {
            return false;
        }
        int sqrtStart = sqrtEnd;
        while (true) {
            while (squareSum < n && --sqrtStart > 0) {
                squareSum += sqrtStart * sqrtStart;
            }
            if (squareSum == n) {
                System.out.format("%d = %d^2 + ... + %d^2%n", n, sqrtStart, sqrtEnd);
                return true;
            } else if (sqrtStart == 0) {
                return false;
            }
            squareSum -= sqrtEnd * sqrtEnd;
            sqrtEnd--;
        }
    }

    public static void main(String[] args) {
        System.out.println(P125.solve(100000000));
    }
}
