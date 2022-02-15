package P251_P300;

/*    
    
    cos alpha = (b^2 + c^2 - a^2)/2bc
    a,b,c integral -> cos alpha rational -> alpha = 60, 90, 120
    
    generate all primitive triangles, calculate perimeter p, add floor((10^8)/p) to total.

    1 primitive equilateral, perimeter p=3
    generating all other primitive triangles: m>n>0, gcd(m,n)=1:
     - 60, type I: (m-n)%3!=0, p=2*m*m+2*n*n+5*m*n
     - 60, type II: (m-n)%3!=0, p=3*m*m+3*m*n
     - 120: (m-n)%3!=0, p=2*m*m+n*n+3*m*n
     - 90: (m-n)%2!=0, p=2*m*m+2*m*n         
 */
public class PE279 {

    public static void main(String[] args) {
        new PE279().solve((long) 1E8);
    }

    void solve(long max) {
        long sum = max / 3;
        long maxm = (long) Math.sqrt(max / 2);
        for (long m = 2; m <= maxm; m++) {
            for (long n = 1; n < m; n++) {
                if (util.Util.gcd(m, n) == 1) {
                    if ((m - n) % 3 > 0) {
                        sum += max / (2 * m * m + 2 * n * n + 5 * m * n);
                        sum += max / (3 * m * m + 3 * m * n);
                        sum += max / (2 * m * m + n * n + 3 * m * n);
                    }
                    if ((m - n) % 2 > 0) {
                        sum += max / (2 * m * m + 2 * m * n);
                    }
                }
            }
        }
        System.out.println(sum);
    }

}
