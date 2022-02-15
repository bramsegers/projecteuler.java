package P251_P300;

import java.math.BigInteger;

public class PE284 {

    public static void main(String[] args) {
        //new Test().test(14, 10000);
        new PE284().solve(14, 10000);
    }

    //P(14,10000)=5a411d7b - (total time: 2 seconds)
    void solve(int base, int len) {
        long sum = solve(new int[len], base, 0, 0);
        System.out.format("P(%d,%d)=%s%n", base, len, Long.toString(sum, base));
    }

    long solve(int[] arr, int base, int pos, int tsum) {
        long rv = 0;
        for (int d = 0; d < base; d++) {
            if (pos == 0) {
                if ((d * d) % base == d) {
                    int[] a = arr.clone();
                    a[0] = d;
                    a[1] = (d * d) / base;
                    rv += tsum + d;
                    rv += solve(a, base, 1, tsum + d);
                }
            } else {
                int c = arr[0] * d * 2 + arr[pos];
                if (c % base == d) {
                    int[] a = arr.clone();
                    for (int i = 0; i < pos && pos + i < arr.length; i++) {
                        a[pos + i] += 2 * d * arr[i];
                        int carry = a[pos + i] / base;
                        int j = 0;
                        while (carry > 0) {
                            a[pos + i + j] %= base;
                            if (pos + i + j + 1 < arr.length) {
                                a[pos + i + j + 1] += carry;
                                carry = a[pos + i + j + 1] / base;
                                j++;
                            } else {
                                carry = 0;
                            }
                        }
                    }
                    if (2 * pos < arr.length) {
                        a[2 * pos] += d * d;
                        int carry = a[2 * pos] / base;
                        int j = 0;
                        while (carry > 0) {
                            a[2 * pos + j] %= base;
                            if (2 * pos + j + 1 < arr.length) {
                                a[2 * pos + j + 1] += carry;
                                carry = a[2 * pos + j + 1] / base;
                                j++;
                            } else {
                                carry = 0;
                            }
                        }
                    }
                    rv += d == 0 ? 0 : tsum + d;
                    if (pos + 1 < arr.length) {
                        rv += solve(a, base, pos + 1, tsum + d);
                    }
                }
            }
        }
        return rv;
    }

    //P(14,10000)=5a411d7b - (total time: 4 minutes 39 seconds)
    void test(int base, int len) {
        long sum = test(BigInteger.ZERO, BigInteger.ONE, base, len, 0, 0);
        System.out.format("P(%d,%d)=%s%n", base, len, Long.toString(sum, base));
    }

    long test(BigInteger n, BigInteger mod, int base, int len, int last, int sum) {
        long rv = 0;
        if ((n.multiply(n)).mod(mod).equals(n)) {
            rv += last == 0 ? 0 : sum;
            for (int d = 0; d < base && len > 0; d++) {
                BigInteger n2 = BigInteger.valueOf(d).multiply(mod).add(n);
                BigInteger m2 = mod.multiply(BigInteger.valueOf(base));
                rv += test(n2, m2, base, len - 1, d, sum + d);
            }
        }
        return rv;
    }

}
