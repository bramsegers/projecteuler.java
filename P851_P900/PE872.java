package P851_P900;

public class PE872 {

  public static void main(String[] args) {
    new PE872().solve(6, 1);
    new PE872().solve(10, 3);
    new PE872().solve(100000000000000000L, 16677181699666569L);
  }
  
  void solve(long n, long k) {
    long a = n, t = k;
    while (t < n) { a += t; t += Long.highestOneBit(n - t); }
    System.out.format("P(%d,%d) = %d%n", n, k, a);
  }
}
