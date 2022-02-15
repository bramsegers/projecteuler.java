package P051_P100;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/*
 A common security method used for online banking is to ask the user for three 
 random characters from a passcode. For example, if the passcode was 531278, 
 they may ask for the 2nd, 3rd, and 5th characters; the expected reply would be: 317.

 The array contains fifty successful login attempts.
 Given that the three characters are always asked for in order, analyse the file 
 so as to determine the shortest possible secret passcode of unknown length.
 */
public class P079 {

    private static final Set<String> permutations = new TreeSet<>();

    public static String solve(int[] arr) {
        Set<String> set1 = new TreeSet<>();
        Set<String> set2 = new TreeSet<>();
        for (int i : arr) {
            set1.add("" + i);
            for (char ch : ("" + i).toCharArray()) {
                set2.add("" + ch);
            }
        }
        System.out.println("Unique logins: " + set1);
        System.out.println("Unique characters: " + set2);
        String key = "";
        for (String s : set2) {
            key += s;
        }
        System.out.println("Minimum key: " + key);
        getPermutation("", key);
        for (String login : set1) {
            Iterator<String> it = permutations.iterator();
            while (it.hasNext()) {
                if (!isValid(it.next(), login)) {
                    it.remove();
                }
            }
        }
        return "Keys found: " + permutations;
    }

    private static boolean isValid(String p, String login) {
        int i = p.indexOf(login.charAt(0));
        int j = p.indexOf(login.charAt(1));
        int k = p.indexOf(login.charAt(2));
        return i < j && j < k;
    }

    private static void getPermutation(String pre, String str) {
        int n = str.length();
        if (n == 0) {
            permutations.add(pre);
        } else {
            for (int i = 0; i < n; i++) {
                getPermutation(pre + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }

    }

    public static void main(String[] args) {
        int arr[] = new int[]{
            319, 680, 180, 690, 129, 620, 762, 689, 762, 318,
            368, 710, 720, 710, 629, 168, 160, 689, 716, 731,
            736, 729, 316, 729, 729, 710, 769, 290, 719, 680,
            318, 389, 162, 289, 162, 718, 729, 319, 790, 680,
            890, 362, 319, 760, 316, 729, 380, 319, 728, 716};
        System.out.println(P079.solve(arr));
    }
}
