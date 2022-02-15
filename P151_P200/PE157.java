package P151_P200;

public class PE157 {

    /*
     * 1/a + 1/b = p/10^n
     *  => 1/(pa) + 1/(pb) = 1/10^n.  
     *  => Solve for p=1, then we get one solution for each factor of gcd(a,b).  
     *  => a must be of the form 10^n+2^r*5^s and less than or equal to 2*10^n.  
     *  => Try all these and see if b comes out integral.
     */
    public static void main(String[] args) {
        new PE157().solve(9);
    }

    void solve(int eMax) {
        long count = 0;
        for (int e = 1; e <= eMax; e++) {
            long n = (long) Math.pow(10, e);
            long R, S;
            for (long r = 0; (R = (long) Math.pow(2, r)) <= n; r++) {
                for (long s = 0; R * (S = (long) Math.pow(5, s)) <= n; s++) {
                    long a = n + R * S;
                    if ((a * n) % (a - n) == 0) {
                        long b = (a * n) / (a - n);
                        long gcd = gcd(a, b);
                        for (int i = 1; i <= (int) Math.sqrt(gcd); i++) {
                            if (gcd % i == 0) {
                                count += 2;
                                //long k = gcd / i;
                                //System.out.format("1/%d + 1/%d = %d/%d%n", a / i, b / i, i, n);
                                //System.out.format("1/%d + 1/%d = %d/%d%n", a / k, b / k, k, n);
                            }
                        }
                    }
                }
            }
        }
        System.out.format("P(%d)=%d%n", eMax, count);
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
