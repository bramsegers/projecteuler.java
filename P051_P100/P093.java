package P051_P100;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import util.Util;

/*
 * By using each of the digits from the set {1,2,3,4} exactly once, and making 
 * use of the four arithmetic operations (+,-,*,/) and brackets/parentheses, 
 * it is possible to form different positive integer targets.
 * For example:
 * 8 = (4 * (1 + 3)) / 2
 * 14 = 4 * (3 + 1 / 2)
 * 19 = 4 * (2 + 3)  1
 * 36 = 3 * 4 * (2 + 1)
 * Note that concatenations of the digits, like 12 + 34, are not allowed.
 * 
 * Using the set {1,2,3,4} it is possible to obtain thirty-one different target numbers 
 * of which 36 is the maximum, and each of the numbers 1 to 28 can be obtained before 
 * encountering the first non-expressible number.
 * 
 * Find the set of four distinct digits, a < b < c < d, for which the longest set 
 * of consecutive positive integers, 1 to n, can be obtained, giving your answer as a string: abcd.
 */
public class P093 {

    private static Set<String> set;

    public static String solve() {

        // get all possiblities
        Map<String, Set<String>> mapNums = new TreeMap<>();
        Map<String, Map<Integer, String>> mapEval = new TreeMap<>();
        for (int i = 1234; i < 9999; i++) {
            char[] ch = ("" + i).toCharArray();
            if (ch[0] < ch[1] && ch[1] < ch[2] && ch[2] < ch[3]) {
                set = new TreeSet<>();
                getPermutations("", "" + i, 4);
                mapNums.put("" + i, set);
                mapEval.put("" + i, new TreeMap());
            }
        }
        set = new TreeSet<>();
        getPermutations("", "+-*/+-*/+-*/", 3);

        // evaluate all possiblities
        for (String s : mapNums.keySet()) {
            for (String nums : mapNums.get(s)) {
                for (String ops : set) {
                    String[] eval = evaluate(nums, ops);
                    if (eval[0] != null) {
                        mapEval.get(s).put(Integer.parseInt(eval[0]), eval[1]);
                    }
                    if (eval[2] != null) {
                        mapEval.get(s).put(Integer.parseInt(eval[2]), eval[3]);
                    }
                }
            }
        }

        // show results        
        int maxLength = 0;
        int maxNum = 0;
        for (String s : mapEval.keySet()) {
            System.out.println("\n" + s);
            int j = 0;
            int len = 0;
            for (int val : mapEval.get(s).keySet()) {
                len += (++j == val) ? 1 : 0;
                System.out.println(val + " = " + mapEval.get(s).get(val));
            }
            if (len >= maxLength) {
                maxLength = len;
                maxNum = Integer.parseInt(s);
            }
        }
        return "\nabcd=" + maxNum + ", length:" + maxLength;
    }

    private static String[] evaluate(String nums, String ops) {
        double d1 = nums.charAt(0) - '0';
        double d2 = nums.charAt(1) - '0';
        double d3 = nums.charAt(2) - '0';
        double d4 = nums.charAt(3) - '0';
        char op1 = ops.charAt(0);
        char op2 = ops.charAt(1);
        char op3 = ops.charAt(2);
        double evA = (op1 == '*') ? d1 * d2 : (op1 == '/') ? d1 / d2 : (op1 == '+') ? d1 + d2 : d1 - d2;
        double evB = (op2 == '*') ? d3 * d4 : (op2 == '/') ? d3 / d4 : (op2 == '+') ? d3 + d4 : d3 - d4;
        double evC = (op2 == '*') ? evA * d3 : (op2 == '/') ? evA / d3 : (op2 == '+') ? evA + d3 : evA - d3;
        double ev1 = (op3 == '*') ? evA * evB : (op3 == '/') ? evA / evB : (op3 == '+') ? evA + evB : evA - evB;
        double ev2 = (op3 == '*') ? evC * d4 : (op3 == '/') ? evC / d4 : (op3 == '+') ? evC + d4 : evC - d4;
        return new String[]{
            ev1 > 0 && ("" + ev1).endsWith(".0") ? ("" + (int) ev1) : null,
            "(" + (int) d1 + op1 + (int) d2 + ")" + op3 + "(" + (int) d3 + op2 + (int) d4 + ")",
            ev2 > 0 && ("" + ev2).endsWith(".0") ? ("" + (int) ev2) : null,
            "((" + (int) d1 + op1 + (int) d2 + ")" + op2 + (int) d3 + ")" + op3 + (int) d4};
    }

    private static void getPermutations(String pre, String s, int len) {
        if (pre.length() == len) {
            set.add(pre);
        } else {
            for (int i = 0; i < s.length(); i++) {
                getPermutations(pre + s.charAt(i), s.substring(0, i) + s.substring(i + 1), len);
            }
        }
    }

    public static int solve(int dMax, int n) {
        int lenMax = 0;
        List<Double> dChosen = null;
        List<List<Double>> digits = new ArrayList<>();
        Util.chooseD(dMax, n, digits);
        for (List<Double> d : digits) {
            Set<Integer> eval = new TreeSet<>();
            getEvals(d, eval);
            int len = 0;
            while (eval.contains(++len)) {
            }
            if (len - 1 >= lenMax) {
                lenMax = len - 1;
                dChosen = d;
            }
        }
        System.out.println("Max found at " + dChosen + ", length: " + lenMax);
        return lenMax;
    }

    private static void getEvals(List<Double> d, Set<Integer> eval) {
        if (d.size() == 1) {
            double ed = d.get(0);
            int ei = (int) ed;
            if (ei > 0 && Math.abs(ed - ei) < 0.00001D) {
                eval.add(ei);
            }
        }
        for (int i = 0; i < d.size(); i++) {
            for (int j = 0; j < d.size(); j++) {
                if (i != j) {
                    for (int op = 0; op < 4; op++) {
                        double d1 = d.get(i);
                        double d2 = d.get(j);
                        List<Double> dd = new ArrayList<>(d);
                        dd.remove(d1);
                        dd.remove(d2);
                        dd.add(eval(d1, d2, op));
                        getEvals(dd, eval);
                    }
                }
            }
        }
    }

    private static double eval(double d1, double d2, int op) {
        switch (op) {
            case 0:
                return d1 + d2;
            case 1:
                return d1 - d2;
            case 2:
                return d1 * d2;
            default:
                return d1 / d2;
        }
    }

    public static void main(String[] args) {
        //System.out.println(P093.solve());
        System.out.println(P093.solve(9, 4));
    }
}
