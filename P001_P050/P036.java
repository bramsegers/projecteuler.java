package P001_P050;

/*
 The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
 Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 (Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class P036 {

    public static int solve(int num) {
        int sum = 0;
        for (int n = 1; n < num; n++) {
            String dec = String.valueOf(n);
            String bin = Integer.toBinaryString(n);
            String revDec = new StringBuffer(dec).reverse().toString();
            String revBin = new StringBuffer(bin).reverse().toString();
            if (dec.equals(revDec) && bin.equals(revBin)) {
                System.out.println(n + " " + bin);
                sum += n;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P036.solve(1000000));
    }
}
