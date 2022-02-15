package P501_P550;

public class PE504 {

    public static void main(String[] args) {
        new PE504().solve(100);
    }

    int[][] lat;

    void solve(int m) {
        
        lat = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                lat[i][j] = -1;
            }
        }

        int tot = 0;
        for (int a = 1; a <= m; a++) {
            for (int b = 1; b <= m; b++) {
                for (int c = 1; c <= m; c++) {
                    for (int d = 1; d <= m; d++) {
                        int p = a + b + c + d - 4 + 1 + lat(a, b) + lat(b, c) + lat(c, d) + lat(d, a);
                        int sqP = (int) Math.sqrt(p);
                        if (sqP * sqP == p) {
                            //System.out.format("%d %d %d %d -> %d%n", a, b, c, d, p);
                            tot++;
                        }
                    }
                }
            }
        }
        System.out.println(tot);
    }

    int lat(int a, int b) {
        if (lat[a][b] < 0) {
            int p = 0;
            int X = Math.min(a, b);
            int Y = Math.max(a, b);
            for (int x = 1; x < X; x++) {
                int y = x * Y / X;
                if (y * X == x * Y) {
                    y--;
                }
                p += y;
            }
            lat[a][b] = p;
            lat[b][a] = p;
        }
        return lat[a][b];
    }

}
