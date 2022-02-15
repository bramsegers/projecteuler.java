package P151_P200;

public class PE174 {

    public static void main(String[] args) {
        new PE174().solve(10,1000000);
    }

    void solve(int range, int nMax) {

        int count = 0;
        int maxA = (nMax / 4) + 1;
        int[] tiles = new int[nMax + 1];
        int[] sum = new int[range + 1];

        for (int a = 3; a <= maxA; a++) {
            boolean more = true;
            for (int b = a - 2; b >= 1 && more; b = b - 2) {
                int c = (a * a) - b * b;
                more = c <= nMax;
                if (more) {
                    tiles[c]++;
                }
            }
        }

        for (int i = 1; i <= nMax; i++) {
            int t = tiles[i];
            if (t <= range) {
                sum[t]++;
            }
        }

        for (int i = 1; i <= range; i++) {
            System.out.format("N(%d)=%d%n", i, sum[i]);
            count += sum[i];
        }
        System.out.format("%n %d%n ∑ N(n) = %d%nn=1%n%n", range, count);
    }
}
