package P301_P350;

import util.CRT;

public class PE330 {

  public static void main(String[] args) {
    new PE330().solve();
  }

  int N=1000000000;

  void solve() {
    long[] a=new long[5];
    long[] b={7,11,73,101,137};
    for(int i=0;i<5;i++) a[i]=A((int)b[i]);
    System.out.println(77777777-CRT.solve(a,b));
  }

  long A(int p){
    int s=0;
    int t=N%(p*(p-1));
    t+=t<p?p*(p-1):0;
    int[] a=new int[t+1];
    int[] b=new int[t+2];
    int[] c=new int[p];
    a[0]=b[0]=b[1]=c[0]=1;
    for(int i=1;i<p;i++)
      c[i]=(c[i-1]*i)%p;
    for(int i=0,j;i<t;i++){
      for(s=i<p-1?c[i+1]:0,j=0;j<=i;j++)
        s+=b[j+1]*a[i-j];
      for(a[i+1]=s%p,j=i+2;j>0;j--)
        b[j]=(b[j]+b[j-1])%p;
    }
    return s%p;
  }

}