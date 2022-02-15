package P051_P100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 The cube, 41063625 (345^3), can be permuted to produce two other cubes: 
 56623104 (384^3) and 66430125 (405^3). In fact, 41063625 is the smallest cube 
 which has exactly three permutations of its digits which are also cube.

 Find the smallest cube for which exactly five permutations of its digits are cube.
 */
public class P062 {

    public static String solve(int n) {
        String solution = null;
        Map<String, List<Long>> map = new HashMap<>();
        long i = 0;
        while (solution == null) {
            i++;
            char[] ch = String.valueOf(i * i * i).toCharArray();
            Arrays.sort(ch);
            String cube = new String(ch);
            List<Long> list = map.get(cube);
            if (list == null) {
                list = new ArrayList<>();
                map.put(cube, list);
            }
            list.add(i);
            if (list.size() == n) {
                solution = "";
                for (long c : list) {
                    solution += String.format("C(%d)=%d%n", c, c * c * c);
                }
            }
        }
        return solution;
    }

    public static void main(String[] args) {
        System.out.println(P062.solve(5));
    }
}
