package P851_P900;

public class PE865 {

  public static void main(String[] args) {
    new PE865().solve(6, 1000);
    new PE865().solve(10000, 998244353);
  }

  void solve(int n, int mod) {
    long r = 0, t1[], t2[]; t1 = new long[2 * n + 2]; int i, j;
    for (t1[i = 2] = 9; i <= n; i++, r = (r + t2[0]) % mod, t1 = t2)
      for (t2 = new long[2 * n + 2], j = (2 * i) % 3; j <= 2 * i; j += 3)
        t2[j] = (j  > 2 ?  9 * t1[j - 2] + t1[j + 1]
               : j == 2 ? 10 * t1[j - 2] + t1[j + 1] 
               : t1[j + 1]) % mod;
    System.out.format("N(%d) = %d%n", n, r);
  }

}
