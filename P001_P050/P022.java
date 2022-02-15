package P001_P050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import util.Util;

/*
 Using P022.txt, a 46K text file containing over five-thousand first names, 
 begin by sorting it into alphabetical order. Then working out the alphabetical 
 value for each name, multiply this value by its alphabetical position 
 in the list to obtain a name score.

 For example, when the list is sorted into alphabetical order, COLIN, which 
 is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. 
 So, COLIN would obtain a score of 938 x 53 = 49714.

 What is the total of all the name scores in the file?
 */
public class P022 {

    public static int solve(String input) {
        String[] lines = input.replaceAll("\"", "").split(",");
        List<String> names = new ArrayList<>(Arrays.asList(lines));
        Collections.sort(names);
        int sum = 0;
        int pos = 0;
        for (String name : names) {
            int score = 0;
            for (char ch : name.toCharArray()) {
                score += ch - 'A' + 1;
            }
            score *= ++pos;
            sum += score;
            System.out.format("%-10s %8d%n", name, score);
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> lines = Util.readText("files/P022.txt");
        System.out.println(P022.solve(lines.get(0)));
    }
}
