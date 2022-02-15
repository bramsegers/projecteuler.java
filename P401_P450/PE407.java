package P401_P450;

import java.util.TreeMap;
import util.Primes;

//https://oeis.org/A182665
//largest value of a < n such that a^2 ≡ a (mod n)
public class PE407 {

    public static void main(String[] args) {
        new PE407().brute(100);
        new PE407().solve(10000000);
    }

    void brute(int lim) {
        long sum = 0;
        for (long n = 1; n <= lim; n++) {
            for (long a = n - 1; a >= 0; a--) {
                if ((a * (a - 1)) % n == 0) {
                    System.out.println(n + " " + a);
                    sum += a;
                    break;
                }
            }
        }
        System.out.println(sum);
    }

    int N;
    long[] arr;
    TreeMap<Long, Integer> last, curr, conc;

    void solve(int lim) {
        N = lim;
        arr = new long[N + 1];
        Primes pr = new Primes(N);
        last = pr.factorize(1);
        for (int n = 2; n <= N; n++) {
            curr = pr.factorize(n);
            conc = new TreeMap<>(last);
            for (long p : curr.keySet()) {
                conc.put(p, curr.get(p));
            }
            last = curr;
            solve(n, 1, 2L);
        }

        long sum = 0;
        for (int i = 2; i <= N; i++) {
            sum += Math.max(1, arr[i]);
        }
        System.out.println(sum);
    }

    void solve(long n, long q, Long p) {
        if (p == null) {
            if (q > n) {
                arr[(int) q] = n;
            }
            return;
        }
        long q2 = 1;
        for (int i = 0; q * q2 <= N && i <= conc.get(p); i++) {
            solve(n, q * q2, conc.higherKey(p));
            q2 *= p;
        }
    }

}
