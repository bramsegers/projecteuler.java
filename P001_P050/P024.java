package P001_P050;

/*
 A permutation is an ordered arrangement of objects. For example, 3124 is one 
 possible permutation of the digits 1, 2, 3 and 4. If all of the permutations 
 are listed numerically or alphabetically, we call it lexicographic order. 
 
 The lexicographic permutations of 0, 1 and 2 are:
 012   021   102   120   201   210

 What is the millionth lexicographic permutation of the digits 
 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class P024 {

    static int done;
    static String permutation;

    public static String solve(String s, int num) {
        permutation("", s, num);
        return permutation;
    }

    private static void permutation(String pre, String s, int todo) {
        if (s.length() == 0 && ++done == todo) {
            permutation = pre;
        } else if (done < todo) {
            for (int i = 0; i < s.length(); i++) {
                permutation(pre + s.charAt(i), s.substring(0, i) + s.substring(i + 1), todo);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(P024.solve("0123456789", 1000000));
    }
}
