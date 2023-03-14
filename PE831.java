package P801_P850;

import java.math.BigInteger;

class PE831 {

  // g(n) = 7^n * (81*n^5 + 765*n^4 + 2085*n^3 + 1835*n^2 + 474*n + 40) / 40;
  public static void main(String[] args) {
    new PE831().solve(142857);
  }

  BigInteger b(long p){return BigInteger.valueOf(p);}

  void solve(long n){
    
    BigInteger b = (
      b(81).multiply(b(n).pow(5))
      .add(b( 765).multiply(b(n).pow(4)))
      .add(b(2085).multiply(b(n).pow(3)))
      .add(b(1835).multiply(b(n).pow(2)))
      .add(b( 474).multiply(b(n)))
      .add(b(40))).divide(b(40));
    
    String s = 
      b.toString(7)
      .substring(0,10);
    
    System.out.format("G(%d) = %s%n",n,s);
  }

}