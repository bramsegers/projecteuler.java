package P101_P150;

/*
 * A row of five black square tiles is to have a number of its tiles replaced with 
 * coloured oblong tiles chosen from red (length two), green (length three), or blue 
 * (length four). If red tiles are chosen there are exactly seven ways this can be done.
 * If green tiles are chosen there are three ways. And if blue tiles are chosen there 
 * are two ways.
 * 
 * Assuming that colours cannot be mixed there are 7 + 3 + 2 = 12 ways of replacing 
 * the black tiles in a row measuring five units in length.
 * 
 * How many different ways can the black tiles in a row measuring fifty units in length 
 * be replaced if colours cannot be mixed and at least one coloured tile must be used?
 */
public class P116 {

    public static long solve(int[] tiles, int nMax) {
        long sum = 0;
        for (int t : tiles) {
            count = new long[nMax + 1][2];
            for (int i = 0; i <= nMax - t; i++) {
                sum += count(nMax - i, 0, t);
            }
        }
        return sum;
    }

    private static long[][] count;

    private static long count(int pos, int br, int t) {
        if (pos < 0) {
            return 0;
        } else if (pos == 0) {
            return 1;
        } else if (count[pos][br] == 0) {
            long rv = 0;
            if (br == 1) {
                for (int i = 0; i <= pos; i++) {
                    rv += count(pos - i, 0, t);
                }
            } else {
                rv += count(pos - t, 1, t);
            }
            count[pos][br] = rv;
        }
        return count[pos][br];
    }

    public static void main(String[] args) {
        System.out.println(P116.solve(new int[]{2, 3, 4}, 50));
    }
}
