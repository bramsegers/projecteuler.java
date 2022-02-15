package P051_P100;

import java.util.List;
import util.Util;

/*
 * The rules for writing Roman numerals allow for many ways of writing each number. 
 * However, there is always a "best" way of writing a particular number.
 * For example, the following represent all of the legitimate ways of writing the number sixteen:
 * IIIIIIIIIIIIIIII
 * VIIIIIIIIIII
 * VVIIIIII
 * XIIIIII
 * VVVI
 * XVI
 * The last example being considered the most efficient, as it uses the least 
 * number of numerals. The text file P089.txt contains one thousand numbers 
 * written in valid, but not necessarily minimal, Roman numerals. That is, they 
 * are arranged in descending units and obey the subtractive pair rule.
 * 
 * Find the number of characters saved by writing each of these in their minimal form.
 * Note: You can assume that all the Roman numerals in the file contain no more 
 * than four consecutive identical units.
 * 
 * Substractive pair rule:
 * Only I, X, and C can be used as the leading numeral in part of a subtractive pair.
 * I can only be placed before V and X.
 * X can only be placed before L and C.
 * C can only be placed before D and M.
 */
public class P089 {

    public static int solve(List<String> lines) {
        int sum = 0;
        String nums = ("I = 1   V = 5   X = 10  L = 50  C = 100 D = 500 M = 1000");
        String convert;
        String out;
        for (String line : lines) {
            convert = line;
            convert = convert.replaceAll("IIIIV", "I");
            convert = convert.replaceAll("IIIV", "II");
            convert = convert.replaceAll("IIV", "III");
            convert = convert.replaceAll("IV", "IIII");

            convert = convert.replaceAll("IIIIX", "VI");
            convert = convert.replaceAll("IIIX", "VII");
            convert = convert.replaceAll("IIX", "VIII");
            convert = convert.replaceAll("IX", "VIIII");

            convert = convert.replaceAll("XXXXL", "X");
            convert = convert.replaceAll("XXXL", "XX");
            convert = convert.replaceAll("XXL", "XXX");
            convert = convert.replaceAll("XL", "XXXX");

            convert = convert.replaceAll("XXXXC", "LX");
            convert = convert.replaceAll("XXXC", "LXX");
            convert = convert.replaceAll("XXC", "LXXX");
            convert = convert.replaceAll("XC", "LXXXX");

            convert = convert.replaceAll("CCCCD", "C");
            convert = convert.replaceAll("CCCD", "CC");
            convert = convert.replaceAll("CCD", "CCC");
            convert = convert.replaceAll("CD", "CCCC");

            convert = convert.replaceAll("CCCCM", "DC");
            convert = convert.replaceAll("CCCM", "DCC");
            convert = convert.replaceAll("CCM", "DCCC");
            convert = convert.replaceAll("CM", "DCCCC");

            out = convert;
            out = out.replaceAll("VIIII", "IX");
            out = out.replaceAll("IIII", "IV");
            out = out.replaceAll("LXXXX", "XC");
            out = out.replaceAll("XXXX", "XL");
            out = out.replaceAll("DCCCC", "CM");
            out = out.replaceAll("CCCC", "CD");

            int saved = line.length() - out.length();
            sum += saved;

            System.out.println(nums);
            System.out.println("Original:  " + line);
            System.out.println("Converted: " + convert);
            System.out.println("Minimal:   " + out);
            System.out.println("Saved:     " + saved + "\n");
        }
        return sum;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P089.txt");
        System.out.println(P089.solve(list));
    }
}
