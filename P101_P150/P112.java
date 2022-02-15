package P101_P150;

/*
 * Working from left-to-right if no digit is exceeded by the digit to its left it is 
 * called an increasing number, for example 134468. Similarly if no digit is exceeded 
 * by the digit to its right it is called a decreasing number, for example 66420.
 * 
 * We shall call a positive integer that is neither increasing nor decreasing 
 * a "bouncy" number, for example 155349. The least number for which the proportion 
 * of bouncy numbers first reaches 50% is 538. Surprisingly, bouncy numbers become 
 * more and more common and by the time we reach 21780 the proportion of bouncy numbers 
 * is equal to 90%.
 * 
 * Find the least number for which the proportion of bouncy numbers is exactly 99%.
 */
public class P112 {

    public static int solve(int perc) {
        int bouncy = 0;
        int n = 1;
        while (bouncy * 100 < n * perc) {
            bouncy += isBouncy(++n) ? 1 : 0;
        }
        return n;
    }

    private static boolean isBouncy(int n) {
        boolean asc = false, desc = false;
        int d = n % 10;
        n /= 10;
        while (n > 0) {
            int m = n % 10;
            asc|= d < m;
            desc|=d > m;
            if (asc && desc) {
                return true;
            }
            n /= 10;
            d = m;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(P112.solve(99));
    }
}
