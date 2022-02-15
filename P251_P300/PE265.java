package P251_P300;

public class PE265 {

    public static void main(String[] args) {
        new PE265().solve(5);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long sum = solve(1, 0, 0, 1 << nMax, nMax);
        long end = System.currentTimeMillis();
        System.out.format("S(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }

    long solve(int add, long built, long occ, int todo, int nMax) {
        built = (built << 1) + add;
        long curr = built & ((1L << nMax) - 1);
        if (((occ >> curr) & 1) == 1) {
            return 0;
        } else {
            if (--todo == 0) {
                System.out.println(Long.toBinaryString(built >> nMax));
                return built >> nMax;
            } else {
                occ += (1L << curr);
                return solve(0, built, occ, todo, nMax) + solve(1, built, occ, todo, nMax);
            }
        }
    }
}
