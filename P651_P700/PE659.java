package P651_P700;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.LongStream;
import static util.Util.initPrimes;
import static util.Util.tonelliShanks;
import static util.Util.isPrime;
import static util.Util.primes;

public class PE659{

    // https://oeis.org/A145689
    public static void main(String[] args){     /*        
        new PE659().brute(10000000);            // 128238518915714422000  189 mninutes @ i5 on 4-cores */
        new PE659().solve(10000000,(long)1e18); //    238518915714422000  5 seconds
    }

    void solve(int K,long mod){
        long[] a=new long[K+1];
        long[] b=new long[K+1];
        for(int k=1;k<=K;k++)
            a[k]=4L*k*k+1;
        for(int p:primes(2*K+1)){
            if(p%4!=1) continue;
            long[] t=tonelliShanks(p-1,p);
            long r=(t[0]%2==0?t[0]:t[1])/2;
            for(int i=0;i<2;i++,r=p-r)
                for(int k=(int)r;k<=K;k+=p){
                    while(a[k]%p==0) a[k]/=p;
                    b[k]=p;
                }
        }
        long sum=0;
        for(int k=1;k<=K;k++)
            sum=(sum+(a[k]==1?b[k]:a[k]))%mod;
        System.out.println("∑="+sum);
    }


    int lim;
    long[] pr;
    BigInteger SUM=BigInteger.ZERO;

    void brute(int K){
        lim=2*K+1;
        initPrimes(lim);
        int j=0;
        pr=new long[lim];
        for(int i=1;i<lim;i+=4)
            if(isPrime[i]) pr[j++]=i;
        pr=Arrays.copyOfRange(pr,0,j);
        LongStream
            .range(1,K+1)
            .parallel()
            .map(k->hifactor(4*k*k+1))
            .forEach(f->add(f));
        System.out.println("∑="+SUM);
    }

    long hifactor(long n){
        for(long p:pr){
            if(n<lim&&isPrime[(int)n]) return n;
            while(n%p==0) if((n/=p)<lim&&isPrime[(int)n]) return n;
        }
        return n;
    }

    synchronized void add(long f){
        SUM=SUM.add(BigInteger.valueOf(f));
    }

}