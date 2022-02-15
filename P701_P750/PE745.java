package P701_P750;

import java.util.HashMap;
import java.util.Map;

public class PE745 {

  // 94586478 (28 sec)  
  public static void main(String[] args) { 
    new PE745().solve();
  }
   
  long N=(long)1e14;
  long M=(long)1e9+7;
  Map<Long,Long> R=new HashMap<>();
   
  void solve(){
    long s=N;
    for(long n=2;n*n<=N;n++)
      s=(s+(f(n*n,1)%M)*((n*n-1)%M))%M;
    System.out.println(s);
  }

  long f(long p,int ie){
    if(R.containsKey(p)) return R.get(p)*ie;
    long m=N/p, r=m*ie, q=1;
    while(++q*q<=m) r+=f(p*q*q,-ie);  
    R.put(p,r*ie);
    return r;
  }

}