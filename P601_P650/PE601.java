package P601_P650;
 
import util.CRT;
import util.Primes;

public class PE601 {

    public static void main(String[] args) {
        new PE601().solve(31);
    }

    void solve(int I) {
        long sum = 1;
        for (int i = 2; i <= I; i++) {
            long N = 1L << (2 * i);
            sum += solve(i, N);
            sum -= solve(i + 1, N);
        }
        System.out.println(sum);
    }

    long solve(long s, long N) {
        Primes pr = new Primes(s);
        long[] mods = new long[(int) pr.size()];
        int i = 0;
        while (pr.next() > 0) {
            long p = pr.cur();
            long e = (long) (Math.log(s) / Math.log(p));
            mods[i++] = (long) Math.pow(p, e);
        }

        long M = 1, x = 0;
        for (i = 0; i < mods.length; i++) {
            M *= mods[i];
        }
        long[] multInv = new long[mods.length];
        for (i = 0; i < multInv.length; i++) {
            multInv[i] = CRT.euclidean(M / mods[i], mods[i])[0];
        }
        for (i = 0; i < mods.length; i++) {
            x += (M / mods[i]) * multInv[i];
        }
        x = CRT.leastPosEquiv(x, M);
        long c = (N - x - 1) / M;
        return x == 1 ? c : c + 1;
    }

}
