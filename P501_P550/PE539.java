package P501_P550;

public class PE539 {

    //sum=426334056
    //BUILD SUCCESSFUL (total time: 5 minutes 8 seconds)
    public static void main(String[] args) {
        new PE539().solve((long) 1e18, 987654321);
    }

    void solve(long N, long mod) {

        long sum = 9;
        long x1 = 2;
        long y1 = 1;
        long x2 = 8;
        long y2 = 2;
        long n = 6;
        long an = 2;

        while (n <= N) {
            System.out.println(n);
            sum += sum(true, n, n + x1 * y1, x1, an, an + y1, N, mod);
            sum += sum(true, n + x1 * y1, n + x1 * y1 + x2 / 2 * y2, x2 / 2, an + y1, an + y1 + y2, N, mod);
            sum += sum(true, n + x1 * y1 + x2 / 2 * y2, n + x1 * y1 + x2 * y2, x2 / 2, an + y1, an + y1 + y2, N, mod);
            sum %= mod;
            an += y1 + y2;
            n += x1 * y1 + x2 * y2;
            x1 *= 2;
            y1 *= 2;
            x2 *= 2;
            y2 *= 2;
        }
        System.out.format("S(%d) mod %d = %d %n", N, mod, sum);

    }

    long sum(boolean vert, long start, long end, long x, long y1, long y2, long N, long mod) {
        if (start > N) {
            return 0;
        }
        if (start == N) {
            return a(y1) % mod;
        }
        long rv = 0;
        if (end - 1 <= N) {
            for (long i = y1; i < y2; i++) {
                rv += (a(i) % mod) * (x % mod);
                rv %= mod;
            }
            return rv;
        }
        long mid = (start + end) / 2;
        if (vert) {
            if (mid - 1 <= N) {
                for (long i = y1; i < y2; i++) {
                    rv += (a(i) % mod) * ((x / 2) % mod);
                    rv %= mod;
                }
                rv += sum(!vert, mid, end, x / 2, y1, y2, N, mod);
                rv %= mod;
            } else {
                rv += sum(!vert, start, mid, x / 2, y1, y2, N, mod);
                rv %= mod;
            }
        } else if (mid - 1 <= N) {
            for (long i = y1; i < (y1 + y2) / 2; i++) {
                rv += (a(i) % mod) * (x % mod);
                rv %= mod;
            }
            rv += sum(!vert, mid, end, x, (y1 + y2) / 2, y2, N, mod);
            rv %= mod;
        } else {
            rv += sum(!vert, start, mid, x, y1, (y1 + y2) / 2, N, mod);
            rv %= mod;
        }
        return rv;
    }

    long a(long n) { //oeis A032911 + 1 
        return n < 3
                ? 2 * n
                : (n & 1) == 0
                        ? 4 * a((n >> 1) - 1)
                        : 4 * a((n >> 1)) - 2;
    }

}
