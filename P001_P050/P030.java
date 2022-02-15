package P001_P050;

/*
 Surprisingly there are only three numbers that can be written 
 as the sum of fourth powers of their digits:
 1634 = 1^4 + 6^4 + 3^4 + 4^4
 8208 = 8^4 + 2^4 + 0^4 + 8^4
 9474 = 9^4 + 4^4 + 7^4 + 4^4
 As 1 = 1^4 is not a sum it is not included.
 
 The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 Find the sum of all the numbers that can be written 
 as the sum of fifth powers of their digits.
 */
public class P030 {

    public static int solve(int pow) {
        int totalSum = 0;
        for (int i = 10; i < 1000000; i++) {
            String n = "" + i;
            int sum = 0;
            for (char ch : n.toCharArray()) {
                sum += Math.pow(ch - '0', pow);
            }
            if (i == sum) {
                System.out.println("->" + i);
                totalSum += sum;
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        System.out.println(P030.solve(5));
    }
}
