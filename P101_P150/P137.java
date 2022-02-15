package P101_P150;

/*
 * Consider the infinite polynomial series AF(x) = xF1 + x^2F2 + x^3F3 + ..., 
 * where Fk is the kth term in the Fibonacci sequence: 1, 1, 2, 3, 5, 8, ..., 
 * that is, Fk = Fk1 + Fk2, F1 = 1 and F2 = 1.
 * 
 * For this problem we shall be interested in values of x for which AF(x) is a positive integer.
 * Surprisingly AF(1/2)	= (1/2)*1 + (1/2)^2*1 + (1/2)^3*2 + (1/2)^4*3 + (1/2)^5*5 + ...
 *                      = 1/2     + 1/4       + 2/8       + 3/16      + 5/32      + ...
 *                      = 2
 * 
 * The corresponding values of x for the first five natural numbers are shown below.
 * x            AF(x)
 * √2-1         1
 * 1/2          2
 * (√13-2)/3	3
 * (√89-5)/8	4
 * (√34-3)/5	5
 * 
 * We shall call AF(x) a golden nugget if x is rational, because they become increasingly 
 * rarer; for example, the 10th golden nugget is 74049690.
 * Find the 15th golden nugget.
 *
 * Info:http://www.mathblog.dk/project-euler-137-fibonacci-golden-nuggets/
 */
public class P137 {

    public static void main(String[] args) {
        System.out.println(P137.solve(15));
    }

    public static long solve(int nr) {
        long nugget = 0;
        for (int n = 1; n <= nr; n++) {
            nugget = fib(2 * n) * fib(2 * n + 1);
            System.out.println(n + ": " + nugget);
        }
        return nugget;
    }

    private static long fib(long k) {
        double sqrt5 = Math.sqrt(5);
        return (long) ((Math.pow((1 + sqrt5) / 2, k) - Math.pow((1 - sqrt5) / 2, k)) / sqrt5);
    }
}
