package P751_P800;

import util.Util;

public class PE772 {

  public static void main(String[] args) {
    new PE772().solve(30,1000000007);
    new PE772().solve(100000000,1000000007);
  }

  void solve(int n,int m){
    long e,lcm=2;
    for(int p:Util.primes(n)){
      e=(long)(Math.log(n)/Math.log(p));
      lcm=(lcm*Util.modPow(p,e,m))%m;
    }
    System.out.format("f(%d) mod %d = %d%n",n,m,lcm);
  }

}