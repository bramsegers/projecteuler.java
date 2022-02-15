package P201_P250;

public class PE237 {

    //https://oeis.org/A181688
    public static void main(String[] args) {
        new PE237().test(10);
        new PE237().solve(1000000000000L, 100000000);
    }

    void solve(long max, int mod) {
        int a1 = 1, a2 = 1, a3 = 4, a4 = 8, an = 0;
        int b1 = 1, b2 = 1, b3 = 4, b4 = 8;
        long i;
        for (i = 4; i < max; i++) {
            an = (((a4 + a3 - a2 + mod) << 1) + a1) % mod;
            a1 = a2;
            a2 = a3;
            a3 = a4;
            a4 = an;
            if (a1 == b1 && a2 == b2 && a3 == b3 && a4 == b4) {
                long period = i - 3;
                while (i + period < max) {
                    i += period;
                }
            }
        }
        System.out.format("P(%d)=%d%n", i, an);
    }

    int C = 10;
    int count;

    void test(int c) {
        for (int i = 1; i <= c; i++) {
            count = 0;
            C = i;
            int[] m = new int[4 * C];
            m[0] = 1;
            recur(m, 0, 1);
            System.out.println(C + " " + count);
        }
    }

    void recur(int[] m, int pos, int step) {
        if (pos == 3 * C && step == 4 * C) {
            print(m);
            count++;
        } else {
            for (int d = 0; d < 4; d++) {
                int pos2 = getpos(pos, d);
                if (pos2 > 0 && m[pos2] == 0) {
                    int[] m2 = m.clone();
                    m2[pos2] = step + 1;
                    recur(m2, pos2, step + 1);
                }
            }
        }
    }

    int getpos(int pos, int d) {
        switch (d) {
            case 0:
                return pos - C;
            case 1:
                return (pos % C) == C - 1 ? -1 : pos + 1;
            case 2:
                return pos >= 3 * C ? -1 : pos + C;
            case 3:
                return (pos % C) == 0 ? -1 : pos - 1;
        }
        return -1; // should not occur
    }

    void print(int[] m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4 * C; i++) {
            String exp = getpos(i, 1) < 0 ? "%2d%n" : "%2d ";
            sb.append(String.format(exp, m[i]));
        }
        System.out.println(sb);
    }

}
