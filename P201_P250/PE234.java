package P201_P250;

import util.Util;

public class PE234 {

    public static void main(String[] args) {
        //new PE234().test(1000);
        new PE234().solve(999966663333L);
    }

    void solve(long nMax) {
        long sum = 0;
        Util.initPrimes((int) Math.sqrt(nMax));
        long p1, p2 = 2;
        long ps1, ps2 = 4;
        long j1, j2, s1, s2;
        for (int i = 1; i < Util.primes.size(); i++) {
            p1 = p2;
            p2 = Util.primes.get(i);
            ps1 = ps2;
            ps2 = p2 * p2;
            j1 = ps2 / p1;
            j2 = p2 - ((ps2 - ps1) / p2);
            s1 = (j1 * (j1 + 1) - p1 * (p1 + 1)) / 2 * p1;
            s2 = ((p2 - 1) * p2 - (j2 - 1) * j2) / 2 * p2;
            sum += s1 + s2 - 2 * p1 * p2;
        }
        p1 = Util.nextPrime(p2);
        for (long n = ps2; n <= nMax; n++) {
            if (ps2 != n && (n % p1 == 0 ^ n % p2 == 0)) {
                sum += n;
            }
        }
        System.out.format("P(%d)=%d%n", nMax, sum);
    }

    void test(long nMax) {
        long sum = 0;
        Util.initPrimes((int) Math.sqrt(nMax));
        Util.addNextPrime();
        for (long n = 4; n <= nMax; n++) {
            int sq = (int) Math.sqrt(n);
            int i = 0;
            long p2;
            while ((p2 = Util.primes.get(i)) <= sq) {
                i++;
            }
            long p1 = Util.primes.get(i - 1);
            p2 = p1 * p1 == n ? p1 : p2;
            if (n % p1 == 0 ^ n % p2 == 0) {
                sum += n;
            }
        }
        System.out.format("P(%d)=%d%n", nMax, sum);
    }

}
