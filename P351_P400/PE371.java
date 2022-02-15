package P351_P400;

import java.util.Arrays;

public class PE371 {

    public static void main(String[] args) {
        new PE371().solve(1000);
    }

    void solve(int n) {

        // prob1[x] =  prob of seeing x distinct non-matching non-zero numbers after r rounds, not seen 500
        // prob1b[x] = prob of seeing x distinct non-matching non-zero numbers including 500 after r rounds 
        // prob2[x] =  same, but for next round, i.e. after r+1 rounds 
        double[] prob1 = new double[n];
        double[] prob1b = new double[n];
        double[] prob2 = new double[n];
        double[] prob2b = new double[n];
        double[] tmp;

        prob1[0] = 1;
        double e = 0;
        double last = -1;
        for (int r = 1; !(r > 2 && e == last); r++) {
            last = e;
            for (int i = 0; i + i <= n; i++) {
                // have seen i distinct non-matching numbers (not seen 500)
                e += prob1[i] * r * i / n; // win
                prob2[i] += prob1[i] * (i + 1) / n; // see 000 or repeat
                prob2b[i + 1] += prob1[i] * 1 / n; // see 500
                prob2[i + 1] += prob1[i] * (n - i - i - 2) / n; // see new number

                // have seen i distinct non-matching numbers (have seen 500)
                e += prob1b[i] * r * i / n; // win
                prob2b[i] += prob1b[i] * i / n; // see 000 or repeat excl 500
                prob2b[i + 1] += prob1b[i] * (n - i - i) / n; // see new number
            }
            // swap arrays for next round
            tmp = prob1;
            prob1 = prob2;
            prob2 = tmp;
            Arrays.fill(prob2, 0);
            tmp = prob1b;
            prob1b = prob2b;
            prob2b = tmp;
            Arrays.fill(prob2b, 0);
            System.out.println(e);
        }
    }

}
