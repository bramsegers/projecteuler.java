package P151_P200;

/*
 * Info: https://oeis.org/A046079
 * Let tau(n) = number of divisors of n (eg. tau(4)=3)
 * For odd n,  a(n) = (tau(n^2) - 1) / 2
 * For even n, a(n) = (tau((n / 2)^2) - 1) / 2
 */
public class PE176 {

    public static void main(String[] args) {
        new PE176().solve();
    }

    void solve() {
        // a(n) = (tau(x)-1)/2= 47547
        // tau(x) = 95095
        // tau(x) = 5*7*11*13*19
        // smallest x = 2^18 * 3^12 * 5^10 * 7^6 * 11^4
        // x is even
        // (n/2)^2 = 2^18 * 3^12 * 5^10 * 7^6 * 11^4
        // n/2 = 2^9 * 3^6 * 5^5 * 7^3 * 11^2
        // n = 2^10 * 3^6 * 5^5 * 7^3 * 11^2
        long n = pow(2, 10) * pow(3, 6) * pow(5, 5) * pow(7, 3) * pow(11, 2);
        System.out.format("P(47547)=%d%n", n);
    }

    long pow(int b, int e) {
        return (long) Math.pow(b, e);
    }

}
