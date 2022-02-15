package P001_P050;

/*
 A Pythagorean triplet is a set of three natural numbers, a  b  c, for which
 a^2 + b^2 = c^2. For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 Find the product abc.
 */
public class P009 {

    public static String solve(int n) {
        for (int a = 1; a <= n; a++) {
            for (int b = a; b <= n && n - a - b > b; b++) {
                int c = n - a - b;
                if ((a * a) + (b * b) == c * c) {
                    return String.format(
                            "(a,b,c)=(%d,%d,%d)%na+b+c=%d%na*b*c=%d%n",
                            a, b, c, a + b + c, a * b * c);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(P009.solve(1000));
    }
}
