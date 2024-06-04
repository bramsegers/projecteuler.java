package P851_P900;

public class PE893 {

  public static void main(String[] args) {
    new PE893().solve(100);
    new PE893().solve(1000000);
  }

  int N, D[], T[] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

  int t(int n) {
    return n < 1 ? 0 : T[n % 10] + t(n / 10);
  }

  void f(int e, int m, int n, int c) {
    if (c < 2 || n - D[e] < 2)
      for (D[e] = c < 2 ? D[e] : n - 2; m <= N / e; m++)
        f(e * m, m, n + 2 + D[m], c + 1);
  }

  void solve(int n) {
    int a, b, c = 0;
    D = new int[(N = n) + 1];
    for (n = 0; n <= N; n++)
      D[n] = n < 1 ? T[n] : t(n);
    for (f(a = 1, 2, 0, 0); a <= N; a++)
      for (b = a; a + b <= N; b++)
        D[a + b] = Math.min(D[a + b], D[a] + 2 + D[b]);
    for (n = 1; n <= N; n++)
      c += D[n];
    System.out.format("T(%d) = %d%n", N, c);
  }

}