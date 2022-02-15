package P001_P050;

/*
 If the numbers 1 to 5 are written out in words: one, two, three, four, five, 
 then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 If all the numbers from 1 to 1000 (one thousand) inclusive were 
 written out in words, how many letters would be used?

 NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and
 forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 
 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */
public class P017 {

    public static int solve(int num) {
        int sum = 0;
        for (int val = 1; val <= num; val++) {
            String s = toWord(val);
            int len = s.replaceAll(" ", "").replaceAll("-", "").length();
            System.out.println("(" + len + ") " + s);
            sum += len;
        }
        return sum;
    }

    private static String toWord(int val) {
        if (val < 100) {
            return toWord_nn(val);
        }
        if (val < 1000) {
            return toWord_nnn(val);
        }
        for (int v = 0; v < DENOM.length; v++) {
            int didx = v - 1;
            int dval = (int) Math.pow(1000, v);
            if (dval > val) {
                int mod = (int) Math.pow(1000, didx);
                int l = val / mod;
                int r = val - (l * mod);
                String ret = toWord_nnn(l) + " " + DENOM[didx];
                if (r > 0) {
                    ret += ", " + toWord(r);
                }
                return ret;
            }
        }
        return null;
    }
    private static final String[] TO19 = {
        "zero",
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
        "ten",
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };
    private static final String[] TENS = {
        "twenty",
        "thirty",
        "forty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety"
    };
    private static final String[] DENOM = {
        "",
        "thousand",
        "million",
        "billion",
        "trillion"};

    private static String toWord_nn(int val) {
        if (val < 20) {
            return TO19[val];
        }
        for (int v = 0; v < TENS.length; v++) {
            String dcap = TENS[v];
            int dval = 20 + 10 * v;
            if (dval + 10 > val) {
                if (val % 10 != 0) {
                    return dcap + "-" + TO19[val % 10];
                }
                return dcap;
            }
        }
        return null;
    }

    private static String toWord_nnn(int val) {
        String word = "";
        int rem = val / 100;
        int mod = val % 100;
        if (rem > 0) {
            word = TO19[rem] + " hundred";
            if (mod > 0) {
                word += " and ";
            }
        }
        if (mod > 0) {
            word += toWord_nn(mod);
        }
        return word;
    }

    public static void main(String[] args) {
        System.out.println(P017.solve(1000));
    }
}
