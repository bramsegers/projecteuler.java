package P601_P650;

import java.math.BigInteger;
import java.util.stream.IntStream;
import static util.Util.bi;
import static util.Util.print;
import static util.Util.modPow;
import static util.Util.primes;

public class PE646{

    // 845218467
    // (1016 minutes 39 seconds)
    public static void main(String[] args){
        new PE646().solve();
    }
    
    int N=70,M=1000000007;
    BigInteger L=bi(10).pow(20);
    BigInteger H=bi(10).pow(60);
    int[] PR,F;
    
    void solve(){
        PR=primes(N);
        int m=PR.length;
        F=new int[m];
        for(int i=0;i<m;i++) 
            F[i]=f(N,PR[i]);
        print(F);
        long t=IntStream
            .range(0,F[0]+1)
            .parallel()
            .mapToLong(i->{
                long s=s(
                    bi(2).pow(i),
                    modPow(2L,i,M),
                    m-1,
                    i&1);
                print(i,s);
                return s;
            })
            .sum();
        t=((t%M)+M)%M;
        print(t);                
    }
    
    int f(int n,int p){
        return n<p?0:n/p+f(n/p,p);
    }
    
    long s(BigInteger p,long mp,int i,int e){
        if(i==0) return 
            p.compareTo(L)<0?0:e==0?mp:-mp;
        long s=0;
        for(int x=0;x<=F[i];x++){
            s=(s+s(p,mp,i-1,(e+x)&1))%M;
            p=p.multiply(bi(PR[i]));
            if(p.compareTo(H)>0) break;
            mp=(mp*PR[i])%M;
        }
        return s;
    }
    
}