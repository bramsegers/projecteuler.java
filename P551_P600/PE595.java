package P551_P600;

public class PE595 {

    void solve(int n) {
        double[] s = new double[n + 1];
        for (int i = 2; i <= n; i++) {
            s[i] = p(i, i);
            for (int j = 2; j < i; j++) {
                s[i] += p(i, j) * (1 + s[j]);
            }
            s[i] /= 1 - p(i, i);
            System.out.format("%d %.8f %n", i, s[i]);
        }
    }

    double p(int n, int k) {
        //https://oeis.org/A010027 
        //U(n,k)=(1/n)*binom(n,k)*d(k+1), where d(j)=A000166(j)
        //p(n,k)=U(n,k)/n!
        return d(k + 1) / (n * fac(n - k) * fac(k));
    }
    

    double d(int j) {
        //https://oeis.org/A000166
        //a(n) = n*a(n-1) + (-1)^n.
        long s = -1;
        double a = 0;
        for (int n = 2; n <= j; n++) {
            s = -s;
            a = n * a + s;
        }
        return a;
    }

    double fac(double n) {
        return n == 0 ? 1 : n * fac(n - 1);
    }

    public static void main(String[] args) {
        new PE595().solve(52);
    }

}
