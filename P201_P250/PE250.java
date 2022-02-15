package P201_P250;

import java.math.BigInteger;

public class PE250 {

    public static void main(String[] args) {
        new PE250().solve(250250, 250, 10000000000000000L);
    }

    void solve(int elm, int div, long mod) {
        // numSubsets[i] is {the number of subsets with sum equal to i mod 250} mod 10^16
        long[] numSubsets = new long[div];
        numSubsets[0] = 1;
        for (int n = 1; n <= elm; n++) {
            BigInteger b = BigInteger.valueOf(n);
            int temp = b.modPow(b, BigInteger.valueOf(div)).intValue();
            long[] newArray = numSubsets.clone();
            for (int j = 0; j < div; j++) {
                newArray[(j + temp) % div] = (numSubsets[j] + numSubsets[(j + temp) % div]) % mod;
            }
            numSubsets = newArray;
        }
        long n = ((numSubsets[0] - 1 + mod) % mod);
        System.out.println(n);
    }

}
