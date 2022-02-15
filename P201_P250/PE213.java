package P201_P250;

import java.util.ArrayList;
import java.util.List;

public class PE213 {

    /*
     * Info: http://nl.wikipedia.org/wiki/Markovketen
     * P(30,30,50)=330.721154
     * BUILD SUCCESSFUL (total time: 6 minutes 31 seconds)
     */
    public static void main(String[] args) {
        new PE213().solve(30, 30, 50);
    }

    void solve(int C, int R, int S) {

        // genereer overgangsmatrix
        double[][] markov = new double[C * R][C * R];
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < C * R; j++) {
            list.clear();
            int row = j / C;
            int col = j % C;
            if (col - 1 >= 0) {
                list.add(col - 1 + row * C);
            }
            if (col + 1 < C) {
                list.add(col + 1 + row * C);
            }
            if (row - 1 >= 0) {
                list.add(col + (row - 1) * C);
            }
            if (row + 1 < R) {
                list.add(col + (row + 1) * C);
            }
            for (int i : list) {
                markov[j][i] = 1D / list.size();
            }
        }

        // genereer waarschijnlijkheidsverdeling voor elke vlo
        List<double[]> distr = new ArrayList<>();
        for (int f = 0; f < R * C; f++) {
            // beginverdeling
            double[] d = new double[C * R];
            d[f] = 1;
            // verdeling na S stappen
            for (int s = 0; s < S; s++) {
                d = multiply(d, markov);
            }
            System.out.println(f + ": " + toString(d));
            distr.add(d);
        }

        // empty[p] = kans dat posititie p leeg is na S stappen 
        double[] empty = new double[C * R];
        for (int i = 0; i < C * R; i++) {
            empty[i] = 1;
        }
        for (double[] d : distr) {
            for (int i = 0; i < C * R; i++) {
                empty[i] *= 1 - d[i];
            }
        }
        System.out.println("P(empty): " + toString(empty));

        // aantal verwachte lege posities = p(1)+..+p(R*C)
        double sum = 0;
        for (int i = 0; i < C * R; i++) {
            sum += empty[i];
        }
        System.out.format("P(%d,%d,%d)=%.6f%n", C, R, S, sum);
    }

    String toString(double[] m) {
        StringBuilder sb = new StringBuilder();
        for (double b : m) {
            sb.append(String.format("%f ", b));
        }
        return sb.toString();
    }

    double[] multiply(double[] t, double[][] markov) {
        double[] rv = new double[t.length];
        for (int j = 0; j < t.length; j++) {
            for (int i = 0; i < t.length; i++) {
                rv[j] += t[i] * markov[i][j];
            }
        }
        return rv;
    }

}
