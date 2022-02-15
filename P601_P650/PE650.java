package P601_P650;

import java.util.Map;
import java.util.TreeMap;
import util.Primes;
import util.Util;

public class PE650{   
                       
    // 1. B(n) = B(n-1) * n^n / n!
    // 2. sum of divors of ∏i pi^ei = ∏i (pi^(ei+1)-1)/(pi-1)
    public static void main(String[] args){
        new PE650().S(100,1000000007);
        new PE650().S(20000,1000000007);
    }
    
    void S(int n,int mod){
        long S=0;
        Primes pr=new Primes(n);
        Map<Long,Integer> F,B=new TreeMap<>();
        for(int i=1;i<=n;i++){
            F=pr.factorize(i);
            for(long p:F.keySet()){
                Integer e=B.get(p);
                if(e==null) e=0;
                B.put(p,i*F.get(p)+e);
            }
            for(long p=2;p>0&&p<=i;p=pr.next(p)){
                int f=0,n2=i,e=B.get(p);
                while(n2>=p) f+=(n2/=p);               
                B.put(p,e-f);
            }
            long t,D=1;
            for(long p:B.keySet()){
                t=Util.modPow(p,1+B.get(p),mod)-1;
                t=(t*Util.modInv(p-1,mod))%mod;
                D=(D*t)%mod;
            }
            S=(S+D)%mod;
        }
        System.out.format("S(%d) mod %d = %d%n",n,mod,S);
    }

}