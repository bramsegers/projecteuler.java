package P151_P200;

public class PE181 {

    public static void main(String[] args) {
        new PE181().solve(60,40);
    }

    long count[][][][];

    void solve(int b, int w) {
        count = new long[b + 1][w + 1][b + w][b + 1];
        long sum = 0;
        for (int m = 1; m <= b + w; m++) {
            sum += count(b, w, m, b);
        }
        System.out.format("P(%d,%d)=%d%n", b, w, sum);
    }

    long count(int B, int W, int P, int maxB) {
        if (B + W == P && B <= maxB) {
            return 1;
        }
        if (count[B][W][P][maxB] == 0) {
            int b1 = Math.max(0, P - W);
            int b2 = Math.min(maxB, Math.min(P, B));
            int p2 = Math.min(B + W - P, P);
            for (int b = b1; b <= b2; b++) {
                int nb = B - b;
                int nw = W - P + b;
                for (int p = 1; p <= p2; p++) {
                    int maxb = (p != P) ? B - b : b;
                    count[B][W][P][maxB] += count(nb, nw, p, maxb);
                }
            }
        }
        return count[B][W][P][maxB];
    }

}
