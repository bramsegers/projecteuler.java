package P401_P450;

import static util.Util.modPow;
import static util.Util.primes;

public class PE421 {

    public static void main(String args[]){
        new PE421().solve((long)1e11,(int)1e8);
    }

    void solve(long N,int M){
        long s=0;
        for (long p:primes(M))
            s+=p*count(p,N);        
        System.out.println("∑="+s);
    }

    long count(long p,long N){
        long g=1;
        if(p%5==1) g*=5;
        if(p%3==1) g*=3;
        if(g==1) return (N+1)/p;
        long e=(p-1)/(2*g);
        long c=(N/p)*g;
        long r=root(p);
        long m=N%p;
        for(int i=0;i<g;i++)
            if(modPow(r,e*(1+2*i),p)<=m) c++;
        return c;
    }

    long root(long p){
        for(long e=p/2,i=2;i<p;i++){
            if(          modPow(i,e,  p)!=p-1) continue;
            if(e%5==0 && modPow(i,e/5,p)==p-1) continue;
            if(e%3==0 && modPow(i,e/3,p)==p-1) continue;
            return i;
        }
        return 1;
    }

}
