package P551_P600;

public class PE571 {

    public static void main(String[] args) {
        new PE571().solve(12, 10);
    }

    int base, search;
    long count, found, sum;
    String alfa = "0123456789abcdefghijklmnopqrstuvwxyz";

    void solve(int b, int s) {
        base = b;
        search = s;
        solve("", alfa.substring(0, base));
    }

    void solve(String p, String s) {
        if (s.isEmpty()) {
            if (++count % 1000000 == 0) {
                System.out.println(count);
            }
            long n = Long.parseLong(p, base);
            if (isSuperPan(n)) {
                showSuperPan(n);
                sum += n;
                found++;
                System.out.format("found:%d sum=%d %n", found, sum);
                if (found == search) {
                    System.exit(0);
                }
            }
        }
        for (int i = p.isEmpty() ? 1 : 0; i < s.length(); i++) {
            solve(p + s.charAt(i), s.substring(0, i) + s.substring(i + 1));
        }
    }

    boolean isSuperPan(long n) {
        for (int b = base - 1; b >= 2; b--) {
            String test = Long.toString(n, b);
            for (int i = 0; i < b; i++) {
                String t = alfa.substring(i, i + 1);
                if (!test.contains(t)) {
                    return false;
                }
            }
        }
        return true;
    }

    void showSuperPan(long n) {
        System.out.println("*" + n);
        for (int b = base; b >= 2; b--) {
            System.out.println(Long.toString(n, b));
        }
    }

}
