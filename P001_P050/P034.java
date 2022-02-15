package P001_P050;

/*
 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
public class P034 {

    public static int solve(int num) {
        int totalSum = 0;
        int[] fac = new int[10];
        fac[0] = 1;
        for (int i = 1; i < 10; i++) {
            fac[i] = i * fac[i - 1];
        }
        for (int i = 10; i <= num; i++) {
            String out = i + " = ";
            int sum = 0;
            for (char ch : (i + "").toCharArray()) {
                out += ch + "! + ";
                sum += fac[ch - '0'];
            }
            if (i == sum) {
                System.out.println(out + "\b\b = " + sum);
                totalSum += sum;
            }
        }
        return totalSum;
    }

    public static void main(String[] args) {
        System.out.println(P034.solve(10000000));
    }
}
