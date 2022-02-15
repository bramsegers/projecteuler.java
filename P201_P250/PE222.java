package P201_P250;

import util.ShortestPath;

public class PE222 {

    public static void main(String[] args) {
        new PE222().solve(50, 30, 50);
    }

    void solve(int r, int r1, int r2) {
        int n = r2 - r1 + 1;
        double[][] dist = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double p = r1 + i;
                double q = r1 + j;
                double a = p + q;
                double b = 2D * r - p - q;
                double dy = Math.sqrt(a * a - b * b);
                dist[i][j] = dy;
            }
        }
        ShortestPath shp = new ShortestPath();
        double min = shp.solve(dist);
        System.out.println(min);
        int[] path = shp.getPath();
        min += path[0] + r1;
        min += path[path.length - 1] + r1;
        System.out.println((int) (min * 1000));
    }
    
}
