package P051_P100;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/*
 * A number chain is created by continuously adding the square of the digits 
 * in a number to form a new number until it has been seen before.
 * For example:
 * 44 - 32 - 13 - 10 - 1 - 1
 * 85 - 89 - 145 - 42 - 20 - 4 - 16 - 37 - 58 - 89
 * 
 * Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. 
 * What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.
 * 
 * How many starting numbers below ten million will arrive at 89?
 */
public class P092 {
    
    public static int solve(int nMax) {
        int sum = 0;
        for (int n = 1; n <= nMax; n++) {
            int num = n;
            String out = "" + num;
            while (!(num == 1 || num == 89)) {
                int squareDigitsSum = 0;
                for (char ch : ("" + num).toCharArray()) {
                    squareDigitsSum += (ch - '0') * (ch - '0');
                }
                num = squareDigitsSum;
                out += " -> " + num;
            }
            sum += (num == 89) ? 1 : 0;
            if (n % 123456 == 0) {
                System.out.println(out);
            }
        }
        return sum;
    }
    
    public static int solve2(int nMax) {
        BitSet b1 = new BitSet();
        BitSet b89 = new BitSet();
        b1.set(1);
        b89.set(89);
        List<Integer> list = new ArrayList<>();
        for (int n = 1; n < nMax; n++) {
            list.clear();
            list.add(n);
            int i = n;
            while (!(b1.get(i) || b89.get(i))) {
                list.add(i);
                int j = i;
                i = 0;
                while (j > 0) {
                    i += (j % 10) * (j % 10);
                    j /= 10;
                }                
            }
            for (int j : list) {
                if (b1.get(i)) {
                    b1.set(j);
                } else {
                    b89.set(j);
                }
            }
        }
        return b89.cardinality();
    }
    
    public static void main(String[] args) {
        //System.out.println(P092.solve(10000000));
        System.out.println(P092.solve2(10000000));
    }
}
