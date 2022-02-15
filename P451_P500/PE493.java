package P451_P500;

import java.util.Arrays;
import java.util.stream.IntStream;
import util.Util;

/*
 * http://www.math.uiuc.edu/~hildebr/408/combinatorialproblemssol.pdf
 *
 * Section:The classical urn/ball problem
 */
public class PE493 {

    int[] qty = {10, 10, 10, 10, 10, 10, 10};
    int pick = 20;

    void solve() {
        double num = solve(0, pick, new int[qty.length]);
        double denom = Util.choose(IntStream.of(qty).sum(), pick);
        System.out.format("%.9f%n", num / denom);

    }

    long solve(int col, int left, int[] parts) {
        long rv = 0;
        if (left == 0) {
            rv += total(parts);
        } else if (col < parts.length) {
            for (int i = 0; i <= Math.min(qty[col], left); i++) {
                int[] newparts = Arrays.copyOf(parts, parts.length);
                newparts[col] = i;
                rv += solve(col + 1, left - i, newparts);
            }
        }
        return rv;
    }

    long total(int[] parts) {
        long rv = 1;
        int distinct = 0;
        for (int i = 0; i < parts.length; i++) {
            distinct += parts[i] > 0 ? 1 : 0;
            int n = qty[i];
            int r = parts[i];
            rv *= Util.choose(n, r);
        }
        rv *= distinct;
        return rv;
    }

    public static void main(String[] args) {
        new PE493().solve();
    }

}
