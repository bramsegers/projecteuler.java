package P751_P800;

import util.Util;

public class PE753{

    public static void main(String[] args){
        new PE753().solve(6000000);
    }

    void solve(int N){
        int[] pr=Util.primes(N);
        long s=(N>=3)?12:0;
        for(long p:pr) s+=F(p);
        System.out.printf("S(%s)=%s%n",N,s);
    }
    
    long F(long p){
        return (p-1)*(p%3==2?(p-2):(f(p)+p-8));
    }
     
    long f(long p){
        for(long q=4*p-1,x=1;x*x<=4*p;x++,q-=2*x-1){
            if(q%27>0) continue;
            long s=(long)Math.sqrt(q/27);
            if(s*s==q/27) return(x*(3-2*(x%3)));
        }
        return 0;
    } 
}