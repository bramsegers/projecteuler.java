package P201_P250;

import java.util.ArrayList;
import java.util.List;

public class PE230 {

    public static void main(String[] args) {
        assert (new PE230().getD("1415926535", "8979323846", 35) == 9);
        new PE230().solve(0, 17);
    }

    void solve(int nMin, int nMax) {
        long start = System.currentTimeMillis();
        long sum = 0;
        String PI_1 = "1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
        String PI_2 = "8214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196";
        for (long n = nMin; n <= nMax; n++) {
            long i = (19 * n + 127) * (long) Math.pow(7, n);
            sum += (long) Math.pow(10, n) * getD(PI_1, PI_2, i);
        }
        long end = System.currentTimeMillis();
        System.out.format("S(%d,%d)=%d%nElapsed:%dms%n", nMin, nMax, sum, end - start);
    }

    int getD(String a, String b, long n) {
        List<Long> list = new ArrayList<>();
        long f1 = a.length();
        long f2 = b.length();
        long f3;
        list.add(f1);
        list.add(f2);
        while ((f3 = f1 + f2) < n) {
            list.add(f3);
            f1 = f2;
            f2 = f3;
        }
        int q = list.size();
        while (q > 1) {
            if (n > list.get(q - 2)) {
                n -= list.get(q - 2);
                q--;
            } else {
                q -= 2;
            }
        }
        return (q == 0 ? a : b).charAt((int) n - 1) - '0';
    }

}
