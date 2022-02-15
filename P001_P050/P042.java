package P001_P050;
 
import java.util.HashSet;
import java.util.Set;
import util.Util;

/*
 The nth term of the sequence of triangle numbers is given by, t(n) = ½n(n+1); 
 so the first ten triangle numbers are:
 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

 By converting each letter in a word to a number corresponding to its 
 alphabetical position and adding these values we form a word value. 
 For example, the word value for SKY is 19 + 11 + 25 = 55 = t(10). 
 If the word value is a triangle number then we shall call the word a triangle word.

 Using P042.txt, a 16K text file containing nearly two-thousand 
 common English words, how many are triangle words?
 */
public class P042 {

    public static int solve(String[] words) {
        int triWords = 0;
        Set<Integer> triNums = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            triNums.add((i + 1) * i / 2);
        }
        for (String word : words) {
            int sum = 0;
            for (char ch : word.toCharArray()) {
                sum += ch - 'A' + 1;
            }
            if (triNums.contains(sum)) {
                System.out.println(word + " - " + sum);
                triWords++;
            }
        }
        return triWords;
    }

    public static void main(String[] args) {
        String text = Util.readText("files/P042.txt").get(0);
        String[] words = text.replaceAll("\"", "").toUpperCase().split(",");
        System.out.println(P042.solve(words));
    }
}
