package P051_P100;

import java.util.Arrays;

/*
 It can be seen that the number, 125874, and its double, 251748, 
 contain exactly the same digits, but in a different order.

 Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x 
 contain the same digits.
 */
public class P052 {

    public static int solve(int f) {
        int n = 0;
        while (!sameDigits(++n, f)) {
            if ((int) Math.log10(n * f) > (int) Math.log10(n)) {
                n = (int) Math.pow(10, 1 + (int) Math.log10(n));
            }
        }
        for (int i = 1; i <= f; i++) {
            System.out.println(i + " x " + n + " = " + (i * n));
        }
        return n;
    }

    public static boolean sameDigits(int n, int f) {
        char[] digits1 = String.valueOf(n).toCharArray();
        Arrays.sort(digits1);
        for (int i = 2; i <= f; i++) {
            char[] digits2 = String.valueOf(n * i).toCharArray();
            Arrays.sort(digits2);
            if (!Arrays.equals(digits1, digits2)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(P052.solve(6));
    }
}
