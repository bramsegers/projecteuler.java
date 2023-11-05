package P851_P900;

public class PE862 {

  public static void main(String[] args) {
    new PE862().S(3);
    new PE862().S(12);
  }

  int K;
  int[] D;
  long[] F;

  void S(int k) {
    K = k;
    D = new int[10];
    F = new long[K + 1];
    for (F[k = 0] = 1; k++ < K;) F[k] = k * F[k - 1];
    System.out.format("S(%d) = %d%n", K, s(0, 0));
  }

  long s(int n, int d) {
    long r = 0;
    if (n == K) {
      r = F[K] - F[K - 1] * D[0];
      for (int k : D) r /= F[k];
      return r * --r / 2;
    }
    D[d]++; r += s(n + 1, d); D[d]--;
    if (d < 9) r += s(n, d + 1);
    return r;
  }

}