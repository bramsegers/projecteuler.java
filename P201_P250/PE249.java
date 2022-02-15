package P201_P250;

import util.Util;

public class PE249 {

    public static void main(String[] args) {
        new PE249().solve(5000, 10000000000000000L);
    }

    void solve(int nMax, long mod) {
        Util.initPrimes(nMax * nMax / 2);
        long[] subsets = new long[nMax * nMax / 2];
        subsets[0] = 1;
        int maxSum = 0;
        for (int i = 0; i < nMax; i++) {
            if (Util.isPrime[i]) {
                maxSum += i;
                for (int j = maxSum; j >= i; j--) {
                    subsets[j] += subsets[j - i];
                    subsets[j] %= mod;
                }
            }
        }
        long sum = 0;
        for (int p : Util.primes) {
            sum += subsets[p];
            sum %= mod;
        }
        System.out.println(sum);
    }
}
