package P301_P350;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.Primes;

public class PE333 {

    public static void main(String[] args) {
        new PE333().solve(1000000);
    }

    Primes primes;
    int maxp;
    int[] P;

    void solve(int p) {

        primes = new Primes(p);
        maxp = (int) primes.max();
        P = new int[p];

        List<Integer> parts = new ArrayList<>();
        for (int pi = 1; pi < maxp; pi *= 2) {
            for (int pj = 1; pi * pj < maxp; pj *= 3) {
                if (pi * pj > 1) {
                    parts.add(pi * pj);
                }
            }
        }
        Collections.sort(parts);

        for (int i = 0; i < parts.size(); i++) {
            List<Integer> list = new ArrayList<>();
            int part = parts.get(i);
            list.add(part);
            solve(part, i, list, parts);
        }

        long sum = 0;
        for (int i = 0; i < p; i++) {
            if (P[i] == 1) {
                sum += i;
            }
        }
        System.out.println(sum);

    }

    void solve(int sumparts, int lastindex, List<Integer> list, List<Integer> parts) {
        if (primes.contains(sumparts)) {
            P[sumparts]++;
        }
        int pi;
        for (int i = 0; i < lastindex && (pi = parts.get(i)) + sumparts <= maxp; i++) {
            if (valid(pi, list)) {
                list.add(pi);
                solve(sumparts + pi, i, list, parts);
                list.remove((Integer) pi);
            }
        }
    }

    boolean valid(int p, List<Integer> list) {
        for (long part : list) {
            if (part % p == 0) {
                return false;
            }
        }
        return true;
    }

}
