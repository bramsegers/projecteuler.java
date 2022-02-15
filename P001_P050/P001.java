package P001_P050;

/*
 If we list all the natural numbers below 10 that are multiples of 3 or 5, 
 we get 3, 5, 6 and 9. The sum of these multiples is 23.
 Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class P001 {

    public static int solve(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum += (i % 3 == 0 || i % 5 == 0) ? i : 0;
        }
        return sum;
    }

    public static int solve2(int n, int d) {
        return ((n - 1) / d) * ((n - 1) / d + 1) / 2 * d;
    }

    public static void main(String[] args) {
        System.out.println(P001.solve(1000));
        System.out.println(P001.solve2(1000, 3) + P001.solve2(1000, 5) - P001.solve2(1000, 15));
    }
}
