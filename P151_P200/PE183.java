package P151_P200;

public class PE183 {

    /*
     Wolframalpha:
     f(m)  = (n/m)^m
     f'(m) = (n/m)^m * (log(n/m)-1) 
     => Dus maximaal bij n/m=e    
     */
    public static void main(String[] args) {
        new PE183().solve(10000);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int n = 5; n <= nMax; n++) {
            int m = (int) Math.round(1D * n / Math.E);
            sum += nonTerminating(m / gcd(n, m)) ? n : -n;
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    boolean nonTerminating(int n) {
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n > 1;
    }

}
