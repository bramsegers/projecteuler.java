package P351_P400;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import util.CRT;
import util.Util;

public class PE365 {

    /*
     http://en.wikipedia.org/wiki/Lucas%27_theorem
     http://nl.wikipedia.org/wiki/Chinese_reststelling  
     */
    public static void main(String[] args) {
        new PE365().solve();
    }

    long n = (long) 1E18;
    long k = (long) 1E9;
    int p1 = 1000;
    int p2 = 5000;

    void solve() {

        long sum = 0;
        BitSet bs = Util.getPrimesBS(p2);
        List<Long> mods = new ArrayList<>();
        List<Integer> primes = new ArrayList<>();
        for (int p = bs.nextSetBit(p1); p >= 0; p = bs.nextSetBit(p + 1)) {
            mods.add(Util.modCh(n, k, p));
            primes.add(p);
        }
        
        for(int i=primes.size()-100;i<primes.size();i++)
            System.out.println(primes.get(i)+" "+mods.get(i));
                    
        for (int a = 0; a < mods.size(); a++) {
            for (int b = a + 1; b < mods.size(); b++) {
                for (int c = b + 1; c < mods.size(); c++) {
                    long[] mod = new long[]{mods.get(a), mods.get(b), mods.get(c)};
                    long[] prime = new long[]{primes.get(a), primes.get(b), primes.get(c)};
                    sum += CRT.solve(mod, prime);
                }
            }
        }

        System.out.format(
                "∑M(%.0E,%.0E,p*q*r) for %d<p<q<r<%d and p,q,r prime = %d%n",
                (double) n,
                (double) k,
                p1,
                p2,
                sum);
    }

}
