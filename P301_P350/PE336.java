package P301_P350;

import java.util.TreeSet;

public class PE336 {

    public static void main(String[] args) {
        new PE336().solve(6, 10);
        new PE336().solve(11, 2011);
    }

    String order;
    int index;
    int max = -1;
    TreeSet<String> set;

    void solve(int n, int i) {
        order = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, n);
        index = i;
        permutation("", order);
        String s = null;
        for (int j = 0; j < i; j++) {
            s = set.pollFirst();
        }
        System.out.format("P(%d,%d) = %d,%s%n", n, i, max, s);
    }

    void permutation(String pre, String s) {
        if (s.length() == 0) {
            solve(pre);
        } else {
            for (int i = 0; i < s.length(); i++) {
                permutation(pre + s.charAt(i), s.substring(0, i) + s.substring(i + 1));
            }
        }
    }

    void solve(String t) {
        String s = t;
        int start = 0;
        int steps = 0;
        while (start < order.length()) {
            char nextChar = order.charAt(start);
            int i = s.indexOf(nextChar);
            if (i == start) {
                start++;
            } else {
                if (i < order.length() - 1) {
                    //flip to end 
                    s = s.substring(0, i) + reverse(s.substring(i));
                } else {
                    // flip to begin
                    s = s.substring(0, start) + reverse(s.substring(start));
                }
                steps++;
            }
        }
        if (steps > max) {
            set = new TreeSet<>();
            max = steps;
        }
        if (steps == max) {
            set.add(t);
        }
        //System.out.println(t + " " + steps);
    }

    String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }

}
