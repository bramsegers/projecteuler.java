package P801_P850;

public class PE839 {

  public static void main(String[] args) {
    new PE839().solve(5);
    new PE839().solve(6);
    new PE839().solve(100);
    new PE839().solve(10000000);
  }

  void solve(int N) {

    long s = 290797;
    long m = 50515093;
    long[] A = new long[N];
    long[] B = new long[N];
    long[] C = new long[N];

    for (int i = 0; i < N; i++) {
      A[i] = B[i] = s;
      s = (s * s) % m;
    }

    int i = 1, j, k;
    long a = 0, b, c, d, e;

    while (i > 0) {
      k = j = i;
      b = d = e = i = 0;
      while (++k < N) {
        d += A[k];
        c = d / (k - j);
        if (e < 1 || c < e) {
          b = d - (k - j) * c;
          e = c;
          i = k;
        }
      }
      k = j + 1;
      while (i > j++) {
        A[j] = i < b + j ? e + 1 : e;
        d = C[j - k] + B[j] - A[j];
        a += C[j - k + 1] = d;
      }
    }

    System.out.format("B(%d) = %d%n", N, a);
  }

}
