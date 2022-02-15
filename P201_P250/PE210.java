package P201_P250;

public class PE210 {

    public static void main(String[] args) {
        new PE210().solve(1000000000);
    }

    void solve(long r) {
        long n = 3 * r * r / 2;
        long x = 1;
        long ySq;
        while ((ySq = (r * r / 32) - (x * x)) >= 0) {
            long y = (long) Math.sqrt(ySq);
            if (y * y > ySq) { // correct rounding error
                y--;
            }
            n += 4 * y;
            n += (y * y == ySq) ? 0 : 4;
            x++;
        }
        n -= (r / 4) - 2;
        System.out.format("P(%d)=%d%n", r, n);
    }

}
