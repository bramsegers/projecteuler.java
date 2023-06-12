package P301_P350;

public class PE334 {

  public static void main(String[] args) {
    new PE334().solve(2);
    new PE334().solve(1500);
  }

  void solve(int N) {
    long r = 0;
    long a = 0, b = 0, c = 1, d = -1;
    long t = 123456, u, v[];
    while (++d < N){
      t = (t&1)>0 ? (t>>1)^926252 : t>>1;
      u = t % (1<<11) + 1;
      while (0 < u--) {
        v = f(a,b,c,d);
        a = v[0];
        b = v[1];
        c = v[2];
        r += v[3];
      }
    }
    System.out.format("M(%d) = %d%n", N, r);
  }

  long[] f(long a, long b, long c, long d) {
    if (d==b) return new long[]{a, c, c+1, 0};
    if (d<b)  return new long[]{a-1, b-d+a-1, c, (d-a+1)*(b-d)};
    var v = f(1-c, -b, 1-a, -d);
    return new long[]{1-v[2], -v[1], 1-v[0], v[3]};
  }

}