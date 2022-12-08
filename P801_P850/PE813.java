package P801_P850;

import java.math.BigInteger;
import java.util.HashSet;
import static util.Util.bi;

public class PE813 {

  public static void main(String[] args) {
    var n = bi(12).pow(8).multiply(bi(8).pow(12));
    var m = bi(10).pow(9).add(bi(7));
    new PE813().solve(n, m);
  }

  HashSet<BigInteger> xorp(HashSet<BigInteger> a, HashSet<BigInteger> b) {
    if (a.isEmpty()) return b;
    var x = new HashSet<BigInteger>();
    for (BigInteger e : a)
      for (BigInteger f : b)
        if (!x.add(e.add(f)))
          x.remove(e.add(f));
    return x;
  }

  void solve(BigInteger n, BigInteger m) {
    var b = new HashSet<Integer>();
    var x = new HashSet<BigInteger>();
    for (int i = 0; n.signum() > 0; i++) {
      if (n.testBit(0)) b.add(i);
      n = n.shiftRight(1);
    }
    for (int i : b) {
      var  t = bi(2).pow(i);
      var  u = new HashSet<BigInteger>();
      u.add(bi(0));
      u.add(t);
      u.add(t.multiply(bi(3)));
      x = xorp(x, u);
    }
    BigInteger ans = bi(0);
    for (BigInteger e : x)
      ans = ans.add(bi(2).modPow(e, m));
    System.out.println(ans.mod(m));
  }
}
