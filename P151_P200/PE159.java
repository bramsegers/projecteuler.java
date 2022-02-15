package P151_P200;

import java.util.ArrayList;
import java.util.List;

public class PE159 {

    public static void main(String[] args) {
        //new PE159().solve(1000000);
        new PE159().solve2(1000000);
    }

    void solve(int nMax) {
        long start = System.currentTimeMillis();

        List<List<Integer>> divs = new ArrayList<>();
        for (int n = 0; n < nMax; n++) {
            divs.add(new ArrayList<Integer>());
        }
        for (int n = 2; n < nMax; n++) {
            for (int i = n; i < nMax; i += n) {
                divs.get(i).add(n);
            }
        }

        long sumMaxDRS = 0;
        for (int n = 2; n < nMax; n++) {

            List<List<Integer>> all = new ArrayList<>();
            factors(0, n, 1, new ArrayList<Integer>(), divs.get(n), all);

            int maxDRS = 0;
            for (List<Integer> factors : all) {
                int DRS = 0;
                for (int f : factors) {
                    DRS += DRS(f);
                }
                maxDRS = Math.max(maxDRS, DRS);
            }
            sumMaxDRS += maxDRS;
            if (n % 10000 == 0) {
                System.out.println(n);
            }

        }

        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sumMaxDRS, end - start);
    }

    void factors(int i, long t, long p, List<Integer> done, List<Integer> divs, List<List<Integer>> all) {
        if (p == t) {
            all.add(done);
            return;
        }
        for (int j = i; j < divs.size(); j++) {
            int factor = divs.get(j);
            long p2 = p * factor;
            if (t % p2 == 0) {
                List<Integer> done2 = new ArrayList<>(done);
                done2.add(factor);
                factors(j, t, p2, done2, divs, all);
            }
        }
    }

    int DRS(int n) {
        int drs = 0;
        while (n > 0) {
            drs += n % 10;
            n /= 10;
        }
        return drs < 10 ? drs : DRS(drs);
    }

    void solve2(int nMax) {
        long start = System.currentTimeMillis();
        int[] DRS = new int[nMax];
        int sum = 0;
        for (int i = 2; i < DRS.length; i++) {
            DRS[i] = (i - 1) % 9 + 1;
        }
        for (int j = 2; j < nMax; j++) {
            int drsj = DRS[j];
            for (int k = 2, n = j + j; k <= j && n < nMax; k++, n += j) {
                DRS[n] = Math.max(DRS[n], drsj + DRS[k]);
            }
            sum += drsj;
        }
        long end = System.currentTimeMillis();
        System.out.format("P(%d)=%d%nElapsed:%dms%n", nMax, sum, end - start);
    }

}
