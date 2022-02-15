package P751_P800;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PE766 {
 
  public static void main(String[] args) {
    new PE766().solve();
  } 
 
  String  
          
  P1="""
  AAB_
  ACD_
  EFGH
  A/BCDEFGH
  """,
          
  P2="""
  _AACBB
  _ACCBE
  IJGGFE
  KLGGFD
  MNHHDD
  AB/CD/EF/G/H/IJKLMN
  """; 
  
  int ID[],K,N,W;
  Set<String> S=new HashSet<>();
  
  void solve(){
    dfs(parse(P2));
    System.out.println(S.size());
  }
 
  long[] parse(String P){
    String[] t=P.split("\n");
    int h=t.length-1;
    W=t[0].length()+2;
    P=t[h].replaceAll("/","");
    N=P.length()+1;
    K=1; ID=new int[N];
    long[] p=new long[N];
    for(char c:t[h].toCharArray())
      if(c=='/') K++;
      else ID[c-'A'+1]=K;
    for(int j=0;j<h;j++)
      for(int k,i=0;i<W-2;i++){
        k=t[j].charAt(i);
        k=k=='_'?0:k-'A'+1;
        p[k]|=1L<<((j+1)*W+i+1);
    }
    return p;
  }
 
  boolean add_state(long[] P){
    long[] a=new long[K];
    for(int i=1;i<N;i++) a[ID[i]-1]|=P[i];
    return S.add(Arrays.toString(a));
  }  
 
  void dfs(long[] P){
    if(add_state(P))
      for(int i=1;i<N;i++){
        long t=P[i],f=P[0]|t,Q[];
        for(long p:new long[]{t<<1,t<<W,t>>1,t>>W})
          if((f|p)==f){
            Q=P.clone();
            Q[0]=f-p;
            Q[i]=p;
            dfs(Q); 
      }
    }
  }
 
}