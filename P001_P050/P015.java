package P001_P050;

import java.math.BigInteger;

/*
 Starting in the top left corner of a 2x2 grid, and only being able to move 
 to the right and down, there are exactly 6 routes to the bottom right corner.
 How many such routes are there through a 20x20 grid?
 */
public class P015 {

    public static String solve(int n, int m) {
        // (n+m)!/(n!m!)
        BigInteger i = new BigInteger(String.valueOf(n));
        BigInteger j = new BigInteger(String.valueOf(m));
        return fac(i.add(j)).divide(fac(i).multiply(fac(j))).toString();
    }

    private static BigInteger fac(BigInteger i) {
        return i.equals(BigInteger.ONE) ? i : i.multiply(fac(i.subtract(BigInteger.ONE)));
    }

    public static void main(String[] args) {
        System.out.println(P015.solve(20, 20));
    }
}
