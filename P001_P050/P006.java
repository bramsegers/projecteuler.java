package P001_P050;

/*
 The sum of the squares of the first ten natural numbers is
 1^2 + 2^2 + ... + 10^2 = 385

 The square of the sum of the first ten natural numbers is
 (1 + 2 + ... + 10)^2 = 552 = 3025

 Hence the difference between the sum of the squares of the first ten 
 natural numbers and the square of the sum is 3025 - 385 = 2640.

 Find the difference between the sum of the squares of the first one 
 hundred natural numbers and the square of the sum.
 */
public class P006 {

    public static long solve(int nums) {
        int sumSquare = 0;
        for (int i = 1; i <= nums; i++) {
            sumSquare += i * i;
        }
        int squareSum = nums * nums * (nums + 1) * (nums + 1) / 4;
        return squareSum - sumSquare;
    }
    
    public static long f(long n) {
        return n*(n+1)*(3*n*n-n-2)/12;
    }

    public static void main(String[] args) {
        System.out.println(P006.solve(100));
        System.out.println(f(100));
    }

    
}
