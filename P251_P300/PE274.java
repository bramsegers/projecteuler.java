package P251_P300;

import java.util.BitSet;
import util.CRT;
import util.Util;

public class PE274 {

    public static void main(String[] args) {
        new PE274().solve(10000000);
    }

    void solve(int max) {
        long sum = 1 + 5; //p = 3,7
        BitSet prm = Util.getPrimesBS(max);
        for (int p = prm.nextSetBit(11); p >= 0; p = prm.nextSetBit(p + 1)) {
            int p1 = p - (p / 10), p2 = p % 10;
            int gcd = Util.gcd(p1, p2);
            p1 /= gcd;
            p2 /= gcd;
            int[] eucl = CRT.euclidean(p, p2);
            int m = (int) ((((1L * eucl[1] * p1) % p) + p) % p);
            sum += m;
            //System.out.println(p + " " + m);
        }
        System.out.format("P(%d)=%d%n", max, sum);
    }

}
