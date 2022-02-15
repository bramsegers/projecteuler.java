package P301_P350;

public class PE348 {

    public static void main(String[] args) {
        new PE348().solve(5);
    }

    int todo;
    long sum;

    void solve(int nMax) {
        int d = 0;
        todo = nMax;
        while (todo > 0) {
            getPalindromes(++d);
        }
        System.out.format("P(%d)=%d%n", nMax,sum);
    }

    void getPalindromes(int d) {
        int n = (d + 1) / 2;
        long m = (long) Math.pow(10, n);
        for (long i = m / 10; i < m && todo > 0; i++) {
            long t1 = 0;
            long t2 = i;
            long t3 = d % 2 == 0 ? i : i / 10;
            while (t3 > 0) {
                t1 *= 10;
                t1 += t3 % 10;
                t2 *= 10;
                t3 /= 10;
            }
            testSquareCube(t1 + t2);
        }
    }

    void testSquareCube(long n) {
        String str = "";
        long count = 0, cube = 0;
        for (long c = 1; (cube = c * c * c) < n; c++) {
            long square = n - cube;
            long s = (long) Math.sqrt(square);
            if (s * s == square) {
                str += String.format("%d = %d^2 + %d^3%n", n, s, c);
                count++;
            }
        }
        if (count == 4) {
            todo--;
            sum += n;
            System.out.println(str);
        }
    }

}
