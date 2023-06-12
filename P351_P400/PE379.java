package P351_P400;

public class PE379 {

  public static void main(String[] args){
    new PE379().solve((long)1e6);
    new PE379().solve((long)1e12);
  }

  void solve(long n){
    long a = n,p,q;
    int i,j,s =(int)Math.sqrt(n);
    int[][] f = new int[++s][9];
    for (i=2; i<s; i++) if (f[i][0]<1)
    for (j=i; j<s; j+=i) f[j][++f[j][0]]=i;
    for (i=1; i<s; i++) for (p=n/i; p>i; p=q)
      a+=n/(i*p)*f(1,q=Math.max(n/(1+n/(i*p))/i,i),p,f[i],1);
    System.out.format("g(%d) = %d%n", n,a);
  }

  long f(long p, long q, long r, int[] f, int i){
    return f[i]<1 ? r/p - q/p : f(p,q,r,f,i+1) - f(p*f[i],q,r,f,i+1);
  }

}