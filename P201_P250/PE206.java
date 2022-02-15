package P201_P250;

public class PE206 {

    public static void main(String[] args) {
        new PE206().solve("1_2_3_4_5_6_7_8_9_0");
    }

    void solve(String key) {
        long start = System.currentTimeMillis();
        long keyMin = Long.parseLong(key.replaceAll("_", "0")) / 100;
        long nMin = (long) Math.sqrt(keyMin);
        while (++nMin % 10 != 3) {
        }
        long n = nMin - 10;
        int i = 4;
        while (!matches(keyMin, n * n)) {
            n += i;
            i = 10 - i;
        }
        n *= 10;
        long end = System.currentTimeMillis();
        System.out.format("Found:%d%nMatching:%d%nElapsed:%dms%n", n, n * n, end - start);
    }

    boolean matches(long k, long n) {
        while (k > 0) {
            k /= 100;
            n /= 100;
            if (k % 10 != n % 10) {
                return false;
            }
        }
        return true;
    }

}
