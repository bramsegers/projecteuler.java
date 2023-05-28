package P601_P650;

import util.Primes;
import java.util.stream.LongStream;

public class PE643{

    //968274154 (6 minutes 40 seconds)
    public static void main(String[] args){
        new PE643().solve();
    }
    
    long N=100000000000L;
    long M=1000000007L;
    Primes P=new Primes(N/4);    
    
    void solve(){
      System.out.format(
        "f(%d) = %d%n",N,
        ((tri(N/2-1) 
         + LongStream
           .range(1,20)
           .parallel()
           .map(t->S(t,2,3)*(t%2>0?-1:1))
           .sum())%M+M)%M
      );
    }
    
    long S(long t,long n,long p){
        if(t==0) return tri(N/n-1); 
        long s=0;
        for(long q=p;q>0;q=P.next(q))
            if(Math.pow(q,t)*n*2>N) break;
            else s=(s+S(t-1,n*q,P.next(q)))%M;
        return s;
    }
        
    long tri(long n){
        return (n&1)==0
            ?(((n/2)%M)*((n+1)%M))%M
            :((n%M)*(((n+1)/2)%M))%M;
    }

}
