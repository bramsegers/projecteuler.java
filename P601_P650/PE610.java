package P601_P650;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PE610 {                                                            //@author brse

    public static void main(String[] args) {
        new PE610().solve();
    }

    char[] ROMANS = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    int[] VALUES = {1, 5, 10, 50, 100, 500, 1000}; 

    Map<String, Integer> MAP = new HashMap<>();                                 //maps valid, minimal roman to decimal value
    Map<Integer, Set<String>> PRE = new HashMap<>();                            //maps path length to all valid, minimal romans

    public void solve() {
        for (int n = 1; n <= 1000; n++) {                                       //generate all roman numerals up to 1000 (M)
            String r = roman(n);
            MAP.put(r, n);                                                      //populate map
            for (int i = 0; i <= r.length(); i++) {                             //populate pre
                Set<String> set = PRE.get(i);
                if (set == null) {
                    set = new TreeSet<>();
                    PRE.put(i, set);
                }
                set.add(r.substring(0, i));
            }
        }
        double exp = solve(1, "");                                              //solve recursively
        System.out.format("SUM=%.8f%n", exp);
    }

    double solve(double p, String path) {                                       //recursively evaluate all paths < 1000 (M)
        if (path.startsWith("M")) {
            return 0;
        }
        List<Character> valid = new ArrayList<>();                              //list all valid successors for current path
        for (char ch : ROMANS) {
            if (valid(path + ch)) {
                valid.add(ch);
            }
        }
        int q = 1 + 7 * valid.size();
        double exp = eval(path, p / q);                                         //expected value for current path

        for (char ch : valid) {                                                 //expected value for successive paths
            exp += solve(p * 7 / q, path + ch);
        }
        return exp;
    }

    double eval(String path, double p) {                                        //add 1000 while substantial
        double exp = 0;
        String m = "";
        int v = (MAP.get(path) == null) ? 0 : MAP.get(path);
        while (p > 1e-20) {
            System.out.format("%s %d %.20f %.20f %n", m + path, v, p, v * p);   //ouput current value
            exp += p * v;
            p *= 7.0 / 50;
            m += "M";
            v += 1000;
        }
        return exp;
    }

    boolean valid(String path) {                                                //check if path is valid
        if (PRE.get(path.length()) == null) {
            return false;
        }
        return PRE.get(path.length()).contains(path);
    }

    String roman(int n) {                                                       //convert number to roman numeral in valid, minimal represenation
        String roman = "";
        while (n > 0) {
            for (int i = ROMANS.length - 1; i >= 0; i--) {
                if (n >= VALUES[i]) {
                    n -= VALUES[i];
                    roman += ROMANS[i];
                    break;
                }
            }
        }
        return roman
                .replace("VIIII", "IX")
                .replace("IIII", "IV")
                .replace("LXXXX", "XC")
                .replace("XXXX", "XL")
                .replace("DCCCC", "CM")
                .replace("CCCC", "CD");
    }

}
