package P451_P500;

import java.util.Set;
import java.util.stream.LongStream;
import util.Primes;

public class PE454{

    public static void main(String[] args){
        new PE454().F((long)1e3);
        new PE454().F((long)1e12);
    }

    void F(long L){
        long M=(long)Math.sqrt(L);
        Primes P=new Primes(M);
        System.out.format(
            "F(%d)=%d%n",L,
            LongStream
                .range(1,M+1)
                .parallel()
                .map(x->f(x,L,P))
                .sum()
        );
    }

    long f(long x,long N,Primes P){
        Set<Long> factors=P.factors(x);
        long sum=0,y0=x+1,y1,n;
        while((n=N/(y0*(x+y0)))>0){
            y1=(long)(Math.sqrt(1D*N/n+x*x/4D)-x/2D);
            int t=(int)(y1-y0+1);
            boolean[] a=new boolean[t];
            for(long p:factors)
                for(long q=(p-(y0%p))%p;q<=y1-y0;q+=p){
                    if(!a[(int)q]) t--;
                    a[(int)q]=true;
                }
            sum+=n*t;
            y0=y1+1;
        }
        return sum;
    }

}