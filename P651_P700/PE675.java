package P651_P700;

import java.util.HashMap;
import java.util.Map;
import util.Primes;
import util.Util;

public class PE675 {
   
    public static void main(String[] args) {
        new PE675().solve();
    }

    long N=10000000;
    long M=1000000087;

    void solve(){
        long F=0;
        Primes pr=new Primes(N);
        Map<Long,Long> map=new HashMap<>();
        for(long m=1,n=2;n<=N;n++){
            Map<Long,Integer> f=pr.factorize(n);
            for(long p:f.keySet()){
                long v=map.getOrDefault(p,0L);
                long w=v+f.get(p);
                map.put(p,w);
                m*=Util.modInv(1+2*v,M);
                m=((m%M)*(1+2*w)%M)%M;
            }
            F=(F+m)%M;
        }
        System.out.format("F(%d) mod %d = %d%n",N,M,F);
    }
}