package P201_P250;

import java.util.Random;
import util.Matrix;

public class PE227 {

    /*
     * Info: http://math.stackexchange.com/questions/237046/using-markov-chains-to-solve-a-project-euler-problem
     */
    public static void main(String[] args) {
        //new PE227().simulate(100, 1000);
        new PE227().solve(100);
    }

    void solve(int nMax) {
        //dist=x
        // x=2...48
        // 1 P(-2) = 1/36
        // 2 P(-1) = 4/6*1/6*2 = 8/36
        // 3 P( 0) = 1/36 + 16/36 + 1/36 = 0.5
        // 2 P( 1) = 4/6*1/6*2 = 8/36
        // 1 P( 2) = 1/36
        // x=0
        //  P( 0) = 1
        // x=1
        //  0 P(-2) = 0
        //  2 P(-1) = 4/6*1/6*2 = 8/36
        //  4 P( 0) = 0.5+1/36
        //  2 P( 1) = 8/36
        //  1 P( 2) = 1/36
        // x=49
        //  1 P(-2) = 1/36
        //  2 P(-1) = 4/6*1/6*2 = 8/36
        //  4 P( 0) = 0.5+1/36
        //  2 P( 1) = 4/6*1/6*2 = 8/36
        //  0 P( 2) = 0
        // x=50
        //  2 P(-2) = 2/36
        //  4 P(-1) = 2*8/36=16/36
        //  3 P( 0) = 1/36 + 16/36 + 1/36 = 0.5
        //  0 P( 1) = 0
        //  0 P( 2) = 0
        int dMax = nMax / 2;
        double[][] markov = new double[dMax + 1][dMax + 1];
        markov[0][0] = 1;
        for (int i = 1; i <= dMax; i++) {
            if (i == 1) {
                markov[i][i - 1] = 8D / 36;
                markov[i][i] = 19D / 36;
                markov[i][i + 1] = 8D / 36;
                markov[i][i + 2] = 1D / 36;
            } else if (i == dMax - 1) {
                markov[i][i - 2] = 1D / 36;
                markov[i][i - 1] = 8D / 36;
                markov[i][i] = 19D / 36;
                markov[i][i + 1] = 8D / 36;
            } else if (i == dMax) {
                markov[i][i - 2] = 2D / 36;
                markov[i][i - 1] = 16D / 36;
                markov[i][i] = 18D / 36;
            } else {
                markov[i][i - 2] = 1D / 36;
                markov[i][i - 1] = 8D / 36;
                markov[i][i] = 18D / 36;
                markov[i][i + 1] = 8D / 36;
                markov[i][i + 2] = 1D / 36;
            }
        }
        double[][] P = new double[dMax + 1][dMax + 1];
        for (int i = 0; i <= dMax; i++) {
            for (int j = 0; j <= dMax; j++) {
                P[i][j] = (i == j && i != 0) ? markov[i][j] - 1 : markov[i][j];
            }
        }

        double[][] Q = new double[dMax + 1][1];
        for (int i = 0; i <= dMax; i++) {
            Q[i][0] = (i == 0) ? 0 : -1;
        }

        Matrix A = new Matrix(P);
        Matrix b = new Matrix(Q);
        Matrix x = A.solve(b);
        double p = x.get(dMax, 0);
        System.out.format("P(%d)=%f%n", nMax, p);
    }

    String toString(double[][] m) {
        StringBuilder sb = new StringBuilder();
        for (double[] arr : m) {
            for (double b : arr) {
                sb.append(String.format("%f ", b));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    void simulate(int n, int rep) {
        Random r = new Random();
        long count = 0;
        for (int R = 0; R < rep; R++) {
            int a = 0, b = n / 2;
            while (a != b) {
                int d1 = 1 + r.nextInt(6);
                int d2 = 1 + r.nextInt(6);
                if (d1 == 1) {
                    a += n - 1;
                } else if (d1 == 6) {
                    a += 1;
                }
                if (d2 == 1) {
                    b += n - 1;
                } else if (d2 == 6) {
                    b += 1;
                }
                a %= n;
                b %= n;
                count++;
            }
        }
        System.out.println(1D * count / rep);
    }

}
