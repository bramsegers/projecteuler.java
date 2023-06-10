package P251_P300;

import util.Util;
import java.util.*;

public class PE295 {

  public static void main(String[] args) {
    new PE295().solve(10);
    new PE295().solve(100);
    new PE295().solve(100000);
  }

  HashSet<Long> U, M;
  HashMap<Long, List<Long>> K;
  HashMap<List<Long>, Long> C;

  void solve(long N) {

    long ans = 0;
    U = new HashSet<>();
    M = new HashSet<>();
    K = new HashMap<>();
    C = new HashMap<>();

    ans += f(N,1,1,1);
    for (long y=1; y<1000; y+=2)
      for (long x=1; x<y; x+=2)
        ans += f(N,x,y,1);

    f(N,1,1,0);
    for (long y=1; y<1000; y+=2)
      for (long x=1; x<y; x+=2)
        f(N,x,y,0);

    for (var a:K.values()) dfs(a,0);

    for (var e:C.entrySet()) {
      long n = e.getKey().size()%2;
      long s = e.getValue();
      ans += s*(s+1)/(n<1?-2:2);
    }
    System.out.format("L(%d)=%d%n",N,ans);
  }

  long k0(long x, long y, long p) {
    for (long a=0; a<x; a++) {
      long b =(a*y+x)/x;
      long n = a*a+b*b+b*x-b*y-a*x-a*y;
      long d = 2*(b*x-a*y);
      long k = (d-n-1)/d;
      p = p>k ? p : k;
    }
    return p;
  }

  long k1(long x, long y, long N) {
    long b = x*x + y*y;
    long k = (long)((Math.sqrt((4*b*N*N-b*b))/b-1)/2+.5);
    return k + ((b*(2*k*(k+1)+1)/2 > N*N) ? 0 : 1);
  }

  long f(long N, long x, long y, long m) {
    if (Util.gcd(x,y)>1) return 0;
    long p = k0(x,y,0), q = k1(x,y,N)-p;
    for (long r,s=p; s<p+q;) {
      r = (x*x+y*y)*(2*s*++s+1)/2;
      if (m>0 && !U.add(r)) M.add(r);
      if (m<1 &&  M.contains(r)) {
        var a = K.getOrDefault(r, new ArrayList<>());
        a.add((x<<16)+y); K.put(r,a);
      }
    }
    return q<0 ? 0 : q*(q+1)/2;
  }

  void dfs(List<Long> a, int i) {
    C.put(a, C.getOrDefault(a,0L)+1);
    for (int n=a.size(),j=i; n>2 && j<n;){
      var b = new ArrayList<>(a);
      b.remove(j);
      dfs(b,j++);
    }
  }

}
