package P401_P450;

import java.util.Map;
import java.util.HashMap;
import util.Primes;
import static util.Util.lcm;
import static util.Util.modPow;

public class PE417{

    public static void main(String[] args){
        new PE417().solve((int)1e6);
        new PE417().solve((int)1e8);
    }
    
    int N;    
    Primes pr;
    Map<Long,Long> map;
    
    void solve(int n){
        N=n;
        pr=new Primes(N);
        map=new HashMap<>();
        for(long p=3;p>0;p=pr.next(p))
            for(long pk=p,t=p-1;pk<=N;pk*=p,t*=p)
                map.put(pk,L(t,pk));
        System.out.format("∑L(%d)=%d%n",N,sum(3,1,1));        
    }
    
    long L(long t,long pk){
        for(long d:pr.divs(t))
            if(modPow(10,d,pk)==1) return d;
        return 0;
    }
    
    long sum(long p,long n,long lcm){
        long q,pk,n2,rv=count(n)*lcm;
        for(q=p;q>0&&n*q<=N;q=pr.next(q))
            for(pk=q,n2=n*q;q!=5&&n2<=N;pk*=q,n2*=q)
                rv+=sum(pr.next(q),n2,lcm(lcm,map.get(pk)));
        return rv;        
    }
    
    long count(long n){
        long n2,n5,t=0;
        for(n2=n;n2>1&&n2<=N;n2*=2)
            for(n5=n2;n5<=N;n5*=5) t++;
        return t;
    }
    
}
