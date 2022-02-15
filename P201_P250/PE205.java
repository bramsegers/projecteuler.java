package P201_P250;

public class PE205 {

    public static void main(String[] args) {
        PE205 t = new PE205();
        t.solve(4, 9, 6, 6);
    }

    void solve(int n1, int e1, int n2, int e2) {
        int[] possA = getPoss(n1, e1);
        int[] possB = getPoss(n2, e2);
        int totA = getSubtotal(possA, possA.length);
        int totB = getSubtotal(possB, possB.length);
        double P = 0;
        for (int i = 0; i < possA.length; i++) {
            double Pa = 1D * possA[i] / totA;
            double Pb = 1D * getSubtotal(possB, i) / totB;
            P += Pa * Pb;
        }
        System.out.format("P(A wins)=%.7f%n", P);
    }

    int[] getPoss(int n, int e) {
        int iMax = (int) Math.pow(n, e);
        int[] poss = new int[n * e + 1];
        for (int i = 0; i < iMax; i++) {
            int N = i, tot = 0;
            for (int ei = 0; ei < e; ei++) {
                tot += (N % n) + 1;
                N /= n;
            }
            poss[tot]++;
        }
        return poss;
    }

    int getSubtotal(int[] poss, int iMax) {
        int tot = 0;
        for (int i = 0; i < iMax; i++) {
            tot += poss[i];
        }
        return tot;
    }

}
