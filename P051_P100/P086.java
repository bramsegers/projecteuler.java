package P051_P100;

/*
 A spider S sits in one corner of a cuboid room, measuring 6 by 5 by 3, and a 
 fly F sits in the opposite corner. By travelling on the surfaces of the room 
 the shortest "straight line" distance from S to F is 10.
 
 However, there are up to three "shortest" path candidates for any given cuboid 
 and the shortest route doesn't always have integer length. By considering all 
 cuboid rooms with integer dimensions, up to a maximum size of M by M by M, 
 there are exactly 2060 cuboids for which the shortest route has integer length 
 when M=100, and this is the least value of M for which the number of solutions 
 first exceeds two thousand; the number of solutions is 1975 when M=99.

 Find the least value of M such that the number of solutions first exceeds one million.
 */
public class P086 {

    public static int solve(int start, int nrCuboids) {
        int i = start;
        while (cuboids(i, i, i) <= nrCuboids) {
            i++;
        }
        return i;
    }

    private static int cuboids(int x, int y, int z) {
        int sum = 0;
        for (int k = 1; k <= z; k++) {
            for (int j = k; j <= y; j++) {
                for (int i = j; i <= x; i++) {
                    if (shortestPathIsInteger(i, j, k)) {
                        sum++;
                        //System.out.println(i + " " + j + " " + k);
                    }
                }
            }
        }
        System.out.println(x + ": " + sum);
        return sum;
    }

    private static boolean shortestPathIsInteger(int i, int j, int k) {
        int len1 = (i * i) + ((j + k) * (j + k));
        int len2 = (j * j) + ((i + k) * (i + k));
        int len3 = (k * k) + ((i + j) * (i + j));
        int sqr = (len1 <= len2 && len1 <= len3) ? len1 : ((len2 <= len1 && len2 <= len3) ? len2 : len3);
        int sqrt = (int) Math.sqrt(sqr);
        return sqrt * sqrt == sqr;
    }

    public static int solve2(int nMax) {
        int sum = 0;//1975;
        int n = 0;//99;
        while (sum < nMax) {
            n++;
            for (int i = 1; i < 2 * n; i++) {
                int sq = i * i + n * n;
                int sqrt = (int) Math.sqrt(sq);
                if (sqrt * sqrt == sq) {
                    sum += i < n ? i / 2 : i / 2 - (i - n - 1);
                }
            }
            System.out.println(n + ": " + sum);
        }
        return n;
    }

    public static void main(String[] args) {
        //System.out.println(P086.solve(1815, 1000000));
        System.out.println(P086.solve2(1000000));
    }
}
