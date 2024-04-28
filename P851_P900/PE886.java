package P851_P900;
 
import java.util.Arrays;

public class PE886 {

  public static void main(String[] args) {
    new PE886().solve(10);
    new PE886().solve(20);
    new PE886().solve(34);
  }

  void solve(int N) {
 
    var A = new int[N + 1];
    var B = new int[N + 1];
    Arrays.fill(B, -1);
    
    for (int n = 2, r; n <= N; n++)
      if (A[r = 2 * n > N && isprime(n) > 0 
        ? 1 : radical(n)]++ < 1) B[r] = D++;
    
    R = new int[D];
    Z = new int[D];
    X = new int[D][D];
    
    int a, b, c;
    for (int r = 0; r <= N; r++) if ((a = B[r]) >= 0) {
    for (R[a] = A[r], c = 1 - r % 2, b = 0; c <= N; c += 2)
      if (B[c] >= 0 && gcd(r, c) < 2) X[a][b++] = B[c]; 
        X[a] = Arrays.copyOf(X[a], b); }
    
    a = 1; b = c = 0;
    for (int k : R) { Z[b++] = a; c += ++c * k; a *= ++k;  }
     
    M = new int[D * a];
    Arrays.fill(M, -1);
    Arrays.fill(M, 0, D, 1);
    
    System.out.format("P(%d) = %d%n", N, dfs(c, B[1]));
  }
  
  
  int D, R[], Z[], M[], X[][];
  
  int dfs(int s, int p) {
    int k = D * s + p;
    int r = M[k];
    if (r >= 0) return r;
    for (int i : X[p]) {
      int t = (s / Z[i]) % (R[i] + 1);
      if (t > 0) r = (r + t * dfs(s - Z[i], i)) % 83456729;
    }
    return M[k] = r + 1;
  }
  
    
  int gcd(int a, int b) { return b < 1 ? a : gcd(b, a % b); }
  int isprime(int n) { for (int d = 2; d*d <= n; d++) if (n % d < 1) return 0; return 1; }
  int radical(int n) { int r = 1; for (int i = 1; n > i++;) if (n % i < 1) for (r *= i; n % i < 1;) n /= i; return r;}
 
}