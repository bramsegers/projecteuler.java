package P101_P150;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

/*
 * The number 512 is interesting because it is equal to the sum of its digits 
 * raised to some power: 5 + 1 + 2 = 8, and 8^3 = 512. Another example of a 
 * number with this property is 614656 = 28^4.
 * 
 * We shall define a(n) to be the nth term of this sequence and insist 
 * that a number must contain at least two digits to have a sum.
 * 
 * You are given that a(2) = 512 and a(10) = 614656. Find a(30).
 */
public class P119 {

    public static String solve(int index, int range) {
        Map<BigInteger, String> map = new TreeMap<>();
        for (int base = 2; base < range; base++) {
            for (int exp = 2; exp < range; exp++) {
                BigInteger b = new BigInteger(String.valueOf(base)).pow(exp);
                int sum = 0;
                for (char ch : b.toString().toCharArray()) {
                    sum += ch - '0';
                }
                if (sum == base) {
                    map.put(b, base + "^" + exp);
                }
            }
        }
        int i = 1;
        for (BigInteger b : map.keySet()) {
            System.out.format("%d) %s = %s%n", i, b, map.get(b));
            if (i++ == index) {
                return b.toString();
            }
        }
        return ":(";
    }

    public static void main(String[] args) {
        System.out.println(P119.solve(30, 100));
    }
}
