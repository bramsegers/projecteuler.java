package P501_P550;

import util.Primes;

public class PE543 {

    public static void main(String[] args) {
        new PE543().solve(44);
    }

    void solve(int n) {
        System.out.format("%2s %10s %20s %n", "k", "F(k)", "S(F(k))");
        long sum = 0, fib1 = 1, fib2 = 1;
        for (int k = 3; k <= n; k++) {
            long fib = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib;
            long s = S(fib);
            sum += s;
            System.out.format("%2d %10d %20d %n", k, fib, s);
        }
        System.out.println("Σ S(F(k)) = " + sum);
    }

    long S(long n) {
        Primes pr = new Primes(n);

        //k=1
        long sum = pr.size();

        //k=2 (Goldbach's conjecture)
        for (long i = 4; i <= n; i++) {
            sum += (i & 1) == 0 || pr.contains(i - 2) ? 1 : 0;
        }

        //k=3...N
        for (long k = 3; k <= n / 2; k++) {
            sum += n - 2 * k + 1;
        }

        return sum;
    }

}
