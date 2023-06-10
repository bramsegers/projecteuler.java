package P801_P850;

public class PE845 {

  public static void main(String[] args){
    new PE845().D((long)61);
    new PE845().D((long)1e8);
    new PE845().D((long)1e16);
  }

  long[] PRI,MEM;

  void D(long n){
    int i,j,p = 200;
    PRI = new long[p];
    for (i=1; ++i<p;) PRI[i]=1;
    for (i=1; ++i<p;) if (PRI[i]>0)
    for (j=i; i+j<p;) PRI[j+=i]=0;
    long a=0, b=(long)1e18, m;
    while (a+1<b) {
      m = (a+b)/2;
      MEM = new long[9999];
      if (f(m,0,0,1)<n) a=m; else b=m;
    }
    System.out.format("D(%d)=%d%n",n,b);
  }

  long f(long n, int a, int b, int c){
    if (b==(""+n).length()) return PRI[a];
    int k = (a<<6)+(b<<1)+(c);
    int d = c<1?9:(""+n).charAt(b)-48;
    if (MEM[k]<1) while (d>=0)
      MEM[k] += f(n, a+d--, b+1, c+(c=0));
    return MEM[k];
  }

}
