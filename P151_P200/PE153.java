package P151_P200;

import util.Util;

public class PE153 {

    /*
     P(100000000)=17971254122360635
     Elapsed:21425ms
     */
    public static void main(String[] args) {
        new PE153().solve(100000000);
    }

    void solve(int nMax) {
        long sum = 0;
        long start = System.currentTimeMillis();
        int sq = (int) Math.sqrt(nMax);
        GCD = new int[sq + 1][sq + 1];

        //rational integer divisors
        for (int n = 1; n <= nMax; n++) {
            sum += n * (nMax / n);
        }

        //Gaussian integer divisors 
        for (int a = 1; a <= sq; a++) {
            for (int b = 1; b <= sq; b++) {
                if (Util.gcd(a, b) == 1) {
                    long m = 0, D = 0, d = a * a + b * b;
                    while ((D += d) <= nMax) {
                        m += 2;
                        sum += m * a * (nMax / D);
                    }
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }

    int[][] GCD;

    int gcd(int a, int b) {
        if (GCD[a][b] == 0) {
            GCD[a][b] = b == 0 ? a : gcd(b, a % b);
            GCD[b][a] = GCD[a][b];
        }
        return GCD[a][b];
    }

}
