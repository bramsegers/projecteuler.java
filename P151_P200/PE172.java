package P151_P200;

public class PE172 {

    public static void main(String[] args) {
        //new PE172b().bruteForce(7);
        new PE172().solve(18);
    }

    int nMax;
    long[] fac;

    void solve(int limit) {
        long start = System.currentTimeMillis();
        long count = 0;
        nMax = limit;
        fac = new long[nMax];
        fac[0] = 1;
        for (int i = 1; i < nMax; i++) {
            fac[i] = i * fac[i - 1];
        }
        for (int i = 3; i >= 0; i--) {
            //count += count(9, nMax - i, i);
        }
        count += count(10, nMax,0);
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, count, end - start);
        System.out.println("P(18)=227485267000992000");
    }

    long count(int num, int len, int path) {
        if (num < 0 || len < 0) {
            return 0;
        } else if (len == 0) {
            return count(path << (2 * num), nMax);
        } else {
            long rv = 0;
            for (int i = 3; i >= 0; i--) {
                rv += count(num - 1, len - i, (path << 2) + i);
            }
            return rv;
        }
    }

    long count(int path, int nMax) {
        long rv = fac[nMax - 1];
        rv *= nMax - (path & 3);
        while (path > 0) {
            rv /= fac[path & 3];
            path >>= 2;
        }
        return rv;
    }

    void bruteForce(int nMax) {
        long start = System.currentTimeMillis();
        long count = 0;
        for (int n = (int) Math.pow(10, nMax - 1); n < (int) Math.pow(10, nMax); n++) {
            count += isOk(n) ? 1 : 0;
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, count, end - start);
    }

    boolean isOk(int n) {
        String s = String.valueOf(n);
        int[] c = new int[10];
        for (char ch : s.toCharArray()) {
            c[ch - '0']++;
        }
        for (int i : c) {
            if (i > 3) {
                return false;
            }
        }
        return true;
    }

}
