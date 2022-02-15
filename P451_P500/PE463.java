package P451_P500;
 
import java.util.HashMap;

public class PE463 {

    public static void main(String[] args) {
        new PE463().solve(8);
        new PE463().solve(100);
        new PE463().solve(450283905890997363L);
    }

    long mod = 1000000000L;
    HashMap<Long, Long> map = new HashMap<>();

    void solve(long n) {
        System.out.format("S(%d)=%d%n", n, a(n));
    }

    //OEIS - A239447
    long a(long n) {
        if (n < 3) {
            return n;
        }
        if (!map.containsKey(n)) {
            long a, mod4 = n & 3, m = n / 4;
            if (mod4 == 0) {
                a = 6 * a(2 * m) - 5 * a(m) - 3 * a(m - 1) - 1;
            } else if (mod4 == 1) {
                a = 2 * a(2 * m + 1) + 4 * a(2 * m) - 6 * a(m) - 2 * a(m - 1) - 1;
            } else if (mod4 == 2) {
                a = 3 * a(2 * m + 1) + 3 * a(2 * m) - 6 * a(m) - 2 * a(m - 1) - 1;
            } else {
                a = 6 * a(2 * m + 1) - 8 * a(m) - 1;
            }
            map.put(n, a % mod);
        }
        return map.get(n);
    }

}
