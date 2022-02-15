package P251_P300;

public class PE277 {

    public static void main(String[] args) {
      new PE277().solve("UDDDUdddDDUDDddDdDddDDUDDdUUDd", 1E15);
    }

    String collatz;

    void solve(String c, double min) {
        collatz = c;
        long an = 0, a1 = 0;
        while (a1 < min) {
            a1 = solve(++an);
            if (a1 > 0) {
                System.out.format(
                        "a%d=%d,a1=%d%n",
                        collatz.length() + 1,
                        an,
                        a1);
            }
        }
    }

    long solve(long n) {
        for (int i = collatz.length() - 1; i >= 0; i--) {
            char step = collatz.charAt(i);

            if (step == 'D') {
                n = 3 * n;

            } else if (step == 'U') {
                n = 3 * n - 2;
                if (n % 4 != 0) {
                    return -1;
                }
                n /= 4;

            } else if (step == 'd') {
                n = 3 * n + 1;
                if (n % 2 != 0) {
                    return -1;
                }
                n /= 2;
            }
        }
        return n;
    }

}
