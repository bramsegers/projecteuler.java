package P251_P300;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PE267 {

    public static void main(String[] args) {
        new PE267().solve(1000, 1000000000);
    }

    void solve(int turns, int goal) {
        int winsNeeded = turns + 1;
        double fMax = 0;
        for (float i = 1; i < 1000; i++) {
            double f = (double) i / 1000;
            int wins = 0;
            for (wins = 0; wins <= turns; wins++) {
                //(1+2f)^w * (1-f)^(n-w)   
                double totalWins
                        = Math.pow(1 + 2 * f, wins)
                        * Math.pow(1 - f, turns - wins);
                if (totalWins >= goal) {
                    break;
                }
            }
            if (wins < winsNeeded) {
                winsNeeded = wins;
                fMax = f;
            }
        }
        System.out.println("Wins needed = " + winsNeeded);
        System.out.println("F max = " + fMax);
        double p = 0;
        for (int w = winsNeeded; w <= turns; w++) {
            p += p(w, turns);
        }
        System.out.format("P = %.12f%n",p);
    }

    double p(int wins, int turns) {
        wins = Math.max(wins, turns - wins);
        BigInteger b = BigInteger.ONE;
        for (int j = wins + 1; j <= turns; j++) {
            BigInteger c = new BigInteger(Integer.toString(j));
            b = b.multiply(c);
        }
        for (int j = 1; j <= turns - wins; j++) {
            BigInteger c = new BigInteger(Integer.toString(j));
            b = b.divide(c);
        }
        BigInteger pow = new BigInteger(Integer.toString(2)).pow(turns);
        BigDecimal bd = new BigDecimal(b).divide(new BigDecimal(pow));
        return bd.doubleValue();
    }

}
