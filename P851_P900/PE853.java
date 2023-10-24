package P851_P900;

class PE853 {

  public static void main(String[] args) {
    new PE853().solve();
  }

  void solve() {
    long[] q = {2, 3, 5, 11, 31, 41, 61, 2521};
    System.out.println(f(0, 1, q));
  }

  long f(int i, long p, long[] q) {
    long r = 0;
    if (i == q.length)
      return p120(p) ? p : 0;
    while (p < 1e9) {
      r += f(i + 1, p, q);
      p *= q[i];
    }
    return r;
  }

  boolean p120(long m) {
    long a = 0;
    long b = 1;
    for (int i = 1; i <= 120; i++) {
      b = a + b;
      a = b - a;
      b %= m;
      if (a == 0 && b == 1)
        return i == 120;
    }
    return false;
  }

}
