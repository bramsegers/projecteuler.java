package P251_P300;

public class PE287 {

    static final int N = 24;
    static final int SIZE = 1 << N;
    static final int HALF_SIZE = 1 << (N - 1);

    public static void main(String args[]) {
        int length = search(SIZE, 0, 0);
        System.out.println(length);
    }

    static boolean getColor(int x, int y) {
        return !(((long) x - HALF_SIZE) * (x - HALF_SIZE)
                + ((long) y - HALF_SIZE) * (y - HALF_SIZE)
                <= (long) HALF_SIZE * HALF_SIZE);
    }

    static int search(int size, int minX, int minY) {
        if (size < SIZE) {
            boolean color = getColor(minX, minY);
            if (getColor(minX, minY + size - 1) == color
                    && getColor(minX + size - 1, minY) == color
                    && getColor(minX + size - 1, minY + size - 1) == color) {
                return 2;
            }
        }
        int length = 1;
        int halfSize = size >> 1;
        int nextX[] = new int[]{minX, minX, minX + halfSize, minX + halfSize};
        int nextY[] = new int[]{minY, minY + halfSize, minY, minY + halfSize};
        for (int i = 0; i < 4; i++) {
            length += search(halfSize, nextX[i], nextY[i]);
        }
        return length;
    }

}
