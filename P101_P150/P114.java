package P101_P150;

/*
 * A row measuring seven units in length has red blocks with a minimum length of 
 * three units placed on it, such that any two red blocks (which are allowed to be 
 * different lengths) are separated by at least one black square. There are exactly 
 * seventeen ways of doing this.
 * 
 * How many ways can a row measuring fifty units in length be filled?
 * NOTE: Although the example above does not lend itself to the possibility, 
 * in general it is permitted to mix block sizes. For example, on a row measuring 
 * eight units in length you could use red (3), black (1), and red (4).
 */
public class P114 {

    public static long solve(int nMax) {
        long sum = 0;
        count = new long[nMax][2];
        for (int i = 1; i <= nMax; i++) {
            sum += count(nMax - i, 0);
        }
        for (int i = 3; i <= nMax; i++) {
            sum += count(nMax - i, 1);
        }
        return sum;
    }

    private static long[][] count;

    private static long count(int pos, int br) {
        if (pos < 0) {
            return 0;
        } else if (pos == 0) {
            return 1;
        } else if (count[pos][br] == 0) {
            long rv = 0;
            if (br == 1) {
                for (int i = 1; i <= pos; i++) {
                    rv += count(pos - i, 0);
                }
            } else {
                for (int i = 3; i <= pos; i++) {
                    rv += count(pos - i, 1);
                }
            }
            count[pos][br] = rv;
        }
        return count[pos][br];
    }

    public static void main(String[] args) {
        System.out.println(P114.solve(50));
    }
}
