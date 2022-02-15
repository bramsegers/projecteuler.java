package P251_P300;

import java.math.BigInteger;

public class PE288 {

    public static void main(String[] args) {
        //new Test().solve(3, 10000, 3, 20);
        new PE288().solve(61, 10000000, 61, 10);
    }

    /*
     * nr factors f of n! in base f
     *
     *  n         p^(n+1) - 1 
     *  Σ  p^i =  -----------
     * i=0            p-1
     *
     */
    void solve(int p, int q, int b, int e) {

        //init T
        int[] T = new int[q + 1];
        long s = 290797;
        for (int i = 0; i <= q; i++) {
            T[i] = (int) s % p;
            s = (s * s) % 50515093;
        }

        // init big integers
        BigInteger SUM = BigInteger.ZERO;
        BigInteger ONE = BigInteger.ONE;
        BigInteger P = BigInteger.valueOf(p);
        BigInteger PminusONE = P.subtract(ONE);
        BigInteger MOD = BigInteger.valueOf(b).pow(e);
        BigInteger MOD2 = MOD.multiply(PminusONE);
        BigInteger MODPOW = BigInteger.ONE;

        // iterate over 'digits' of n! in base p
        for (int i = 1; i <= q; i++) {
            MODPOW = MODPOW.multiply(P).mod(MOD2);
            BigInteger S = MODPOW.subtract(ONE);
            S = S.divide(PminusONE);
            S = S.multiply(BigInteger.valueOf(T[i]));
            SUM = SUM.add(S).mod(MOD);
        }
        System.out.format("NF(%d,%d) mod %d^%d = %s%n", p, q, b, e, SUM);
    }

}
