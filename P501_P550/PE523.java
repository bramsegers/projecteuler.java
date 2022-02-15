package P501_P550;

import java.util.ArrayList;
import java.util.List;

// https://oeis.org/A279683
public class PE523 {

    public static void main(String[] args) {
        new PE523().test(10);
        new PE523().solve(30);
    }

    void solve(int n) {
        double a = 0, f = 1, p = 1;
        for (int i = 1; i <= n; i++) {
            a = a * i + f * (p - 1);
            f *= i;
            p *= 2;
            System.out.format("%d %.2f %n", i, a / f); 
        }
    }

    void test(int lim) {
        for (int n = 1; n <= lim; n++) {
            int f = 1;
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                list.add(i);
                f *= i;
            }
            int sum = perm(new ArrayList<>(), list);
            double exp = 1D * sum / f;
            System.out.format("%2d %10d %10f %n", n, sum, exp);
        }
    }

    int perm(List<Integer> done, List<Integer> todo) {
        if (todo.isEmpty()) {
            return calc(0, done);
        } else {
            int rv = 0;
            for (Integer i : todo) {
                List<Integer> done2 = new ArrayList<>(done);
                List<Integer> todo2 = new ArrayList<>(todo);
                done2.add(i);
                todo2.remove(i);
                rv += perm(done2, todo2);
            }
            return rv;
        }
    }

    int calc(int sum, List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                Integer j = list.remove(i);
                list.add(0, j);
                return calc(sum + 1, list);
            }
        }
        return sum;
    }

}

