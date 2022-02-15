package P101_P150;

/*
 * A positive integer, n, is divided by d and the quotient and remainder are q and r 
 * respectively. In addition d, q, and r are consecutive positive integer terms in a 
 * geometric sequence, but not necessarily in that order.
 * 
 * For example, 58 divided by 6 has quotient 9 and remainder 4. It can also be seen 
 * that 4, 6, 9 are consecutive terms in a geometric sequence (common ratio 3/2).
 * We will call such numbers, n, progressive.
 * 
 * Some progressive numbers, such as 9 and 10404 = 102^2, happen to also be perfect 
 * squares. The sum of all progressive perfect squares below one hundred thousand 
 * is 124657.
 * 
 * Find the sum of all progressive perfect squares below one trillion (10^12).
 *
 * Info: http://www.mathblog.dk/project-euler-141investigating-progressive-numbers-n-which-are-also-square/
 */
public class P141 {

    public static void main(String[] args) {
        System.out.println(P141.solve(1000000000000L));
    }

    public static long solve(long limit) {
        long result = 0;
        for (long a = 2; a < 10000; a++) {
            for (long b = 1; b < a; b++) {
                if (a * a * a * b + b * b >= limit) {
                    break;
                }
                if (gcd(a, b) > 1) {
                    continue;
                }
                for (long c = 1;; c++) {
                    long n = a * a * a * b * c * c + c * b * b;
                    if (n >= limit) {
                        break;
                    }
                    if (isSquare(n)) {
                        result += n;
                    }

                }
            }
        }
        return result;
    }

    private static boolean isSquare(long n) {
        long root = (long) Math.sqrt(n);
        return (root * root == n);
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
