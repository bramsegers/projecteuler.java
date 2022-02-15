package P001_P050;

import java.util.ArrayList;
import java.util.List;

/*
 A unit fraction contains 1 in the numerator. The decimal representation
 of the unit fractions with denominators 2 to 10 are given:
 1/2	= 	0.5
 1/3	= 	0.(3)
 1/4	= 	0.25
 1/5	= 	0.2
 1/6	= 	0.1(6)
 1/7	= 	0.(142857)
 1/8	= 	0.125
 1/9	= 	0.(1)
 1/10	= 	0.1
 Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can 
 be seen that 1/7 has a 6-digit recurring cycle. Find the value of d < 1000 
 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class P026 {

    public static int solve(int num) {
        int maxLength = 0;
        for (int n = 2; n < num; n++) {
            String out = n + " = 0,";
            List<Integer> remain1 = new ArrayList<>();
            List<Integer> remain2 = new ArrayList<>();
            int rem = 1;
            while (true) {
                remain1.add(rem);
                int d = (10 * rem) / n;
                out += d;
                rem = (10 * rem) - (d * n);
                if (rem == 0) {
                    break;
                } else if (remain1.contains(rem)) {
                    out += "-";
                    if (remain2.contains(rem)) {
                        break;
                    }
                    remain2.add(rem);
                }
            }
            if (!out.contains("-")) {
                System.out.println("(0) " + out);
            } else {
                String[] s = out.split("-");
                String out2 = "";
                for (int i = 1; i < s.length; i++) {
                    out2 += s[i];
                }
                System.out.println("(" + out2.length() + ") " + s[0].replaceAll(out2, "(" + out2 + ")"));
                maxLength = Math.max(maxLength, out2.length());
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(P026.solve(1000));
    }
}
