package P001_P050;

/*
 A palindromic number reads the same both ways. The largest palindrome made 
 from the product of two 2-digit numbers is 9009 = 91 x 99.
 Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class P004 {

    public static void solve(int n) {
        int max = 0;
        int nMin = (int) Math.pow(10, n - 1);
        int nMax = (int) Math.pow(10, n);
        for (int n1 = nMin; n1 < nMax; n1++) {
            for (int n2 = nMin; n2 < nMax; n2++) {
                StringBuilder sb = new StringBuilder(String.valueOf(n1 * n2));
                if (sb.toString().equals(sb.reverse().toString())) {
                    int palindrome = n1 * n2;
                    if (max < palindrome) {
                        max = palindrome;
                        System.out.println(n1 + "*" + n2 + "=" + max);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        P004.solve(3);
    }
}
