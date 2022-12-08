package P751_P800;

import java.util.Arrays;

public class PE793 {

  public static void main(String[] args) {
    new PE793().solve(103);
    new PE793().solve(1000003);
  }

  int N;
  long A[], B[], T;

  long S = 290797;
  long M = 50515093;
  long G() {long r = S; S = (S * S) % M; return r;}

  int bin0(int r, int i, int j, long v) {
    if (i == j) return A[r] * A[i] <= v ? i : -1;
    int m = i + ((j - i) >> 1);
    if (v < A[r] * A[m]) return bin0(r, i, m, v);
    int b = bin0(r, m + 1, j, v);
    return b < 0 ? m : b;
  }

  long bin1(long i, long j, long t) {
    long r = 0;
    long m = (i + j) / 2;
    for (int k = 0; k < N - 1; k++) {
      int n = bin0(k, k + 1, N - 1, m);
      if (n < 0) break;
      r += n - k;
    }
    if (r == t) return m;
    if (r < t) return bin1(m, j, t);
    return bin1(i, m, t);
  }

  long bin2(long v) {
    long t = 0;
    for (int k, i = 0; i < N - 1; i++) {
      k = bin0(i, i + 1, N - 1, v);
      if (k < 0) break;
      t += k - i;
    }
    return t;
  }

  long bin3(int i, int j) {
    int m = (i + j) / 2;
    long t = bin2(B[m]);
    if (t == T) return B[m];
    if (t < T) return bin3(m, j);
    return bin3(i, m);
  }

  void solve(int n) {

    N = n;
    A = new long[N];
    B = new long[N];
    T = (1L * N * (N - 1)) / 4 + 1;
    for (int i = 0; i < N; i++) A[i] = G();
    Arrays.sort(A);

    long min = A[0] * A[1];
    long max = A[N - 2] * A[N - 1];
    long lob = bin1(min, max, T - 1);
    long upb = bin1(min, max, T + 1);

    for (int i = 0, j = 0, u, v;; i++) {
      u = bin0(i, i + 1, N - 1, lob);
      v = bin0(i, i + 1, N - 1, upb);
      if (u < 0) break;
      while (u <= v) B[j++] = A[i] * A[u++];
    }

    Arrays.sort(B);
    long ans = bin3(0, N - 1);
    System.out.format("M(%d) = %d%n", N, ans);
  }

}