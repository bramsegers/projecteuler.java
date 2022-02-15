package P001_P050;

/*
 Starting with the number 1 and moving to the right in a clockwise direction, 
 a 5 by 5 spiral is formed as follows:

 21 22 23 24 25
 20  7  8  9 10
 19  6  1  2 11
 18  5  4  3 12
 17 16 15 14 13

 It can be verified that the sum of the numbers on the diagonals is 101.
 What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral?
 */
public class P028 {

    public static int solve(int num) {
        int[][] grid = new int[num][num];
        int posX = num / 2;
        int posY = num / 2;
        int steps = 0;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int c = 0;
        int cntr = 0;
        while (posX < num) {
            steps += (c % 2 == 0) ? 1 : 0;
            for (int i = 0; i < steps; i++) {
                grid[posX][posY] = ++cntr;
                posX += dx[c % 4];
                posY += dy[c % 4];
            }
            c++;
        }
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += grid[i][i] + grid[num - i - 1][i];
        }
        return sum - 1;
    }

    public static int solve2(int nMax) {
        int sum = 0;
        for (int n = 2; n <= (nMax + 1) >> 1; n++) {
            sum += 16 * n * n - 28 * n + 16;
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        System.out.println(P028.solve(1001));
        System.out.println(P028.solve2(1001));
    }
}
