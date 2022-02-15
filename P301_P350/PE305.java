package P301_P350;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;

public class PE305 {

    public static void main(String[] args) {
        PE305 p = new PE305();
        //p.bruteforce(7780);
        long sum = 0;
        int pow = 1;
        for (int i = 1; i <= 13; i++) {
            pow *= 3;
            long f = p.solve(pow);
            System.out.format("f(%d)=%d%n%n", pow, f);
            sum += f;
        }
        System.out.println("sum=" + sum);
    }

    TreeSet<Long> pos;
    int size;

    long solve(int n) {
        pos = new TreeSet<>();
        size = n;
        String s = "" + n;
        int d = 0;
        while (pos.size() < n) {
            d++;
            for (int off = 0; off < d; off++) {
                String s2 = "";
                for (int i = 0; i <= s.length() - d - off; i += d) {
                    s2 += s.substring(i + off, i + off + d) + " ";
                }
                eval(s2.split(" "));
            }
            s = "_" + s + "_";
            System.out.println();
        }
        return pos.last();
    }

    void eval(String[] s) {
        String eval = "";
        if (!s[0].contains("_")) {
            eval = "A";
            long n = Long.parseLong(s[0]);
            if (evalAB(s, n)) {
                add(pos(n));
            }
        } else if (s.length > 1 && !s[1].contains("_")) {
            eval = "B";
            long n = Long.parseLong(s[1]) - 1;
            if (evalAB(s, n)) {
                add(pos(n) + leadingWhites(s[0]));
            }
        } else if (s.length == 1) {
            eval = "C";
            evalC(s[0], leadingWhites(s[0]));
        } else {
            eval = "D " + evalD(s[0], s[1]);
        }
        System.out.println(Arrays.toString(s) + " " + eval + " " + pos.size());
    }

    boolean add(long n) {
        pos.add(n);
        if (pos.size() > size) {
            if (pos.pollLast() == n) {
                return false;
            }
        }
        return true;
    }

    long pos(long n) {
        // a(n) = d*n + 1 - (10^d - 1)/9 
        // d = number of decimal digits in n
        // d = floor(log10(n)) + 1.
        long d = (long) Math.log10(n) + 1;
        return d * n + 1 - ((long) Math.pow(10, d) - 1) / 9;
    }

    int leadingWhites(String s) {
        int rv = -1;
        while (s.charAt(++rv) == '_') {
        }
        return rv;
    }

    int trailingWhites(String s) {
        int rv = 0;
        while (s.charAt(s.length() - (++rv)) == '_') {
        }
        return rv - 1;
    }

    boolean valid(String real, String comp) {
        if (real.length() != comp.length()) {
            return false;
        }
        for (int i = 0; i < real.length(); i++) {
            char r = real.charAt(i);
            char c = comp.charAt(i);
            if (c != r && c != '_') {
                return false;
            }
        }
        return true;
    }

    boolean evalAB(String[] s, long n) {
        for (int i = 0; i < s.length; i++) {
            if (!valid("" + (n + i), s[i])) {
                return false;
            }
        }
        return true;
    }

    boolean evalC(String s, long off) {

        long a = leadingWhites(s);
        if (a > 0) {
            long i = (long) Math.pow(10, a - 1);
            String num = s.replaceAll("_", "");
            for (long j = i; j < 10 * i; j++) {
                if (!evalC(j + num + s.substring((j + num).length()), off)) {
                    return false;
                }
            }
        }

        long b = trailingWhites(s);
        if (a == 0 && b > 0) {
            long c = Long.parseLong(s.replaceAll("_", "0"));
            long d = Long.parseLong(s.replaceAll("_", "9"));
            for (long i = c; i <= d; i++) {
                if (!evalC("" + i, off)) {
                    return false;
                }
            }
        }

        if (a == 0 && b == 0) {
            return add(pos(Long.parseLong(s)) + off);
        }
        return true;
    }

    String evalD(String s1, String s2) {
        if (s2.startsWith("0")) {
            return "sw0";
        }
        String num1 = s1.replaceAll("_", "");
        long n1 = Long.parseLong(num1) + 1;
        String num1b = "" + n1;
        if (num1b.length() > num1.length()) {
            num1b = num1b.substring(1);
        }
        String t = s2.substring(0, s2.length() - num1b.length()) + num1b;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) != '_' && s2.charAt(i) != t.charAt(i)) {
                return "no fit";
            }
        }

        int off = -s1.length() + leadingWhites(s1);
        int z = 0;
        String t2 = "";
        while (t.contains(t2 + "_")) {
            z++;
            t2 += "_";
        }
        if (z == 0) {
            long i = Long.parseLong(t);
            add(pos(i) + off);
        } else {
            String[] t3 = t.split(t2);
            long t4 = Long.parseLong(t3[0] + t2.replaceAll("_", "0"));
            long t5 = Long.parseLong(t3[0] + t2.replaceAll("_", "9"));
            for (long i = t4; i <= t5; i++) {
                String t6 = i + t3[1];
                long t7 = Long.parseLong(t6);
                if (!add(pos(t7) + off)) {
                    return t + " skipped";
                }
            }
        }
        return t;
    }

// <editor-fold defaultstate="collapsed" desc="bruteforce">
    LinkedList<Integer> //
            queue = new LinkedList<>(),
            needle = new LinkedList<>(),
            temp = new LinkedList<>();

    void bruteforce(int nd) {
        int count = 0;
        int pos = 1;
        int n = 0;
        needle.addAll(get(nd));
        while (count < nd) {
            queue.addAll(get(++n));
            int len = queue.size() - needle.size();
            for (int i = 0; i <= len; i++) {
                if (eq()) {
                    count++;
                    System.out.println(count + " " + pos);
                }
                queue.pop();
                pos++;
            }
        }
    }

    boolean eq() {
        for (int i = 0; i < needle.size(); i++) {
            if (!needle.get(i).equals(queue.get(i))) {
                return false;
            }
        }
        return true;
    }

    LinkedList get(int n) {
        temp.clear();
        while (n > 0) {
            temp.push(n % 10);
            n /= 10;
        }
        return temp;
    }
    String a = "";

// </editor-fold>
}
