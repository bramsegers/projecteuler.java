package P151_P200;

/*
 For a positive integer n, let f(n) be the sum of the squares of the digits (in base 10) of n, e.g.
 f(3) = 3^2 = 9,
 f(25) = 2^2 + 5^2 = 4 + 25 = 29,
 f(442) = 4^2 + 4^2 + 2^2 = 16 + 16 + 4 = 36

 Find the last nine digits of the sum of all n, 0 < n < 10^20, such that f(n) is a perfect square.
 */
public final class PE171 {

    public static void main(String[] args) {
        new PE171().solve(20, 10, 1000000000);
    }

    /* 
     * The key insight is to use dynamic programming to build up the answer one digit at a time.
     * 
     * Let Num(n, s) denote the set of numbers of length n (with leading zeros) whose squared digits sum to s.
     * For example, Num(2, 25) = {05, 34, 43, 50}.
     * Then for any particular n and s, we know that Num(n, s) = union of
     *   (prepend 0 to each of Num(n-1, s - 0*0)),
     *   (prepend 1 to each of Num(n-1, s - 1*1)),
     *   ...,
     *   (prepend 9 to each of Num(n-1, s - 9*9)).
     * 
     * However, keeping track of these sets of numbers explicitly is just as slow as iterating over
     * all the numbers by brute force. So instead, we only store the sums and counts of these sets,
     * and these two pieces of information are enough to determine the final answer.
     * (Furthermore, these can be reduced by the modulus.)
     *    
     * By Nayuki Minase 
     * http://nayuki.eigenstate.org/page/project-euler-solutions
     * https://github.com/nayuki/Project-Euler-solutions
     */
    void solve(int LENGTH, int BASE, int MODULUS) {
        // Maximum possible squared digit sum (for 99...99)
        int MAX_SQR_DIGIT_SUM = (BASE - 1) * (BASE - 1) * LENGTH;
        // sum[n][s] is the sum of all length-n numbers with a square digit sum of s, modulo MODULUS
        long[][] sum = new long[LENGTH + 1][MAX_SQR_DIGIT_SUM + 1];
        // count[n][s] is the count of all length-n numbers with a square digit sum of s, modulo MODULUS
        long[][] count = new long[LENGTH + 1][MAX_SQR_DIGIT_SUM + 1];
        count[0][0] = 1;
        for (int i = 1; i <= LENGTH; i++) {
            for (int j = 0; j < BASE; j++) {
                for (int k = 0; k + j * j <= MAX_SQR_DIGIT_SUM; k++) {
                    sum[i][k + j * j] = (sum[i][k + j * j] + sum[i - 1][k] + powMod(BASE, i - 1, MODULUS) * j % MODULUS * count[i - 1][k]) % MODULUS;
                    count[i][k + j * j] = (count[i][k + j * j] + count[i - 1][k]) % MODULUS;
                }
            }
        }
        long s = 0;
        for (int i = 1; i * i <= MAX_SQR_DIGIT_SUM; i++) {
            s = (s + sum[LENGTH][i * i]) % MODULUS;
        }
        System.out.println(String.format("%09d", s));
    }

    long powMod(int b, int n, int mod) {
        long rv = 1;
        for (int i = 0; i < n; i++) {
            rv *= b;
            rv %= mod;
        }
        return rv;
    }

}
