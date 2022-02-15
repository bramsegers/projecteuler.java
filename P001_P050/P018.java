package P001_P050;

import java.util.ArrayList;
import java.util.List;

/*
 * By starting at the top of the triangle below and moving to adjacent numbers 
 * on the row below, the maximum total from top to bottom is 23.
 * 
 *          3
 *         7 4
 *        2 4 6
 *       8 5 9 3
 * 
 * That is, 3 + 7 + 4 + 9 = 23.
 * Find the maximum total from top to bottom of the triangle
 */
public class P018 {

    public static int solve(List<int[]> tri) {
        int maxSum = 0;
        for (int i = 0; i < 1 << (tri.size() - 1); i++) {
            int index = 0;
            int sum = tri.get(0)[0];
            for (int j = 1; j < tri.size(); j++) {
                index += (i >> (j - 1)) & 1;
                int num = tri.get(j)[index];
                sum += num;
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        List<int[]> tri = new ArrayList<>();
        tri.add(new int[]{75});
        tri.add(new int[]{95, 64});
        tri.add(new int[]{17, 47, 82});
        tri.add(new int[]{18, 35, 87, 10});
        tri.add(new int[]{20, 4, 82, 47, 65});
        tri.add(new int[]{19, 1, 23, 75, 3, 34});
        tri.add(new int[]{88, 2, 77, 73, 7, 63, 67});
        tri.add(new int[]{99, 65, 4, 28, 6, 16, 70, 92});
        tri.add(new int[]{41, 41, 26, 56, 83, 40, 80, 70, 33});
        tri.add(new int[]{41, 48, 72, 33, 47, 32, 37, 16, 94, 29});
        tri.add(new int[]{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14});
        tri.add(new int[]{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57});
        tri.add(new int[]{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48});
        tri.add(new int[]{63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31});
        tri.add(new int[]{4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23});
        System.out.println(P018.solve(tri));
    }
}
