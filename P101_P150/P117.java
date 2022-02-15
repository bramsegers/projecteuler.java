package P101_P150;

/*
 * Using a combination of black square tiles and oblong tiles chosen from: 
 * red tiles measuring two units, green tiles measuring three units, and blue tiles 
 * measuring four units, it is possible to tile a row measuring five units in length 
 * in exactly fifteen different ways.
 * 
 * How many ways can a row measuring fifty units in length be tiled?
 */
public class P117 {

    public static long solve(int[] tiles, int nMax) {
        long sum = 0;
        count = new long[nMax + 1][tiles.length + 1];
        for (int i = 0; i <= nMax; i++) {
            sum += count(nMax - i, 0, tiles);
        }
        return sum;
    }

    private static long[][] count;

    private static long count(int pos, int tile, int[] tiles) {
        if (pos < 0) {
            return 0;
        } else if (pos == 0) {
            return 1;
        } else if (count[pos][tile] == 0) {
            long rv = 0;
            if (tile == 0) {
                for (int t = 0; t < tiles.length; t++) {
                    rv += count(pos - tiles[t], t + 1, tiles);
                }
            } else {
                for (int i = 0; i <= pos; i++) {
                    rv += count(pos - i, 0, tiles);
                }
            }
            count[pos][tile] = rv;
        }
        return count[pos][tile];
    }

    public static void main(String[] args) {
        System.out.println(P117.solve(new int[]{2, 3, 4}, 50));
    }
}
