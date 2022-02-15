package P501_P550;

import util.Primes;

public class PE549 {

    public static void main(String[] args) {
        new PE549().solve((int) 1e8);
    }

    void solve(int m) {

        Primes pr = new Primes(m);
        long[] f = new long[m + 1];
        long p = 0;
        while ((p = pr.next(p)) > 0) {
            long pe = p;
            int e = 1;
            while (pe <= m) {
                long fact = fact(p, e);
                for (int i = (int) pe; i <= m; i += pe) {
                    f[i] = Math.max(f[i], fact);
                }
                pe *= p;
                e++;
            }
        }

        long sum = 0;
        for (int i = 0; i <= m; i++) {
            sum += f[i];
        }
        System.out.println(sum);

    }

    long fact(long p, int e) {
        int c = 0;
        long rv = 0;
        while (c < e) {
            rv += p;
            long t = rv;
            while (t % p == 0) {
                t /= p;
                c++;
            }
        }
        return rv;
    }

}
