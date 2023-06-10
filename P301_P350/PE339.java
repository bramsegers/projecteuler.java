package P301_P350;

public class PE339 {

  public static void main(String[] args) {
    new PE339().solve(5);
    new PE339().solve(10000);
  }

  void solve(int n) {
    double a,b=1;
    for (int i=1;++i<n;) b=f(i,2*i-1,b);
    b=f(n+1,2*n, f(n,2*n-1, a=b));
    System.out.format("E(%d)=%.6f%n",n,(a+b)/2);
  }

  double f(int a,int b,double e) {
    double p=b-1, q=1D/b, r,s,t=p;
    while (a<t--) {r=t/b; s=1-r*q; p=p*r/s; q=(1-r)/s;}
    return p+q*e;
  }
}