package P051_P100;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import util.Util;

/*
 * The minimal path sum in the 5 by 5 matrix below, by starting in any cell in 
 * the left column and finishing in any cell in the right column, and only 
 * moving up, down, and right, is indicated between []; the sum is equal to 994.
 *
 *       131     673    [234]   [103]   [018]
 *      [201]   [096]   [342]    965     150
 *       630     803     746     422     111
 *       537     699     497     121     956
 *       805     732     524     037     331
 *
 * Find the minimal path sum in P082.txt from the left column to the right column.
 */
public class P082 {

    public static int solve(int[][] arr) {
//        arr = new int[][]{
//            {131, 673, 234, 103, 18},
//            {201, 96, 342, 965, 150},
//            {630, 803, 746, 422, 111},
//            {537, 699, 497, 121, 956},
//            {805, 732, 524, 37, 331}};
        print(arr);
        Map<String, Map<String, Integer>> nodes = new TreeMap<>();
        Map<String, Integer> map;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                map = new TreeMap<>();
                if (i > 0) {
                    map.put((i - 1) + "-" + j, arr[i][j]);
                }
                if (i < arr.length - 1) {
                    map.put((i + 1) + "-" + j, arr[i][j]);
                }
                if (j < arr[i].length - 1) {
                    map.put(i + "-" + (j + 1), arr[i][j]);
                }
                if (j == arr[i].length - 1) {
                    map.put("Y", arr[i][j]);
                }
                nodes.put(i + "-" + j, map);
            }
        }

        //start
        map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(i + "-0", 0);
        }
        nodes.put(" X", map);

        //end
        map = new TreeMap<>();
        nodes.put("Y", map);

        // row 0
        String[][] nodeState = new String[nodes.keySet().size() + 1][nodes.keySet().size() + 1];
        int j = 0;
        for (String node : nodes.keySet()) {
            nodeState[0][j++] = node;
        }
        nodeState[0][j] = "done";

        // row 1
        String curr = " X";
        int i = 1;
        j = 0;
        int minJ = 0;
        int minVal = Integer.MAX_VALUE / 2;
        int val;
        for (String node : nodes.keySet()) {
            if (nodeState[i][j] == null) {
                if (node.equals(curr)) {
                    val = 0;
                    nodeState[i][j] = node + "=" + val;
                } else {
                    val = Integer.MAX_VALUE / 2;
                    nodeState[i][j] = "-=" + val;
                }
                if (val < minVal) {
                    minVal = val;
                    minJ = j;
                }
            }
            j++;
        }
        for (int ii = i; ii < nodes.keySet().size() + 1; ii++) {
            nodeState[ii][minJ] = nodeState[i][minJ];
        }
        curr = nodeState[0][minJ];
        Set<String> set = nodes.get(curr).keySet();
        nodeState[i][j] = curr;
        int lastMinVal = 0;

        // row >1
        while (i < nodes.keySet().size()) {
            i++;
            j = 0;
            minJ = 0;
            minVal = Integer.MAX_VALUE / 2;
            for (String node : nodes.keySet()) {
                if (nodeState[i][j] == null) {
                    if (set.contains(node)) {
                        int val1 = nodes.get(curr).get(node) + lastMinVal;
                        int val2 = Integer.parseInt(nodeState[i - 1][j].split("=")[1]);
                        nodeState[i][j] = (val1 < val2) ? curr + "=" + val1 : nodeState[i - 1][j];

                    } else {
                        nodeState[i][j] = nodeState[i - 1][j];
                    }
                    val = Integer.parseInt(nodeState[i][j].split("=")[1]);
                    if (val < minVal) {
                        minVal = val;
                        minJ = j;
                    }
                }
                j++;
            }
            for (int ii = i + 1; ii < nodes.keySet().size() + 1; ii++) {
                nodeState[ii][minJ] = nodeState[i][minJ];
            }
            curr = nodeState[0][minJ];
            set = nodes.get(curr).keySet();
            lastMinVal = minVal;
            nodeState[i][j] = curr;
        }

        //print(nodeState);
        System.out.println(nodeState[0][nodeState[0].length - 2]);
        System.out.println(nodeState[nodeState.length - 1][nodeState[0].length - 2]);
        return Integer.parseInt(nodeState[nodeState.length - 1][nodeState[0].length - 2].split("=")[1]);
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
        List<String> list = Util.readText("files/P082.txt");
        int[][] arr = new int[list.size()][list.get(0).split(",").length];
        for (int i = 0; i < list.size(); i++) {
            String[] nums = list.get(i).split(",");
            for (int j = 0; j < nums.length; j++) {
                arr[i][j] = Integer.parseInt(nums[j]);
            }
        }
        System.out.println(P082.solve(arr));
    }
}
