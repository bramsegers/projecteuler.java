package P001_P050;

import util.Util;

/*
 We shall say that an n-digit number is pandigital if it makes use of all 
 the digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital 
 and is also prime.
 What is the largest n-digit pandigital prime that exists?
 */
public class P041 {

    static String panPrime;

    static String solve() {
        String pan = "987654321";
        int i = 0;
        while (panPrime == null) {
            permutation("", pan.substring(i++));
        }
        return panPrime;
    }

    static void permutation(String p, String s) {
        int n = s.length();
        if (panPrime == null) {
            if (n == 0) {
                panPrime = Util.isPrime(Integer.parseInt(p)) ? p : null;
            } else {
                for (int i = 0; i < n; i++) {
                    permutation(p + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n));
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P041.solve());
    }
}
