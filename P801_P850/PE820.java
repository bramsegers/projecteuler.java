package P801_P850;

import util.Util;

class PE820 {

  public static void main(String[] args) {
    new PE820().solve(100);
    new PE820().solve(10000000);
  }

  void solve(int n){
    long s=0;
    for(int i=1;i<=n;i++)
      s+=Util.modPow(10,n-1,i)*10/i;
    System.out.format("S(%d)=%d%n",n,s);
  }

}