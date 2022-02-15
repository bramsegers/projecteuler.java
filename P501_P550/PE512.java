package P501_P550;

import util.Primes;

public class PE512 {

    // g(500000000)=50660591862310323
    // BUILD SUCCESSFUL (total time: 44 minutes 48 seconds)
    public static void main(String[] args) {
        new PE512().solve(100);
        new PE512().solve(500000000);
    }

    void solve(int n) {
        Primes pr = new Primes(n);
        long sum = 0;
        for (int i = 1; i <= n; i += 2) {
            sum += pr.totient(i);
        }
        System.out.format("g(%d)=%d%n", n, sum);
    }

}
