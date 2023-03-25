package P801_P850;

import util.Util;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

class PE834 {

  public static void main(String[] args) {
    new PE834().solve(100);
    new PE834().solve(1234567);
  }

  void solve(int N) {
    List<List<Integer>> divs = new ArrayList<>();
    for (int i=0;i<=N;i++) divs.add(new ArrayList<>());
    for (int p=1,u;(u=p*p)<=N;p++){
      divs.get(u).add(p);
      for (int q=p+1,v;(v=p*q)<=N;q++){
        divs.get(v).add(p);
        divs.get(v).add(q);
      }
    }
    long ans=IntStream
      .range(3,N+1)
      .parallel()
      .mapToLong(n->{
        long m,d,t=0;
        for (int p:divs.get(n-1))
          for (int q:divs.get(n))
            if ((d=(long)p*q)>n)
              t+=(((m=d-n))%2<1?n+m/2:2*n+m)%(d/
              Util.gcd(d,m%2<1?m+1:(m+1)/2))<1?m:0;
        return t;
    }).sum();
    System.out.format("U(%d)=%d%n",N,ans);
  }

}