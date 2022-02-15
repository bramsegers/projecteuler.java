package P551_P600;

import java.util.HashMap; 
import util.Primes;
import util.Util;

public class PE590 {

    long M1 = 1000000000;
    long M2 = 1562500;
    long[][] chooseM1;
    long[][] modpowM1;
    long[][] modpowM2;

    long solve(int i, int[] A, int[] B, int[] C) {
        if (i == A.length) {
            int a, b, mu, npf = 0;
            long pow, rv, m = 1, mtp = 1;
            for (int j = 0; j < i; j++) {
                a = A[j] + 1;
                b = C[j];
                m *= modpowM2[a][b];
                m %= M2;
                a = A[j];
                b = B[j] - C[j];
                m *= modpowM2[a][b];
                m %= M2;
                npf += b;
                mtp *= chooseM1[B[j]][C[j]];
                mtp %= M1;
            }
            mu = (npf & 1) == 0 ? 1 : -1;
            pow = modpowM1[2][(int) m];
            rv = mtp * mu * (pow - 1);
            return rv % M1;
        }
        long rv = 0;
        for (int j = 0; j <= B[i]; j++) {
            C[i] = j;
            rv += solve(i + 1, A, B, C);
            rv %= M1;
        }
        return (rv + M1) % M1;
    }

    void solve(int n) {

        HashMap<Integer, Integer> t = new HashMap<>();
        Primes pr = new Primes(n);
        for (int i = 0; i < pr.size(); i++) {
            int e = 0;
            long p = pr.next();
            long pe = p;
            while (pe <= n) {
                e++;
                pe *= p;
            }
            Integer v = t.get(e);
            t.put(e, v == null ? 1 : v + 1);
        }

        int k = 0, a = 0, b = 0;
        int[] A = new int[t.size()];
        int[] B = new int[t.size()];
        for (int i : t.keySet()) {
            A[k] = i;
            B[k] = t.get(i);
            a = Math.max(a, A[k]);
            b = Math.max(b, B[k]);
            k++;
        }

        chooseM1 = new long[b + 1][b + 1];
        for (int i = 0; i <= b; i++) {
            for (int j = 0; j <= i; j++) {
                chooseM1[i][j] = (j == 0 || j == i)
                        ? 1
                        : chooseM1[i - 1][j - 1]
                        + chooseM1[i - 1][j];
                chooseM1[i][j] %= M1;
            }
        }

        modpowM1 = new long[3][(int) M2];
        long mp = 1;
        for (int i = 0; i < M2; i++) {
            modpowM1[2][i] = mp;
            mp = (mp << 1) % M1;
        }

        modpowM2 = new long[a + 2][b + 2];
        for (int i = 0; i <= a + 1; i++) {
            for (int j = 0; j <= b + 1; j++) {
                modpowM2[i][j] = Util.modPow(i, j, M2);
            }
        }

        long HL = solve(0, A, B, B.clone());
        System.out.format("HL(%d)=%d %n", n, HL);

    }

    public static void main(String[] args) {
        new PE590().solve(50000); //https://oeis.org/A076078
    }

}
