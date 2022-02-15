package P301_P350;

public class PE307 {

    public static void main(String[] args) {
        new PE307().solve(3, 7);
        new PE307().solve(20000, 1000000);
    }

    double[][] p;

    void solve(int k, int n) {
        p = new double[k + 1][k / 2 + 1];
        System.out.format("P(%d,%d)=%.10f%n", k, n, 1 - solve(0, 0, k, n));
    }

    double solve(int ones, int twos, int k, int n) {
        if (ones < 0) {
            return 0;
        } else if (ones + 2 * twos == k) {
            return 1;
        } else if (p[ones][twos] == 0) {
            p[ones][twos] = (n - ones - twos) * solve(ones + 1, twos, k, n) / n;
            p[ones][twos] += ones * solve(ones - 1, twos + 1, k, n) / n;
        }
        return p[ones][twos];
    }

}
