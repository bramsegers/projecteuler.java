package P101_P150;

public class P147 {

    public static void main(String[] args) {
        new P147().solve(43, 47);
    }

    void solve(int cIn, int rIn) {
        long start = System.currentTimeMillis();
        long sum = 0;
        int cMax = Math.min(cIn, rIn);
        int rMax = Math.max(cIn, rIn);
        for (int R = 1; R <= rMax; R++) {
            for (int C = 1; C <= cMax && (C <= R || R > cMax); C++) {
                for (int r = 1; r <= R; r++) {
                    for (int c = 1; c <= C; c++) {
                        sum += (R > cMax || C == R ? 1 : 2) * countStraight(C, R, c, r);
                    }
                }
                for (int r = 1; r < Math.min(R, C) << 1; r++) {
                    for (int c = 1; c <= r; c++) {
                        sum += (R > cMax || C == R ? 1 : 2) * countCross(C << 1, R << 1, c, r);
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d,%d)=%d%nElapsed:%dms%n", cIn, rIn, sum, end - start);
    }

    long countStraight(int c, int r, int i, int j) {
        return (c - i + 1) * (r - j + 1);
    }

    long countCross(int C, int R, int i, int j) {
        long count = 0;
        for (int r = 0; r <= R; r += 2) {
            for (int c = 2; c <= C; c += 2) {
                count += (c - i >= 0 && c + j <= C && r + i + j <= R) ? 1 : 0;
                count += (c - i - 1 >= 0 && c + j - 1 <= C && r + i + j + 1 <= R) ? 1 : 0;
            }
        }
        return i == j ? count : count << 1;
    }
}
