package P001_P050;

/*
 The number, 1406357289, is a 0 to 9 pandigital number because it is made up 
 of each of the digits 0 to 9 in some order, but it also has a rather 
 interesting sub-string divisibility property.

 Let d(1) be the 1st digit, d(2) be the 2nd digit, and so on. 
 In this way, we note the following:
 d(2)d(3)d(4) = 406 is divisible by 2
 d(3)d(4)d(5) = 063 is divisible by 3
 d(4)d(5)d(6) = 635 is divisible by 5
 d(5)d(6)d(7) = 357 is divisible by 7
 d(6)d(7)d(8) = 572 is divisible by 11
 d(7)d(8)d(9) = 728 is divisible by 13
 d(8)d(9)d(10)= 289 is divisible by 17
 Find the sum of all 0 to 9 pandigital numbers with this property.
 */

public class P043 {

    static long sum;

    static long solve() {
        permutation("", "0123456789");
        return sum;
    }

    static void permutation(String pre, String str) {
        int n = str.length();
        if (n == 0) {
            if (isDivisible(pre)) {
                sum+=Long.parseLong(pre);
                System.out.println(pre);
            }
        } else {
            for (int i = 0; i < n; i++) {
                permutation(pre + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }
    }

    static boolean isDivisible(String n) {
        int[] divisors = {2, 3, 5, 7, 11, 13, 17};
        for (int i = 0; i < divisors.length; i++) {
            if (Long.parseLong(n.substring(i + 1, i + 4)) % divisors[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(P043.solve());
    }
}
