package P801_P850;

import java.util.*;

class PE838 {

  public static void main(String[] args) {
    new PE838().solve(2800);
    new PE838().solve(1000000);
  }
    
  void solve(int N) {
    
    var A = 0D;
    var M = new int[]{0,3,0,1,0,0,0,9,0,7};
    var S = new HashMap<Integer,Integer>();
    var P = new HashMap<Integer,List<Integer>>();
    
    for (int p=3; p<=N; p+=10){
      var d = new ArrayList<Integer>();
      for (int n=p,q=3;q<=n;q+=2) while (n%q<1)
      {d.add(q); n/=q;} P.put(p,d);
    }
    
    for (int e,f, p=3; p<=N; p+=10){
      if (!P.containsKey(p)) continue;
      if (P.get(p).size()!=1) f=11*(e=p);
      else {e=P.get(p).get(0);f=e*M[e%10];A+=Math.log(e);}
      while (f<=N) {P.remove(f); f+=10*e;}
    }
    
    while (!P.isEmpty()){
      int t,m=0,e=N; S.clear();
      for (int k:P.keySet()) for (int q:P.get(k)){
      S.put(q,t=S.getOrDefault(q,0)+1);m=t>m?t:m;}
      for (int p:S.keySet()) if (p<e && S.get(p)==m) e=p;
      for (int p=e*M[e%10]; p<=N; p+=10*e) P.remove(p);
      A += Math.log(e);
    }
    
    System.out.format("f(%d) = %.6f%n", N,A);
  }

}