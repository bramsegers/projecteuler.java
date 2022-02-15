package P751_P800;

import util.Util;

public class PE779 {

  public static void main(String[] args) {
    new PE779().solve();
  }

  void solve() {
    double A=0,B=1;
    int[] P=Util.primes(100000);
    for (double p:P){ A+=B/p/--p/p; B*=p/++p; }
    System.out.format("%.12f%n",A);
  }

}