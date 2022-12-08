package P751_P800;

import util.Util;

public class PE800{

  public static void main(String[] args){
    new PE800().C(800);
    new PE800().C(800800);
  }

  int N,P[];

  void C(int n){
    int s=0,q=1;N=n;
    P=Util.primes(20*N);
    for (int p=0;q>p;p++) s+=(q=Q(p))-p;
    System.out.format("C(%d**%d)=%d%n",n,n,s);
  }
   
  double P(int p,int q){
    return Math.pow(P[p],1d*P[q]/N);
  }

  int Q(int p){
    int a=p,b=P.length-1,q;
    while (b-a>1) if (P(p,q=(a+
    (b-a)/2))*P(q,p)<N) a=q; else b=q;
    return a;
  }

}