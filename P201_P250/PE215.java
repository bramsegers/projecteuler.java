package P201_P250;

public class PE215 {

    public static void main(String[] args) {
        //new PE215f().solveTest(16, 11);
        new PE215().solve(32, 10);
    }

    int cols, rows;
    long[][][] m;

    void solve(int c, int r) {
        cols = c;
        rows = r;
        m = new long[4][cols * rows][1 << 16];
        long start = System.currentTimeMillis();
        long count = count(3, 0, 0) + count(2, 0, 0);
        long end = System.currentTimeMillis();
        System.out.format("P(%d,%d)=%d%nElapsed:%dms%n", c, r, count, end - start);
    }

    long count(int len, int pos, int bits) {
        if (pos % cols + len > cols) {
            m[len][pos][bits] = 0L;
        } else if ((pos % cols) > 0 && (pos / cols) > 0 && isCrack(bits)) {
            m[len][pos][bits] = 0L;
        } else if (pos + len == cols * rows) {
            m[len][pos][bits] = 1L;
        } else if (m[len][pos][bits] == 0) {
            long i = count(2, pos + len, shift(bits, len));
            long j = count(3, pos + len, shift(bits, len));
            m[len][pos][bits] = i + j;
        }
        return m[len][pos][bits];
    }

    int shift(int bits, int len) {
        return ((bits << 1) + (len - 2)) & 0b1111111111111111;
    }

    boolean isCrack(int bits) {
        int shifted = 0;
        while (shifted < cols) {
            shifted += (bits & 1) + 2;
            bits >>= 1;
        }
        return shifted == cols;
    }

    void solveTest(int c, int r) {
        cols = c;
        rows = r;
        int count = count(new boolean[cols][rows], 0, 0);
        System.out.println(count);
    }

    int count(boolean[][] wall, int c, int r) {
        if (c > cols) {
            //brick does not fit in row
            return 0;
        }
        if (c == cols) {
            //brick completes row, move to next row
            c = 0;
            r++;
            if (r == rows) {
                //all rows completed, solution found
                //print(wall);
                return 1;
            }
        }
        if (r > 0 && c > 0 && wall[c][r - 1]) {
            //brick causes a crack in the wall
            return 0;
        }
        //so far so good, add more bricks
        wall[c][r] = true;
        int i = count(copyOf(wall), c + 2, r);
        int j = count(copyOf(wall), c + 3, r);
        return i + j;
    }

    boolean[][] copyOf(boolean[][] m) {
        boolean[][] rv = new boolean[cols][rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rv[c][r] = m[c][r];
            }
        }
        return rv;
    }

    void print(boolean[][] m) {
        String s = "";
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                s += String.format("%d", m[c][r] ? 1 : 0);
            }
            s += "\n";
        }
        System.out.println(s);
    }

}
