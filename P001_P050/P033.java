package P001_P050;

/*
 The fraction 49/98 is a curious fraction, as an inexperienced mathematician 
 in attempting to simplify it may incorrectly believe that 49/98 = 4/8, 
 which is correct, is obtained by cancelling the 9s.

 We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 There are exactly four non-trivial examples of this type of fraction, less 
 than one in value, and containing two digits in the numerator and denominator.

 If the product of these four fractions is given in its lowest common terms, 
 find the value of the denominator.
 */
public class P033 {

    public static String solve() {
        int rvNum = 1;
        int rvDen = 1;
        for (int num = 10; num < 100; num++) {
            for (int den = num + 1; den < 100; den++) {
                String n = "" + num;
                String d = "" + den;
                for (int i = 1; i < 10; i++) {
                    if (n.contains("" + i) && d.contains("" + i) && !(n + d).contains("0")) {
                        int num2 = Integer.parseInt(n.replaceFirst("" + i, ""));
                        int den2 = Integer.parseInt(d.replaceFirst("" + i, ""));
                        if (num * den2 == num2 * den) {
                            System.out.println(num + "/" + den + " = " + num2 + "/" + den2);
                            rvNum *= num;
                            rvDen *= den;
                        }
                    }
                }
            }
        }
        int gcd = gcd(rvNum, rvDen);
        return rvNum + "/" + rvDen + " = " + (rvNum / gcd) + "/" + (rvDen / gcd);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(P033.solve());
    }
}
