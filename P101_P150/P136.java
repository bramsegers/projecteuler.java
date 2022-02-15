package P101_P150;

/*
 * The positive integers, x, y, and z, are consecutive terms of an arithmetic 
 * progression. Given that n is a positive integer, the equation 
 * x^2 - y^2 - z^2 = n, has exactly one solution when n = 20:
 * 13^2 - 10^2 - 7^2 = 20
 * In fact there are twenty-five values of n below one hundred for which the 
 * equation has a unique solution.
 * 
 * How many values of n less than fifty million have exactly one solution?
 */
public class P136 {

    public static int solve(int nMax, int s) {
        // (x+a)^2 - x^2 - (x-a)^2 = n
        // x^2 + 2ax + a^2 - x^2 - x^2 + 2ax - a^2 = n 
        // 4ax - x^2 = n  
        // x(4a-x) = n  (x=9, a=3, n=27)  (x=27, a=7, n=27) 
        // constr1: 4a-x > 0
        // constr2: a < x
        // constr3: x(4a-x) < nMax
        int[] count = new int[nMax];
        int n;
        for (int x = 2; x < nMax; x++) {
            for (int a = x / 4 + 1; a < x && (n = x * (4 * a - x)) < nMax; a++) {
                //System.out.println("(x,a,n)=" + x + "," + a + "," + n);
                count[n]++;
            }
        }
        int sum = 0;
        for (n = 2; n < nMax; n++) {
            sum += count[n] == s ? 1 : 0;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P136.solve(50000000, 1));
    }
}
