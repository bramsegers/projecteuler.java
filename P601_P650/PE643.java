package P601_P650;

import util.Primes;

public class PE643{

    //968274154 (36 minutes 30 seconds)
    public static void main(String[] args){
        new PE643().solve();
    }
    
    long N=100000000000L;
    long M=1000000007L;
    Primes P=new Primes(N/4);    
    
    void solve(){        
        long a=tri(N/2-1),m=1,s;
        for(int t=1;(s=S(t,2,3))>0;t++)
            a=(a+((m=-m)*s)+M)%M;
        System.out.println(a);
    }
    
    long S(int t,long n,long p){
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