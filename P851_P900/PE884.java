package P851_P900;

import java.util.HashMap;

public class PE884 {

  public static void main(String[] args) {
    new PE884().S((long)1e2);
    new PE884().S((long)1e17);
  }

  HashMap<Long, Long> mem;
  
  void S(long n) {
    (mem = new HashMap<>()).put(0L, 1L);
    System.out.format("S(%d) = %d%n", n, f(n - 1) - 1);
  }

  long f(long n) {
    long c = mem.getOrDefault(n, 0L);
    if (c < 1) {
      c = (long) Math.cbrt(n);
      c -= c * c * c > n ? 1 : 0;
      c = n - (c *= c * c) + f(c - 1) + f(n - c);
      mem.put(n, c);
    }
    return c;
  }

}