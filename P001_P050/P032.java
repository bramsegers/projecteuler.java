package P001_P050;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 We shall say that an n-digit number is pandigital if it makes use of all 
 the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 
 1 through 5 pandigital. 
 The product 7254 is unusual, as the identity, 39  186 = 7254, containing 
 multiplicand, multiplier, and product is 1 through 9 pandigital.

 Find the sum of all products whose multiplicand/multiplier/product identity 
 can be written as a 1 through 9 pandigital.

 HINT: Some products can be obtained in more than one way so be sure 
 to only include it once in your sum.
 */
public class P032 {

    static List<String> pandigits = new ArrayList<>();

    public static int solve() {
        Set<Integer> set = new HashSet<>();
        permutation("", "123456789");
        for (String p : pandigits) {
            int a = Integer.parseInt(p.substring(0, 4));
            int b = Integer.parseInt(p.substring(4, 5));
            int c = Integer.parseInt(p.substring(5));
            if (a == b * c) {
                System.out.println("(a,b,c)=(" + a + "," + b + "," + c + ")");
                set.add(a);
            }
            b = Integer.parseInt(p.substring(4, 6));
            c = Integer.parseInt(p.substring(6));
            if (a == b * c) {
                System.out.println("(a,b,c)=(" + a + "," + b + "," + c + ")");
                set.add(a);
            }
        }
        int sum=0;
        for(int i:set){
            sum+=i;
        }
        return sum;
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            pandigits.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P032.solve());
    }
}
