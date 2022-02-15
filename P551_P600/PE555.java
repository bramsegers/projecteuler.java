package P551_P600;

import java.util.ArrayList;
import java.util.List;

public class PE555 {

    public static void main(String[] args) {
        //new PE().test(10); //pe555.xlsx
        new PE555().solve(1000000);
    }

    void solve(long pm) {
        long sum = 0;
        for (long len = 1; len <= pm / 2; len++) {
            long q = pm / len - 1;
            long r = pm - len * q;
            sum += pm * (pm + 1) / 2;
            sum -= r * (r + 1) / 2;
        }
        System.out.println(sum);
    }

    void test(int pm) {
        for (int k = 2; k <= pm; k++) {
            for (int s = 1; s < k; s++) {
                List<Integer> list = new ArrayList<>();
                for (int n = 1; n <= 150000; n++) {
                    int M = mc(n, pm, k, s);
                    if (n == M) {
                        list.add(n);
                    }
                }
                if (!list.isEmpty()) {
                    int sum = list.stream().mapToInt(Integer::intValue).sum();
                    System.out.println(k + "_" + s + "_" + list.size() + "_" + sum + "_" + list);
                }
            }
        }
    }

    int mc(int n, int m, int k, int s) {
        return n > m
                ? n - s
                : mc(mc(n + k, m, k, s), m, k, s);
    }

    int mc91(int n) {
        int c = 1;
        while (c != 0) {
            if (n > 100) {
                n = n - 10;
                c--;
            } else {
                n = n + 11;
                c++;
            }
        }
        return n;
    }

    int mc91rec(int n) {
        return n > 100
                ? n - 10
                : mc91rec(mc91rec(n + 11));
    }

}
