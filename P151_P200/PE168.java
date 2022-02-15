package P151_P200;

import java.math.BigInteger;

public class PE168 {

    public static void main(String[] args) {
        new PE168().solve(100, 5);
    }

    void solve(int nMax, int dMax) {
        long sum = 0;
        long mod = (long) Math.pow(10, dMax);
        BigInteger ONE = BigInteger.ONE;
        BigInteger TEN = BigInteger.TEN;
        BigInteger MOD = TEN.pow(dMax);
        for (int pow = 1; pow <= nMax; pow++) {
            for (BigInteger a = ONE; a.compareTo(TEN) < 0; a = a.add(ONE)) {
                for (BigInteger n = ONE; n.compareTo(TEN) < 0; n = n.add(ONE)) {
                    BigInteger denom = ((TEN.pow(pow)).subtract(n)).multiply(a);
                    BigInteger div = (TEN.multiply(n)).subtract(ONE);
                    if (denom.mod(div).equals(BigInteger.ZERO)) {
                        BigInteger num = denom.divide(div);
                        if (num.toString().length() == pow) {
                            BigInteger value = new BigInteger(num + "" + a);
                            if (value.toString().length() <= 100) {
                                sum = (sum + value.mod(MOD).longValue()) % mod;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

}
