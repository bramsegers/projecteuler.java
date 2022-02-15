package P051_P100;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Each of the six faces on a cube has a different digit (0 to 9) written on it.
 * The same is done to a second cube. By placing the two cubes side-by-side in 
 * different positions we can form a variety of 2-digit numbers.
 * 
 * In fact, by carefully choosing the digits on both cubes it is possible to display 
 * all of the square numbers below one-hundred: 01, 04, 09, 16, 25, 36, 49, 64, and 81.
 * For example, one way this can be achieved is by placing {0, 5, 6, 7, 8, 9} 
 * on one cube and {1, 2, 3, 4, 8, 9} on the other cube.
 * 
 * However, for this problem we shall allow the 6 or 9 to be turned upside-down 
 * so that an arrangement like {0, 5, 6, 7, 8, 9} and {1, 2, 3, 4, 6, 7} allows 
 * for all nine square numbers to be displayed; otherwise it would be impossible to obtain 09.
 * 
 * In determining a distinct arrangement we are interested in the digits on each cube, not the order.
 * {1, 2, 3, 4, 5, 6} is equivalent to {3, 6, 4, 1, 2, 5}
 * {1, 2, 3, 4, 5, 6} is distinct from {1, 2, 3, 4, 5, 9}
 * 
 * How many distinct arrangements of the two cubes allow for all of the square numbers to be displayed?
 */
public class P090 {

    private static Set<String> perm = new TreeSet<>();

    public static int solve() {
        int sum = 0;
        getPermutation("", "0123456789");
        System.out.println(perm.size());
        List<String> list = new ArrayList<>(perm);
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (allSquaresPossible(list.get(i), list.get(j))) {
                    System.out.println(list.get(i) + "-" + list.get(j));
                    sum++;
                }
            }
        }
        return sum;
    }

    public static boolean allSquaresPossible(String c1, String c2) {
        c1 += (c1.contains("6") && !c1.contains("9")) ? "9" : (c1.contains("9") && !c1.contains("6")) ? "6" : "";
        c2 += (c2.contains("6") && !c2.contains("9")) ? "9" : (c2.contains("9") && !c2.contains("6")) ? "6" : "";
        String[] squares = new String[]{"01", "04", "09", "16", "25", "36", "49", "64", "81"};
        for (String square : squares) {
            String num1 = square.substring(0, 1);
            String num2 = square.substring(1);
            if (!((c1.contains(num1) && c2.contains(num2)) || (c1.contains(num2) && c2.contains(num1)))) {
                return false;
            }
        }
        return true;
    }

    private static void getPermutation(String pre, String str) {
        int n = str.length();
        if (n == 4) {
            Set<Character> set = new TreeSet<>();
            for (char ch : pre.toCharArray()) {
                set.add(ch);
            }
            pre = "";
            for (char ch : set) {
                pre += ch;
            }
            perm.add(pre);
        } else {
            for (int i = 0; i < n; i++) {
                getPermutation(pre + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P090.solve());
    }
}
