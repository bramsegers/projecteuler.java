package P251_P300;

import java.util.List;
import util.Util;

public class PE268 {

    public static void main(String[] args) {
        //new PE268().test();
        new PE268().solve();
    }

    long max = (long) 1E16;
    List<Integer> primes = Util.getPrimes(100);
    int len = primes.size();

    void test() {
        int sum = 0;
        for (int n = 2; n <= max; n++) {
            int count = 0;
            for (int p : primes) {
                if (n % p == 0 && ++count == 4) {
                    break;
                }
            }
            sum += (count == 4) ? 1 : 0;
        }
        System.out.println(sum);
    }

    void solve() {
        long sum = 0;
        sum += count();
        for (int i = 0; i < len; i++) {
            sum += count(i);
            for (int j = i + 1; j < len; j++) {
                sum += count(i, j);
                for (int k = j + 1; k < len; k++) {
                    sum += count(i, j, k);
                }
            }
        }
        System.out.println(max - sum);
    }

    long count(int... pi) {
        long P = 1;
        for (int i : pi) {
            P *= primes.get(i);
        }
        long m = max / P;
        long sum = m;
        for (int i = 0; i < len; i++) {
            sum += inclExcl(-1, m, i, pi);
        }
        return sum;
    }

    long inclExcl(long sign, long m, int i, int[] pi) {
        if (primes.get(i) > m) {
            return 0;
        }
        for (int pii : pi) {
            if (i == pii) {
                return 0;
            }
        }
        m /= primes.get(i);
        long rv = sign * m;
        for (int j = i + 1; j < len; j++) {
            rv += inclExcl(-sign, m, j, pi);
        }
        return rv;
    }

}
