package P551_P600;

import java.util.TreeMap;
import util.Primes;

public class PE576 {

    public static void main(String[] args) {
        new PE576().solve();
    }

    void solve() {
        //M1(100, 0.00002D);
        M2(100, 0.00002D);
    }

    void M1(int n, double g) {

        TreeMap<Double, Double> map = new TreeMap<>();

        Primes pr = new Primes(n);
        double d, max = 0;
        int sta = 0;
        int end = 1000000;
        double tot = 1000000;
        for (int i = sta; (d = i / tot) < 1 - g && i < end; i++) {
            double sum = 0;
            for (long p = 2; p > 0; p = pr.next(p)) {
                double len = Math.sqrt(1D / p);
                sum += S(len, g, d);
            }
            System.out.format("%.8f %.10f %n", d, sum);
            max = Math.max(max, sum);
            map.put(sum, d);
            if (map.size() > 40) {
                map.remove(map.firstKey());
            }

        }
        for (double m : map.keySet()) {
            System.out.println(m + " " + map.get(m));
        }
        //System.out.format("%.4f %n", max); 
    }

    void M2(int n, double g) {

        Primes pr = new Primes(n);
        double d, max = 0;
        int sta = 78347300;
        int end = 78347500;
        double tot = 100000000;
        for (int i = sta; (d = i / tot) < 1 - g && i < end; i++) {
            double sum = 0;
            for (long p = 2; p > 0; p = pr.next(p)) {
                double len = Math.sqrt(1D / p);
                sum += S(len, g, d);
            }
            System.out.format("%.8f %.10f %n", d, sum);
            max = Math.max(max, sum);
        }
        System.out.format("%.4f %n", max);
    }

    double S(double len, double g, double d) {
        long count = 0;
        double x = 0;
        while (x < d || x > d + g) {
            count++;
            x += len;
            x -= (long) x;
        }
        //System.out.println(count * len);
        return count * len;
    }

}
