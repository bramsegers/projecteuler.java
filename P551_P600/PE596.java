package P551_P600;

public class PE596 {

    //https://en.wikipedia.org/wiki/Gauss_circle_problem
    //https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem    
    public static void main(String[] args) {
        new PE596().solve(2, 100);
        new PE596().solve(5, 10000);
        new PE596().solve(100, (long) 1e9);
        new PE596().solve(10000, (long) 1e18);
        new PE596().solve(100000000, 1000000007);
    }

    void solve(long r, long m) {
        long p, q, sum = 0, n = 1, r2 = r * r;
        while (n <= r2) {
            p = r2 / n;
            q = r2 / p;
            sum += (p % m) * ((t2(q, m) - t2(n - 1, m)) % m);
            sum %= m;
            n += (4 - (n & 3)) & 3;
            sum -= ((p * 4) % m) * ((t2(q / 4, m) - t2((n / 4) - 1, m)) % m);
            sum %= m;
            n = q + 1;
        }
        sum *= 4;
        sum += 1;
        sum %= m;
        System.out.format("T(%d) mod %d = %d %n", r, m, sum);
    }

    long t2(long n, long m) {
        return (n % m) * ((n + 1) % m);
    }

}
