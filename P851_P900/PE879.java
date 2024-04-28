package P851_P900;

import util.Util;

public class PE879 {

  public static void main(String[] args) {
    new PE879().solve(3);
    new PE879().solve(4);
  }
  
  int N;
  int S;
  long[] M;
  
  void solve(int n) {
    
    N = n;
    S = n * n;
    M = new long[S << S];
   
    long ans = 0;
    for (int m = 1, c = 0; c < S; m <<= 1, c++)
      ans += f((1 << S) - m - 1, c) - 1;
    System.out.format("P(%d) = %d%n", N, ans);
  }
  
  long f(int a, int b) {
    long r = 1;
    int k = S * a + b;
    if (M[k] > 0) return M[k];
    for (int m = 1, c = 0; c < S; m <<= 1, c++)
      if (m == (a & g(b, c))) r += f(a - m, c);
    return M[k] = r;
  }
        
  int g(int a, int b) {
    int r = 0;
    int ax = a % N;
    int ay = a / N;
    int bx = b % N;
    int by = b / N;
    int cx = bx - ax;
    int cy = by - ay;
    int dx = Math.abs(cx);
    int dy = Math.abs(cy);
    int g = Util.gcd(dx, dy);
    for (int i = 0; i < g; i++){
      ax += cx / g;
      ay += cy / g;
      r |= 1 << (N * ay + ax);
    }
    return r;
  }

}