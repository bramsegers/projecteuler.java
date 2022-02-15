package P451_P500;

import java.util.HashMap;
import java.util.Map;
import util.Util;

public class PE474{

    //9690646731515010 (9 minutes 26 seconds)
    public static void main(String[] args){
        new PE474().solve();
    }
    
    int D=65432/8;   
    int N=1000000;
    int M1=100000/8;
    long M2=10000000000000061L;
    
    void solve(){
        int[] pr=Util.primes(N);
        Map<Integer,Long> m1,m2,m3;
        m1=new HashMap<>();
        m1.put(1,1L);
        for(long p:pr){
            if(p==2||p==5) continue;
            m2=new HashMap<>();
            int e=0,f=0,k=1,n=N;
            while(n>0) f+=(n/=p);
            while(e++<=f){
                Long v=m2.get(k);
                if(v==null) v=0L;
                m2.put(k,++v);
                k=(int)((p*k)%M1);                
            }
            m3=new HashMap<>();
            for(int k1:m1.keySet()){
                for(int k2:m2.keySet()){
                    k=(k1*k2)%M1;
                    Long v=m3.get(k);
                    if(v==null) v=0L;
                    v+=m1.get(k1)*m2.get(k2);
                    m3.put(k,v%M2);
                }
            }
            m1=m3;
        }
        System.out.println(m1.get(D));
    }
    
}