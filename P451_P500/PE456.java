package P451_P500;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PE456 {

    public static void main(String[] args) {
        new PE456().solve(8);
        new PE456().solve(600);
        new PE456().solve(40000);
        new PE456().solve(2000000);
    }

    List<Double>[] arr = new ArrayList[9];

    void solve(int N) {

        for (int i = 0; i < 9; i++) {
            arr[i] = new ArrayList<>();
        }

        long[] A = {1248, 32323, 16161};
        long[] B = {8421, 30103, 15051};
        long x, y, px = 1, py = 1;

        for (int n = 0; n < N; n++) {

            px *= A[0];
            py *= B[0];

            px %= A[1];
            py %= B[1];

            x = px - A[2];
            y = py - B[2];

            int i = 0;
            i += y < 0 ? 6 : (y == 0 ? 3 : 0);
            i += x < 0 ? 0 : (x == 0 ? 1 : 2);
            arr[i].add(Math.abs(1D * y / x));
        }

        for (int i = 0; i < 9; i++) {
            Collections.sort(arr[i]);
        }

        long sum = 0;
        sum += multsize(0, 5, 7);
        sum += multsize(1, 3, 8);
        sum += multsize(1, 5, 6);
        sum += multsize(2, 3, 7);

        sum += multsize(0, 2, 7);
        sum += multsize(0, 5, 6);
        sum += multsize(1, 6, 8);
        sum += multsize(2, 3, 8);

        sum += addsize(0, 1, 3) * calc1(6, 2);
        sum += addsize(1, 2, 5) * calc1(8, 0);
        sum += addsize(3, 6, 7) * calc1(0, 8);
        sum += addsize(5, 7, 8) * calc1(2, 6);

        sum += calc2(0, 8);
        sum += calc2(2, 6);
        sum += calc2(8, 0);
        sum += calc2(6, 2);

        System.out.format("C(%d)=%d%n", N, sum);
    }

    long addsize(int a, int b, int c) {
        return 0L + arr[a].size() + arr[b].size() + arr[c].size();
    }

    long multsize(int a, int b, int c) {
        return 1L * arr[a].size() * arr[b].size() * arr[c].size();
    }

    long calc1(int a, int b) {
        long sum = 0;
        for (double d : arr[a]) {
            sum += lower(d, b) + 1;
        }
        return sum;
    }

    long calc2(int a, int b) {
        long sum = 0;
        for (double d : arr[a]) {
            long i = arr[b].size() - higher(d, b);
            long j = lower(d, b) + 1;
            sum += i * j;
        }
        return sum;
    }

    long lower(double d, int b) {
        return d <= arr[b].get(0)
                ? -1
                : d > arr[b].get(arr[b].size() - 1)
                ? arr[b].size() - 1
                : lower(d, b, 0, arr[b].size() - 1);
    }

    long lower(double d, int b, int f, int t) {
        int m = (f + t) / 2;
        double e = arr[b].get(m);
        return t - f < 2
                ? f
                : e < d
                        ? lower(d, b, m, t)
                        : lower(d, b, f, m);
    }

    long higher(double d, int b) {
        return d >= arr[b].get(arr[b].size() - 1)
                ? arr[b].size()
                : d < arr[b].get(0)
                ? 0
                : higher(d, b, 0, arr[b].size() - 1);
    }

    long higher(double d, int b, int f, int t) {
        int m = (f + t) / 2;
        double e = arr[b].get(m);
        return t - f < 2
                ? t
                : e > d
                        ? higher(d, b, f, m)
                        : higher(d, b, m, t);
    }

}
