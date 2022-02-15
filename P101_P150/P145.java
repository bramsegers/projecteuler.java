package P101_P150;

/*
 * Some positive integers n have the property that the sum n + reverse(n) consists 
 * entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and 409 + 904 = 1313. 
 * We will call such numbers reversible; so 36, 63, 409, and 904 are reversible. 
 * Leading zeroes are not allowed in either n or reverse(n).
 * 
 * There are 120 reversible numbers below one-thousand.
 * How many reversible numbers are there below one-billion (10^9)?
 */
public class P145 {

    public static int solve(int num) {
        int sum = 0;
        //no 9 digit solutions exist
        //num = Math.min(num, 100000000);
        for (int n = 1; n < num; n++) {
            if (n % 10 != 0) {
                if (entirelyOddDigits(n + reverse(n))) {
                    //System.out.println(n);
                    sum++;
                }
            }
        }
        return sum;
    }

    private static int reverse(int n) {
        int i = 0;
        while (n > 0) {
            i *= 10;
            i += n % 10;
            n /= 10;
        }
        return i;
    }

    private static boolean entirelyOddDigits(int n) {
        while (n > 0) {
            if ((n % 10 & 1) == 0) {
                return false;
            }
            n /= 10;
        }
        return true;
    }

    // 608720
    // BUILD SUCCESSFUL (total time: 3 minutes 27 seconds)
    public static void main(String[] args) {
        System.out.println(P145.solve(1000000000));
    }
}
