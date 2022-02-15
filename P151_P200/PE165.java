package P151_P200;

import java.util.HashSet;
import java.util.Set;

public class PE165 {

    public static void main(String[] args) {
        new PE165().solve(5000);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();

        // generate pseudo random numbers
        int[] t = new int[nMax * 4 + 1];
        long s = 290797;
        for (int n = 1; n <= nMax * 4; n++) {
            s = (s * s) % 50515093;
            t[n] = (int) (s % 500);
        }

        // generate lines
        Line[] lines = new Line[nMax];
        for (int i = 0; i < nMax; i++) {
            Point p1 = new Point(t[4 * i + 1], t[4 * i + 2]);
            Point p2 = new Point(t[4 * i + 3], t[4 * i + 4]);
            lines[i] = new Line(p1, p2);
        }

        // check all combinations
        Set<Point> intersectionPoints = new HashSet<>();
        for (int i = 0; i < nMax; i++) {
            for (int j = i + 1; j < nMax; j++) {
                Point p = lines[i].getIntersectionPoint(lines[j]);
                if (p != null) {
                    intersectionPoints.add(p);
                }
            }
        }

        long sum = intersectionPoints.size();
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);

    }

    static class Point {

        double x, y;

        Point(double px, double py) {
            x = px;
            y = py;
        }

        @Override
        public boolean equals(Object other) {
            if ((other == null) || (!(other instanceof Point))) {
                return false;
            } else {
                Point o = (Point) other;
                return (x == o.x && y == o.y);
            }
        }

        @Override
        public int hashCode() {
            return (x + ":" + y).hashCode();
        }

    }

    static class Line {

        Point start, end;

        Line(Point sp, Point ep) {
            start = sp;
            end = ep;
        }

        Point getIntersectionPoint(Line o) {
            double a1 = end.y - start.y;
            double b1 = start.x - end.x;
            double c1 = end.x * start.y - start.x * end.y;

            double a2 = o.end.y - o.start.y;
            double b2 = o.start.x - o.end.x;
            double c2 = o.end.x * o.start.y - o.start.x * o.end.y;

            double denom = a1 * b2 - a2 * b1;
            if (denom == 0) {
                return null;
            } else {
                double x = (b1 * c2 - b2 * c1) / denom;
                double y = (a2 * c1 - a1 * c2) / denom;
                Point result = new Point(x, y);
                if (containsPoint(result)
                        && o.containsPoint(result)
                        && !start.equals(result)
                        && !end.equals(result)
                        && !o.end.equals(result)
                        && !o.start.equals(result)) {
                    return result;
                } else {
                    return null;
                }
            }
        }

        boolean containsPoint(Point point) {
            return point.x >= Math.min(start.x, end.x)
                    && point.x <= Math.max(start.x, end.x)
                    && point.y >= Math.min(start.y, end.y)
                    && point.y <= Math.max(start.y, end.y);
        }
    }

}
