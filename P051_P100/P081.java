package P051_P100;

import java.util.List;
import util.Util;

/*
 * In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, 
 * by only moving to the right and down, is indicated between [] and is equal to 2427.
 *
 *      [131]    673     234     103     018
 *      [201]   [096]   [342]    965     150
 *       630     803    [746]   [422]    111
 *       537     699     497    [121]    956
 *       805     732     524    [037]   [331]
 *
 * Find the minimal path sum in P081.txt from the top left to the bottom right by only moving right and down.
 */
public class P081 {

    public static int solve(int[][] arr) {
        System.out.println("Input array:");
        print(arr);
        int[][] minSum = new int[arr.length][arr[0].length];
        minSum[0][0] = arr[0][0];
        int r = arr.length + arr[0].length - 1;
        for (int i = 1; i < r; i++) {
            int x2 = Math.min(i, arr[0].length - 1);
            int y1 = Math.min(i, arr.length - 1);
            int x1 = i - y1;
            for (int j = x1; j <= x2; j++) {
                int a = (j == 0) ? Integer.MAX_VALUE : minSum[y1][j - 1];
                int b = (y1 == 0) ? Integer.MAX_VALUE : minSum[y1 - 1][j];
                minSum[y1][j] = arr[y1][j] + Math.min(a, b);
                y1--;
            }
        }
        System.out.println("Minimal sum array:");
        print(minSum);
        return minSum[arr.length - 1][arr[0].length - 1];
    }

    private static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(String.format("%7s", arr[i][j]));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        /*
         arr = new int[][]{
         {131, 673, 234, 103, 18},
         {201, 96, 342, 965, 150},
         {630, 803, 746, 422, 111},
         {537, 699, 497, 121, 956},
         {805, 732, 524, 37, 331}};       
         */
        List<String> list = Util.readText("files/P081.txt");
        int[][] arr = new int[list.size()][list.get(0).split(",").length];
        for (int i = 0; i < list.size(); i++) {
            String[] nums = list.get(i).split(",");
            for (int j = 0; j < nums.length; j++) {
                arr[i][j] = Integer.parseInt(nums[j]);
            }
        }
        System.out.println(P081.solve(arr));
    }
}
