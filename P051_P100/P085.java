package P051_P100;

/*
 By counting carefully it can be seen that a rectangular grid 
 measuring 3 by 2 contains eighteen rectangles.

 Although there exists no rectangular grid that contains exactly two million 
 rectangles, find the area of the grid with the nearest solution.
 */
public class P085 {

    public static String solve(int tot) {
        String rv = "";
        int minDiff = tot;
        int x = 2;
        int y = 1;
        while (x > y) {
            x = 0;
            int currDiff = tot;
            int lastDiff = tot + 1;
            while (lastDiff > currDiff) {
                x++;
                lastDiff = currDiff;
                currDiff = Math.abs(tot - rectangles(x, y));
            }
            int sum = rectangles(--x, y);
            int diff = Math.abs(tot - sum);
            String out = "(" + x + "," + y + ")  Squares:" + sum + "  Diff:" + diff;
            System.out.println(out);
            if (diff < minDiff) {
                minDiff = diff;
                rv = "-> "+out+"  Area:"+(x*y)+" <-";
            }
            y++;
        }
        return rv;
    }

    private static int rectangles(int x, int y) {
        int sum = 0;
        for (int j = 1; j <= y; j++) {
            for (int i = 1; i <= x; i++) {
                int pX = 1 + x - i;
                int pY = 1 + y - j;
                sum += pX * pY;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P085.solve(2000000));
    }
}
