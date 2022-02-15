package P051_P100;

import java.util.List;
import util.Util;

/*
 * Comparing two numbers written in index form like 2^11 and 3^7 is not 
 * difficult, as any calculator would confirm that 2^11 = 2048 < 3^7 = 2187.
 * 
 * However, confirming that 632382^518061 > 519432^525806 would be much more 
 * difficult, as both numbers contain over three million digits.
 * 
 * Using P099.txt, determine which line number has the greatest numerical value.
 */
public class P099 {

    public static int solve(List<String> list) {
        list.add(0, "0,1");
        int maxLine = 0;
        double maxBase = 0;
        double maxExp = 0;
        for (int i = 1; i < list.size(); i++) {
            double base = Double.parseDouble(list.get(i).split(",")[0]);
            double exp = Double.parseDouble(list.get(i).split(",")[1]);
            double base2 = Math.pow(maxBase, maxExp / exp);
            System.out.println((i + ": " + list.get(i)
                    + (base > base2 ? " > " : " < ")
                    + list.get(maxLine)).replaceAll(",", "^"));
            maxBase = base > base2 ? base : maxBase;
            maxExp = base > base2 ? exp : maxExp;
            maxLine = base > base2 ? i : maxLine;
        }
        return maxLine;
    }

    public static void main(String[] args) {
        List<String> list = Util.readText("files/P099.txt");
        System.out.println(P099.solve(list));
    }
}
