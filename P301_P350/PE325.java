package P301_P350;

import java.math.BigInteger;

public class PE325 {

  public static void main(String[] args) {
    new PE325().solve(10, (long)1e18);
    new PE325().solve(10000, (long)1e18);
    new PE325().solve((long)1e16, (long)Math.pow(7,10));
  }

  double PHI = (1+Math.sqrt(5))/2;
  BigInteger B(long n) {return BigInteger.valueOf(n);}

  void solve(long n, long m) {
    System.out.format("S(%d) = %s%n", n,
    B(4*n-1).multiply(B(n)).multiply(B(n+1)).
    divide(B(6)).subtract(f(B(n))[1]).mod(B(m)));
  }
  
  BigInteger[] f(BigInteger n) {
    if (n.equals(B(1))) return new BigInteger[]{B(1),B(1)};
    BigInteger a[],b,c,d,p,q; 
    a = f(d=B((long)(n.doubleValue()/PHI)));
    b = n.multiply(n.add(B(1))).divide(B(2));
    c = d.multiply(d.add(B(1))).divide(B(2));
    p = b.multiply(d).add(b.multiply(n.add(n).add(B(1))).divide(B(3))).subtract(a[1]);
    q = c.multiply(n).subtract(b.multiply(n.subtract(B(1))).divide(B(3))).add(p).subtract(a[0]);
    return new BigInteger[]{p,q};
  }

}