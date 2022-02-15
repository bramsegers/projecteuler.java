package P501_P550;

import java.util.BitSet;

public class PE521 {

    //44389811
    //BUILD SUCCESSFUL (total time: 443 minutes 50 seconds)
    public static void main(String[] args) {
        new PE521().test(10000);
        new PE521().solve(10000);
        new PE521().solve(1000000000000L);
    }

    long sum;
    long mod = 1000000000L;

    void test(int limit) {
        sum = 0;
        for (int n = 2; n <= limit; n++) {
            int d = 2;
            if ((n & 1) != 0) {
                d++;
                while (n % d != 0) {
                    d += 2;
                }
            }
            sum += d;
        }
        System.out.println(sum);
    }

    void solve(long limit) {
        sum=0;
        int sqrt = (int) (0.5D + Math.sqrt(limit));
        BitSet primes = getPrimesBS(sqrt);
        //print(primes, 0); 
        for (long i = sqrt; i < limit; i += sqrt) {
            BitSet primes2 = new BitSet(sqrt);
            primes2.set(0, sqrt);
            for (int j = primes.nextSetBit(0); j >= 0; j = primes.nextSetBit(j + 1)) {
                int d = (int) (((j - (i % j))) % j);
                for (int c = d; c < sqrt; c += j) {
                    if (primes2.get(c)) {
                        primes2.clear(c);
                        sum += j; // count multiples of p
                        sum %= mod;
                    }
                }
            }
            for (int j = primes2.nextSetBit(0); j >= 0; j = primes2.nextSetBit(j + 1)) {
                sum += i + j; // count p
                sum %= mod;
            }
            System.out.println(i / sqrt + "/" + sqrt);
            //print(primes2, i);
        }
        System.out.println(sum);
    }

    BitSet getPrimesBS(int n) {
        BitSet bs = new BitSet();
        bs.set(2, n + 1);
        for (int i = 2; i * i <= n; i++) {
            if (bs.get(i)) {
                for (int j = i; i * j <= n; j++) {
                    if (bs.get(i * j)) {
                        bs.clear(i * j);
                        sum += i;   // count multiples of p
                        sum %= mod;
                    }
                }
            }
        }
        for (int j = bs.nextSetBit(0); j >= 0; j = bs.nextSetBit(j + 1)) {
            sum += j; // count p
            sum %= mod;
        }
        return bs;
    }

    void print(BitSet primes, long offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = primes.nextSetBit(0); i >= 0; i = primes.nextSetBit(i + 1)) {
            sb.append(i + offset).append(",");
        }
        System.out.println(sb + "\b");
    }

}
