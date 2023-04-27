package P301_P350;

public class PE322 {

  public static void main(String[] args) {
    new PE322().solve((long)1e9, (long)1e7-10);
    new PE322().solve((long)1e18,(long)1e12-10);
  }

  void solve(long m,long n) {
    long ans = m-n;
    ans -= f(m-n,n,n,2,0,1);
    ans -= f(m-n,n,n,5,0,1);
    System.out.println(ans);
  }

  long f(long m, long n, long a, long b, long c, long d){
    long r = 0;
    long s = b-n%b;
    long t = (m-1-c)/d+1;
    if (n<1 && b==2) return t>0?t:0;
    while (n*s>0) r+=f(m,n/b,a,b,c+d*--s,d*b);
    while (n<1 && t>0) r+=((c+d*--t)&a)>0?1:0;
    return r;
  }

}