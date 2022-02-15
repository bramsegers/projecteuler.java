package P301_P350;

public class PE327 {

    public static void main(String[] args) {
        new PE327().solve(3, 40, 30);
        new PE327().solve2(3, 40, 30);
    }

    void solve(int cMin, int cMax, int R) {
        long sum = 0;
        for (int C = cMin; C <= cMax; C++) {
            sum += M(C, R);
        }
        System.out.println(sum);
    }

    long M(long C, long R) {
        if (C > R) {
            return R + 1;
        }
        double cx = C - 2;
        for (long i = C; i <= R; i++) {
            C = (long) (2 * Math.ceil((double) (C - 1) / cx) + C - 1);
        }
        return C;
    }

    void solve2(int cMin, int cMax, int R) {
        long sum = 0;
        for (int C = cMin; C <= cMax; C++) {
            sum += M2(C, R);
        }
        System.out.println(sum);
    }

    long M2(int C, int R) {
        long lo = 1, hi = Long.MAX_VALUE - lo;
        while (lo < hi) {
            long mid = (lo + hi) >> 1;
            if (can(mid, C, R)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    boolean can(long cards, int C, int R) {
        if (cards == 0) {
            return false;
        } else if (cards <= C) {
            return cards >= R + 1;
        } else if (cards % C >= 2) {
            return can(cards / C * (C - 2) + cards % C - 1, C, R - 1);
        } else {
            return can((cards - C) / C * (C - 2) + C - 1, C, R - 1);
        }
    }
}
