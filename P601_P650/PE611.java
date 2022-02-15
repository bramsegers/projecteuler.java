package P601_P650;

import static java.lang.Math.ceil;
import static java.lang.Math.max;
import static java.lang.Math.sqrt;
import java.util.BitSet;

public class PE611 {

    public static void main(String[] args) {
        new PE611().solve1(1000000);
        new PE611().solve2(1000000000000L, 10000);
        //F(1000000000000)=49283233900 
        //BUILD SUCCESSFUL (total time: 186 minutes 52 seconds)
    }

    void solve1(int N) {
        BitSet bs = new BitSet();
        int count = 0, i = 1, j;
        while (i * i + (j = i + 1) * j <= N) {
            while (i * i + j * j <= N) {
                bs.flip(i * i + j * j);
                count++;
                j++;
            }
            i++;
        }
        System.out.println(count);
        System.out.format("F(%d)=%d %n", N, bs.cardinality());
    }

    void solve2(long N, long P) {
        long c1 = 0, c2 = 0, N1, N2, imax, jmin, jmax;
        for (long p = 0; p < P; p++) {
            N1 = p * N / P;
            N2 = (p + 1) * N / P;
            BitSet bs = new BitSet();
            imax = (long) ((sqrt(2 * N2 - 1) - 1) / 2);
            for (long i = 1; i <= imax; i++) {
                jmin = (long) ceil(sqrt(N1 - i * i));
                jmin += (jmin * jmin == N1 - i * i) ? 1 : 0;
                jmin = max(i + 1, jmin);
                jmax = (long) sqrt(N2 - i * i);
                for (long j = jmin; j <= jmax; j++) {
                    bs.flip((int) (i * i + j * j - N1));
                }
                c1 += max(0, jmax - jmin + 1);
            }
            c2 += bs.cardinality();
            System.out.format("%d %d %d %n", p, c1, c2);
        }
        System.out.format("F(%d)=%d %n", N, c2);
    }

}
