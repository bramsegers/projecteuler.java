package P101_P150;

/*
 * Working from left-to-right if no digit is exceeded by the digit to its left, it is 
 * called an increasing number, for example: 134468. Similarly if no digit is exceeded 
 * by the digit to its right it is called a decreasing number, for example: 66420.
 * 
 * We shall call a positive integer that is neither increasing nor decreasing a 
 * "bouncy" number, for example: 155349.
 * 
 * As n increases, the proportion of bouncy numbers below n increases such that there are 
 * only 12951 numbers below one-million that are not bouncy and only 277032 non-bouncy 
 * numbers below 10^10.
 * 
 * How many numbers below a googol (10^100) are not bouncy?
 */
public class P113 {

    public static long solve(int n) {
        return choose(n + 10, 10) + choose(n + 9, 9) - 2 - (10 * n);
    }

    private static long choose(long n, long k) {
        k = Math.min(k, n - k);
        long rv = 1;
        for (int i = 1; i <= k; i++) {
            rv = rv * (n - k + i) / i;
        }
        return rv;
    }

    public static void main(String[] args) {
        System.out.println(P113.solve(100));
    }
}
