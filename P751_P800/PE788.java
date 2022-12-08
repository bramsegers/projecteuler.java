package P751_P800;

import static util.Util.modChoose;
import static util.Util.modPow;

public class PE788 {

  public static void main(String[] args) {
    new PE788().solve(2022,1000000007);
  }

  void solve(int N,int M){

    long a=0, f[]=new long[N+1];
    for(int i=0;i<=N;i++)
      f[i]=i<2?1:(i*f[i-1])%M;

    for(long m,e,d=1;d<=N;d++)
      for(m=1,e=d>>1;e<d;e++,m=10)
        a=(a+((m*modChoose(d-1,e,M,f))%M)*modPow(9,d-e,M))%M;

    System.out.println(a);
  }

}