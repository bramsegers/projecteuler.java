package P401_P450;

import java.util.stream.LongStream;
import util.Primes;
import util.Util;

public class PE437{

    /*
        http://www.fq.math.ca/Scanned/15-4/deleon.pdf
    
        "necessary and sufficient condition for a prime!=5 to have 
        a Fibonacci primitive root is p = 1 or 9 (mod 10) and  A(p) = p-1,
        where A(p) is the period of the Fibonacci numbers modulo p"
    
    */    
    public static void main(String[] args){
        new PE437().solve(10000);
        new PE437().solve(100000000);
    }
      
    void solve(int n){   
        Primes p1=new Primes(n);
        int[] p2=Util.primes(n);
        long t=LongStream
            .range(4,p2.length)
            .parallel()
            .map(i->{
                int p=p2[(int)i];
                if(p%10!=1&&p%10!=9) return 0;
                return primitive(p,p1)  ?p :0;
            })
            .sum();
        System.out.format("P(%d)=%d%n",n,t+5);
    }
    
    boolean primitive(long p,Primes pr){        
        for(long d:pr.divs(p-1,0,0)){
            long[] f=Util.fibmod(d,p);
            if(f[0]==1&&f[1]==0) return false;
        }
        return true;
    }

}