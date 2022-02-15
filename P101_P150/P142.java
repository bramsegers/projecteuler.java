package P101_P150;

/*
 * Find the smallest x + y + z with integers x > y > z > 0 such that 
 * x + y, x - y, x + z, x - z, y + z, y - z are all perfect squares.
 *
 * Info: http://www.mathblog.dk/project-euler-142-perfect-square-collection/
 */
public class P142 {

    public static void main(String[] args) {
        System.out.println(P142.solve());
    }

    public static long solve() {
        long result = 0;
        long a, b, c, d, e, f;
        boolean solved = false;
        for (long i = 4; !solved; i++) {
            a = i * i;
            for (long j = 3; j < i && !solved; j++) {
                c = j * j;
                f = a - c;
                if (f <= 0 || !isSquare(f)) {
                    continue;
                }
                long kstart = (j % 2 == 1) ? 1 : 2;
                for (long k = kstart; k < j; k += 2) {
                    d = k * k;
                    e = a - d;
                    b = c - e;
                    if (b <= 0 || e <= 0 || !isSquare(b) || !isSquare(e)) {
                        continue;
                    }
                    long x = (d + c) / 2;
                    long y = (e + f) / 2;
                    long z = (c - d) / 2;
                    result = x + y + z;
                    System.out.println("(x,y,z)=(" + x + "," + y + "," + z + ")");
                    solved = true;
                    break;
                }
            }
        }
        return result;
    }

    private static boolean isSquare(long n) {
        long s = (long) Math.sqrt(n);
        return s * s == n;
    }
}
