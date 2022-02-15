package P601_P650;

import java.util.BitSet;
import java.util.stream.LongStream;
import util.Util;

public class PE642{
      
    static long M=1000000000L;
    static long N=201820182018L;
    static int S=(int)Math.sqrt(N);
    static int[] P=Util.primes(S);
    static int L=P.length;

    //631499044 (12min 39sec on i5 4core 3.2GHz)
    //631499044 (25min 54sec on i5 2core 1.7GHz)
    public static void main(String[] args){
        long ans=LongStream
            .range(0,(N+S-1)/S)
            .parallel()
            .map(i->(i==0)
                ?LPF1(1,0,0)%M
                :LPF2(i*S)%M)
            .sum()%M;
        System.out.println(ans);
    }

    //largest prime factor <= √N
    static long LPF1(long p,int i,long f){
        long rv=f;
        for(int j=i;j<L&&p*P[j]<=N;j++)
            rv+=LPF1(p*P[j],j,P[j]);        
        return rv;
    }

    //largest prime factor  > √N
    static long LPF2(long k){
        BitSet b=new BitSet();
        b.set(1,S+1);
        for(int p:P){
            int q=-(int)(k%p);
            while((q+=p)<=S) b.clear(q);
        }
        return b.stream()
            .parallel()
            .filter(p->(p<=S&&k+p<=N))
            .mapToLong(p->((k+p)*(N/(k+p)))%M)
            .sum();
    }
    
}
