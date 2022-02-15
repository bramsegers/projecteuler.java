package P301_P350;

import java.math.BigInteger;
import util.Matrix;

public class PE323 {

    /*
    * Aantal mogelijkheden N(i,j) om van i gezette bits naar j gezette bits te gaan:
    * - i bits maken niet uit => 2^i
    * - houden we 32-i bits over
    * - hiervan moeten j-i bits "1" zijn
    * - en de overigen = 32-i-(j-i) = 32-j bits moeten "0" zijn
    * - dus N(i,j) = 2^i * (32-i)! / ( (j-i)!(32-j)! )
    * - De kans P(i,j) om van i naar j te gaan is dan N(i,j)/2^32
    *
    * => Markov keten genereren en matrix oplossen, als in PE227
    */
    public static void main(String[] args) {
        new PE323().solve(32);
    }

    void solve(int nMax) {
        
        // genereer markov keten
        double[][] markov = new double[nMax + 1][nMax + 1];
        for (int i = 0; i <= nMax; i++) {
            for (int j = i; j <= nMax; j++) {
                markov[i][j] = 1D * (1L << i) * fac(nMax - i, j - i, nMax - j) / (1L << nMax);
            }
        }
        System.out.println("Markov chain:\n"+toString(markov));

        // genereer matrix A
        double[][] A = new double[nMax + 1][nMax + 1];
        for (int i = 0; i <= nMax; i++) {
            for (int j = 0; j <= nMax; j++) {
                A[i][j] = (i == j && i != nMax) ? markov[i][j] - 1 : markov[i][j];
            }
        }
        System.out.println("Matrix A:\n"+toString(A));
        Matrix MA = new Matrix(A);
        
        // genereer matrix b
        double[][] b = new double[nMax + 1][1];
        for (int i = 0; i <= nMax; i++) {
            b[i][0] = (i == nMax) ? 0 : -1;
        }
        Matrix Mb = new Matrix(b);
        
        // los op
        Matrix Mx = MA.solve(Mb);
        System.out.print("Matrix x:");
        Mx.print(10,10);
        double p = Mx.get(0, 0);
        System.out.format("P(%d)=%.10f%n", nMax, p);

    }

    long fac(long a, long b, long c) {
        BigInteger i = fac(new BigInteger(String.valueOf(a)));
        BigInteger j = fac(new BigInteger(String.valueOf(b)));
        BigInteger k = fac(new BigInteger(String.valueOf(c)));
        return i.divide(j).divide(k).longValue();
    }

    BigInteger fac(BigInteger f) {
        return f.signum() == 0
                ? BigInteger.ONE
                : f.multiply(fac(f.subtract(BigInteger.ONE)));
    }

    String toString(double[][] m) {
        StringBuilder sb = new StringBuilder();
        for (double[] arr : m) {
            for (double b : arr) {
                sb.append(String.format("%.12f ", b));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
