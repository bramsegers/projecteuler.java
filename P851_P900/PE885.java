package P851_P900;

public class PE885 {

  public static void main(String[] args) {
     new PE885().S(1);
     new PE885().S(2);
     new PE885().S(18);
  }

  int m = 1123455689;
  
  void S(int n) {
    System.out.format("S(%d) = %d%n", n, f(n, 1, 0, 0, 0));
  }

  long f(int n, long f, long v, int d, int c) {
    if (n < 1) return ((f % m) * (v % m)) % m;
    long r = f(n - 1, f * n / ++c, 10 * v + d, d, c);
    while (++d < 10) r += f(n - 1, f * n, 10 * v + d, d, 1);
    return r % m;
  }

}