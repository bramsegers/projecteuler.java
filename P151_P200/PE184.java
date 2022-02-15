package P151_P200;

import java.util.ArrayList;
import java.util.List;

public class PE184 {

    public static void main(String[] args) {

        new PE184().test(5);
        new PE184().solve(5);
        new PE184().solve(105);

    }

    class Point {

        int x, y;

        Point(int i, int j) {
            x = i;
            y = j;
        }

    }

    private void solve(int r) {

        List<Point> points = new ArrayList<>();
        int[][] underLine = new int[r][r];
        int[][] onLine = new int[r][r];

        for (int x = 1; x < r; x++) {
            for (int y = 1; y < r; y++) {
                if (x * x + y * y < r * r) {
                    points.add(new Point(x, y));
                }
            }
        }
        int size = points.size();

        // nr points under/on line (0,0)-(px,py) 
        for (Point p : points) {
            for (Point q : points) {
                if (p.y * q.x > p.x * q.y) {
                    underLine[p.x][p.y]++;
                } else if (p.y * q.x == p.x * q.y) {
                    onLine[p.x][p.y]++;
                }
            }
        }

        // 1 point on pos x-axis, 1 point on pos y-axis, 1 point in Q3, rotate *4
        long a = (long) (r - 1) * (r - 1) * size;

        // 1 point on pos x-axis, 1 point in Q1, 1 point in Q3, rotate *4
        long b = 0;
        for (Point p : points) {
            b += (long) (r - 1) * underLine[p.x][p.y];
        }

        // 1 point on pos y-axis, 1 point in Q1, 1 point in Q3, rotate *4
        long c = b;

        // 1 point on pos x-axis, 1 point in Q2, 1 point in Q3, rotate *4
        long d = (long) (r - 1) * size * size;

        // 1 point in Q1, 1 point in Q2, 1 point in Q3, rotate *4
        long e = 0;
        for (Point p : points) {
            for (Point q : points) {
                if (p.y * (p.x - q.x) < p.x * (p.y - q.y)) {
                    e += size;
                }
            }
        }

        // 2 points in Q1, 1 point in Q3, rotate *4 
        long f = 0;
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                Point q = points.get(j);
                if (q.x * p.y > q.y * p.x) {
                    f += underLine[p.x][p.y] - underLine[q.x][q.y] - onLine[q.x][q.y];
                } else if (q.x * p.y < q.y * p.x) {
                    f += underLine[q.x][q.y] - underLine[p.x][p.y] - onLine[p.x][p.y];
                }
            }
        }

        long sum = (a + b + c + d + e + f) << 2;
        System.out.println("Q3 X Y " + a);
        System.out.println("Q1 Q3 X " + b);
        System.out.println("Q1 Q3 Y " + c);
        System.out.println("Q2 Q3 X " + d);
        System.out.println("Q1 Q2 Q3 " + e);
        System.out.println("Q1 Q1 Q3 " + f);
        System.out.format("P(%d)=%d%n%n", r, sum);
    }

    void test(int r) {

        List<Point> pts = new ArrayList<>();
        for (int x = -r; x < r; x++) {
            for (int y = -r; y < r; y++) {
                if (x * x + y * y < r * r) {
                    pts.add(new Point(x, y));
                }
            }
        }
        int count = 0;
        Point o = new Point(0, 0);
        for (int i = 0; i < pts.size(); i++) {
            for (int j = i + 1; j < pts.size(); j++) {
                for (int k = j + 1; k < pts.size(); k++) {
                    if (pointInTriangle(o, pts.get(i), pts.get(j), pts.get(k))) {
                        count++;
                    }
                }
            }
        }
        System.out.format("P(%d)=%d (test)%n%n", r, count);
    }

    boolean pointInTriangle(Point p, Point p0, Point p1, Point p2) {
        int s = p0.y * p2.x - p0.x * p2.y + (p2.y - p0.y) * p.x + (p0.x - p2.x) * p.y;
        int t = p0.x * p1.y - p0.y * p1.x + (p0.y - p1.y) * p.x + (p1.x - p0.x) * p.y;
        if ((s < 0) != (t < 0)) {
            return false;
        }
        int A = -p1.y * p2.x + p0.y * (p2.x - p1.x) + p0.x * (p1.y - p2.y) + p1.x * p2.y;
        if (A < 0) {
            s = -s;
            t = -t;
            A = -A;
        }
        return s > 0 && t > 0 && (s + t) < A;
    }

}
