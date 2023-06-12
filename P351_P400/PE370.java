package P351_P400;

public class PE370 {

  public static void main(String[] args) {
    new PE370().solve((long)1e6);
    new PE370().solve((long)(25*1e12));
  }

  void solve(long n) {
    long p = 0;
    int s = (int) Math.sqrt(n);
    int[] mu = moebius(s);
    for (int j=0; ++j<s;)
      p += mu[j]*f(n/(1L*j*j));
    System.out.format("P(%d) = %d%n", n, p);
  }
  
  int[] moebius(int n){
    int i,j,m[][]=new int[3][n];
    for (i=1;++i*i<n;) for (j=i;i*j<n && m[0][i]<1;j+=1) m[0][i*j]=1;
    for (i=1;++i*i<n;) for (j=i*i;j<n && m[0][i]<1;j+=i*i) m[1][j]=1;
    for (i=1;++i  <n;) for (j=i;  j<n && m[0][i]<1;j+=i)  m[2][j]^=2;
    while (n>0) m[2][--n]=(1-m[1][n])*(1-m[2][n]);
    return m[2];
  }

  long f(long n) {
    long a,b,c,d;
    long p=(long)Math.cbrt(n);
    double q=(Math.sqrt(5)-1)/2;
    for (a=d=0; ++d<p;)
      for (b=(long)(d*q),c=1; c>0 && d>b++;)
        a+= c=n/(b*b+b*d+d*d);
    for (;p*p<n; p++)
      for (b=(long)(p*q),c=d=n,c/=3*p*p; d!=b && c<n/(2*p*p)+2;)
        a+= c*(Math.min(s(n,p,c),p)-(d=Math.max(b,s(n,p,++c))));
    return a;
  }

  long s(long n, long a, long b) {
    return (long)((Math.sqrt(4D*n/b-3*a*a)-a)/2);
  }
}