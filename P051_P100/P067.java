package P051_P100;

import java.util.ArrayList;
import java.util.List;
import util.Util;

/*
 Find the maximum total from top to bottom in P067.txt, 
 a 15K text file containing a triangle with one-hundred rows.

 NOTE: This is a much more difficult version of Problem 18. It is not possible 
 to try every route to solve this problem, as there are 2^99 altogether! If you 
 could check one trillion (10^12) routes every second it would take over twenty 
 billion years to check them all. There is an efficient algorithm to solve it. ;o)
 */
public class P067 {

    public static int solve(List<int[]> tri) {
        int max = 0;
        int[][] maxSum = new int[tri.size()][tri.size()];
        maxSum[0][0] = tri.get(0)[0];
        for (int row = 1; row < tri.size(); row++) {
            for (int col = 0; col <= row; col++) {
                maxSum[row][col] = tri.get(row)[col]
                        + (col == 0 ? maxSum[row - 1][0]
                                : col == row ? maxSum[row - 1][col - 1]
                                        : Math.max(maxSum[row - 1][col - 1], maxSum[row - 1][col]));
                max = Math.max(max, maxSum[row][col]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tri.size(); i++) {
            for (int j = 0; j <= i; j++) {
                sb.append(String.format("%5d", maxSum[i][j]));
            }
            sb.append("\n");
        }
        System.out.println(sb);
        return max;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P067.txt");
        List<int[]> tri = new ArrayList<>();
        for (String text : list) {
            String[] nums = text.split(" ");
            int[] arr = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = Integer.parseInt(nums[i]);
            }
            tri.add(arr);
        }
        System.out.println(P067.solve(tri));
    }
}
