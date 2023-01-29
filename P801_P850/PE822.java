package P801_P850;

import java.util.TreeSet;
import java.util.function.Function;
import static util.Util.modPow;
import static java.lang.Math.log;
import static java.lang.Math.min;

public class PE822 {

  public static void main(String... args) {
    new PE822().solve(10, 100);
    new PE822().solve(10000, (long) 1e16);
  }

  void solve(int m, long n) {

    record R(long a, long b) {}
    Function<R, Double> C = r -> r.b * log(2) + log(log(r.a));
    var T = new TreeSet<R>((R a, R b) -> C.apply(a) < C.apply(b) ? -1 : 1);
    long a, b, c, d = 0, e = 0, f = 2;
    
    while (f <= m)
      e += (f - min((f *= f), m)) * ++d;
    
    c = (n - e) / (m - 1);
    T.add(new R(2, c));

    for (a = d = 0, f = 2; f <= m;)
      for (f *= b = f, d++; b < min(f, m); b++)
        T.add(new R(++a + 2, c - d));

    for (e += c * (m - 1); e < n; e++) {
      R r = T.pollFirst();
      T.add(new R(r.a, r.b + 1));
    }

    e = 0;
    m = 1234567891;
    for (R r : T)
      e = (e + modPow(r.a, modPow(2, r.b, m - 1), m)) % m;
    System.out.println(e);
  }
}
