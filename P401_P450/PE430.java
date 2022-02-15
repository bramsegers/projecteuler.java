package P401_P450;

// first post in thread, by epiclolz
public class PE430 {

    public static void main(String[] args) {
        new PE430().solve(1e10, 4000);
    }

    void solve(double N, double M) {

        double sum = 0, incr = 1, todo = 1;

        for (long k = 1; k <= N / 2; k++) {

            double a = (1D + k) / N;
            double b = (N - k) / N;
            double pk = a * a + b * b;

            todo = (N / 2) - k + 1;
            incr = Math.pow(1 - 2 * pk, M);
            sum += incr;

            if (incr * todo < 1e-3) {
                break;
            }

        }

        System.out.format("%.2f %n", sum + (N / 2));
    }

}
