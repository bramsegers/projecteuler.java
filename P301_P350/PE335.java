package P301_P350;

import static util.Util.modPow;
import static util.Util.modInv;

public class PE335 {

  public static void main(String[] args) {
    new PE335().solve((long)1e18, (long)Math.pow(7,9));
  }

  void solve(long N, long M) {
    long i2 = modInv(2,M);
    long i3 = modInv(3,M);
    long p2 = modPow(2,N+1,M)-1;
    long p3 = modPow(3,N+1,M)-1;
    long p4 = modPow(4,N+1,M)-1;
    long ans = 2*p2 + i3*p4 - i2*p3;
    System.out.println(ans % M);
  }

}