package P251_P300;

import java.util.ArrayList;
import static util.Util.pythTriplets;
import static java.lang.Math.abs;
import static java.lang.Math.min;

public class PE292 {

    public static void main(String[] args) {
        new PE292().solve(30);
        new PE292().solve(60);
        new PE292().solve(120);                
    }

    int N;
    ArrayList<int[]> Q, PT;

    void solve(int n) {
        N = n;
        Q = new ArrayList<>();
        PT = new ArrayList<>();
        for (int[] p : pythTriplets(n / 2)) {
            PT.add(p);
            PT.add(new int[]{p[1], p[0], p[2]});
        }
        genQ(0, 0, 0, 0, 0);
        long count = 0;
        for (int[] q1 : Q) {
            for (int[] q2 : Q) {
                if (!valid(q1, q2)) continue;
                for (int[] q3 : Q) {
                    if (!valid(q1, q2, q3)) continue;
                    for (int[] q4 : Q) {
                        if (!valid(q1, q2, q3, q4)) continue;
                        int x = q1[0] + q2[0] - q3[0] - q4[0];
                        int y = q1[1] - q2[1] - q3[1] + q4[1];
                        int d = q1[2] + q2[2] + q3[2] + q4[2];
                        int s = q1[3] + q2[3] + q3[3] + q4[3];
                        d+= abs(x) + abs(y);
                        s+= min(abs(x), 1) + min(abs(y), 1);     
                        count -= (s == 2) ? 1 : 0;                   
                        for (int a = (s == 0 ? 2 : 0); d + a <= N; a += 2) {
                            count += (N - d - a) / 2;
                            count += (s == 0) ? 0 : 1;
                        }
                    }
                }
            }
        }
        System.out.format("P(%d)=%d%n", N, count);
    }

    boolean valid(int[]... q) {
        int x = 0, y = 0, len = 0;
        for (int i = 0; i < q.length; i++) {
            x += q[i][0] * ((i == 0 || i == 1) ? 1 : -1);
            y += q[i][1] * ((i == 0 || i == 3) ? 1 : -1);
            len += q[i][2];
        }
        return (x * x + y * y <= (N - len) * (N - len));
    }

    void genQ(int x, int y, int len, int i, int j) {
        if (x * x + y * y > (N - len) * (N - len)) {
            return;
        }
        if (i == PT.size()) {
            Q.add(new int[]{x, y, len, j});
            return;
        }
        genQ(x, y, len, i + 1, j);
        int[] s = PT.get(i);
        int m = 0, a = s[0], b = s[1], c = s[2];
        while (len + (++m) * c <= N) {
            genQ(x + m * a, y + m * b, len + m * c, i + 1, j + 1);
        }
    }

}
