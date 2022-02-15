package P051_P100;

/*
 * There are exactly fourteen triangles containing a right angle that can be formed 
 * when each co-ordinate lies between 0 and 2 inclusive, that is 0 <= x1,y1,x2,y2 <= 2
 * 
 * Given that 0 <= x1,y1,x2,y2 <= 50, how many right triangles can be formed?
 */
public class P091 {

    public static int solve(int nMax) {
        int sum = 0;
        for (int n = 1; n <= nMax; n++) {
            sum = 0;
            for (int xy1 = 0; xy1 < (n + 1) * (n + 1); xy1++) {
                for (int xy2 = xy1 + 1; xy2 < (n + 1) * (n + 1); xy2++) {
                    int x1 = xy1 % (n + 1);
                    int y1 = xy1 / (n + 1);
                    int x2 = xy2 % (n + 1);
                    int y2 = xy2 / (n + 1);
                    sum += isNiceTriangle(x1, y1, x2, y2) ? 1 : 0;
                }
            }
            System.out.println(n + ": " + sum);
        }
        return sum;
    }

    private static boolean isNiceTriangle(int x1, int y1, int x2, int y2) {
        return x1 * (y2 - y1) != (x2 - x1) * y1 //         points not in 1 line?
                && (x1 * (x2 - x1) == y1 * (y1 - y2) //    angle a,b = right?
                || x1 * x2 == -y1 * y2 //                  angle a,c = right?
                || (x2 - x1) * x2 == (y1 - y2) * y2);//    angle b,c = right?
    }

    public static void main(String[] args) {
        System.out.println(P091.solve(50));
    }
}
