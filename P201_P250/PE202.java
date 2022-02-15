package P201_P250;

import util.Util;

public class PE202 {

    public static void main(String[] args) {
        new PE202().test(1000001);
        //new PE202().test(12017639147L); // 5 mins 10 sec
        new PE202().solveHardCoded();
    }

    void test(long bounce) {
        long count = 0;
        long row = (bounce + 3) / 2;
        System.out.format("Bounces: %d%nRow:     %d%nCount:   ", bounce, row);
        int[][] seq = {{3, 0, 1, 0, 2, 0}, {0, 2, 0, 3, 0, 1}};
        int[] start = {6, 3};
        for (long col = start[(int) (row & 1)]; col < row; col += 6) {
            //System.out.println(row+","+col);
            long gcd = Util.gcd(row, col);
            if (gcd == 1) {
                count++;
            } else {
                boolean open = true;
                for (long j = 1; j < Math.min(gcd, 3) && open; j++) {
                    long r = row / gcd * j;
                    long c = col / gcd * j;
                    open = seq[(int) (r & 1)][(int) (c % 6)] == 0;
                }
                count += open ? 1 : 0;
            }
        }
        System.out.println(2 * count);
    }

    void solveHardCoded() {
        long bounce = 12017639147L;
        long row = (bounce + 3) / 2;
        long count = 0;
        for (long N = 3; N < row; N += 6) {
            if (N % 5 == 0) {
                continue;
            }
            if (N % 11 == 0) {
                continue;
            }
            if (N % 17 == 0) {
                continue;
            }
            if (N % 23 == 0) {
                continue;
            }
            if (N % 29 == 0) {
                continue;
            }
            if (N % 41 == 0) {
                continue;
            }
            if (N % 47 == 0) {
                continue;
            }
            count++;
        }
        System.out.println(2 * count);
    }

}
