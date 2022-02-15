package P151_P200;

/*
 * Info: http://vilhena.wordpress.com/2010/05/31/problem-182/
 *
 * Nr of unconcealed messages is given by:
 * (1 + gcd(e-1,p-1)) * (1 + gcd(e-1,q-1))
 * Minimum = 9 unconcealed messages
 * => gcd(e-1,p-1)==2 && gcd(e-1,q-1)==2
 */
public class PE182 {

    public static void main(String[] args) {
        //new Test().test(19, 37);
        new PE182().solve(1009, 3643);
    }

    void solve(int p, int q) {
        long sum = 0;
        int phi = (p - 1) * (q - 1);
        for (int e = 2; e < phi; e++) {
            sum += gcd(e, phi) == 1 && gcd(e - 1, p - 1) == 2 && gcd(e - 1, q - 1) == 2 ? e : 0;
        }
        System.out.println(sum);
    }

    void test(int p, int q) {
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int[] count = new int[phi];
        for (int m = 0; m < n; m++) {
            int c = 1;
            for (int e = 0; e < phi; e++) {
                if (e > 1 && c == m && gcd(e, phi) == 1) {
                    count[e]++;
                }
                c *= m;
                c %= n;
            }
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int e = 2; e < phi; e++) {
            if (gcd(e, phi) == 1) {
                System.out.println(e + " " + count[e]);
                min = Math.min(min, count[e]);
            }
        }
        for (int e = 2; e < phi; e++) {
            if (count[e] == min) {
                sum += e;
            }
        }
        System.out.println(min + " " + sum);
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
