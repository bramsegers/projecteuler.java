package P851_P900;

import util.Util;

public class PE869 {

  public static void main(String[] args) {
    new PE869().solve(10);
    new PE869().solve(30);
    new PE869().solve(100000000);
  }

  void solve(int N) {
    int[] P = Util.primes(N);
    int[] M = new int[3 * N];
    for (int p : P) {
      int m = 1;
      int e = 2;
      while (m < 2 * p) {
        M[e | (p & m)]++;
        m = 2 * m + 1;
        e = 2 * e;
      }
    }
    int s = 0;
    for (int p : P) s += f(p, M);
    double ans = 1D * s / P.length;
    System.out.format("P(%d) = %.8f%n", N, ans);
  }

  int f(int p, int[] M) {
    int r = 0;
    int s = 0;
    int t = 1;
    int u = 2;
    int v = 3;
    while (p > 0) {
      int a = M[u + s];
      int b = M[v + s];
      int g = b > a ? 1 : 0;
      r += g == (p & 1) ? 1 : 0;
      s += (p & 1) * t;
      t *= 2;
      u *= 2;
      v *= 2;
      p /= 2;
    }
    return r;
  }

}