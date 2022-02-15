package P051_P100;

/*
 Consider the fraction, n/d, where n and d are positive integers. 
 If n<d and gcd(n,d)=1, it is called a reduced proper fraction.

 If we list the set of reduced proper fractions for d<=8 in ascending order of size, we get:
 1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

 It can be seen that there are 3 fractions between 1/3 and 1/2.
 How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d <= 12,000?
 */
public class P073 {

    public static long solve(long p1, long q1, long p2, long q2, long maxNum) {
        int sum = 0;
        for (long q = maxNum; q > 1; q--) {
            long c1 = p1 * q / q1 + 1;
            long c2 = (p2 * q - 1) / q2;
            if (c1 <= c2) {
                System.out.format("(%d,%d)-(%d,%d)%n", c1, q, c2, q);
                for (long p = c1; c1 <= c2 && p <= c2; p++) {
                    sum += gcd(p, q) == 1 ? 1 : 0;
                }
            }
        }
        return sum;
    }

    private static long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(P073.solve(1, 3, 1, 2, 12000));
    }
}
