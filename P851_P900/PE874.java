package P851_P900;

public class PE874 {

  public static void main(String[] args) {
    int[] P = util.Util.primes(100000);
    new PE874().solve(2, 5, P);
    new PE874().solve(7000, P[7000], P);
  }

  void solve(int k, int n, int[] P) {

    int L = n * (k - 1) % k;
    int[] D = new int[L + 1];

    for (int i = 0, j, u, v; i++ < L;) {
      for (j = v = 0; j < i; j++) {
        u = D[j] - P[k - i + j - 1];
        v = j < 1 || u < v ? u : v;
      }
      D[i] = P[k - 1] + v;
    }

    long ans = 1L * n * P[k - 1] - D[L];
    System.out.format("P(%d,%d) = %d%n", k, n, ans);
  }
}
