package P151_P200;

public class PE191 {

    public static void main(String[] args) {
        new PE191().solve(30);
    }

    void solve(int days) {
        long start = System.currentTimeMillis();
        m = new long[days][3][2];
        long count = count(days - 1, 0, 0) + count(days - 1, 1, 0) + count(days - 1, 0, 1);
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", days, count, end - start);
    }

    long[][][] m;

    long count(int days, int absent, int late) {
        if (absent == 3 || late == 2) {
            return 0;
        } else if (days == 0) {
            m[days][absent][late] = 1;
        } else if (m[days][absent][late] == 0) {
            m[days][absent][late]
                    = count(days - 1, 0, late)
                    + count(days - 1, absent + 1, late)
                    + count(days - 1, 0, late + 1);
        }
        return m[days][absent][late];
    }
}
