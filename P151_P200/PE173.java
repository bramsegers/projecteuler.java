package P151_P200;

public class PE173 {

    public static void main(String[] args) {
        new PE173().solve(1000000);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();
        long sum = 0;
        int maxA = (nMax / 4) + 1;
        for (int a = 3; a <= maxA; a++) {
            boolean more = true;
            for (int b = a - 2; b >= 1 && more; b = b - 2) {
                int c = (a * a) - b * b;
                more = c <= nMax;
                sum += more ? 1 : 0;
            }
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }
}
