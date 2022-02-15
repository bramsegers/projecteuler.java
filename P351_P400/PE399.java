package P351_P400;

import static java.lang.Math.*;
import util.Util;

public class PE399 {

    public static void main(String[] args) {
        new PE399().solve();
    }

    int SIEVE = 150000000;
    int PRIME = 10000000;
    int     N = 100000000;
    long  MOD = 10000000000000000L;
    
    void solve() {
        int[] primes=Util.primes(PRIME);
        boolean[] sieve=new boolean[SIEVE];
        for (int p:primes){
            int a=A(p);
            if(a<0) continue;
            for(int b=p*a,i=b;i<SIEVE;i+=b) 
                sieve[i] = true;
        }
        for (int c=0,i=1;i<SIEVE;i++){
            if(!sieve[i]&&++c==N){
                double phi=0.5+0.5*sqrt(5);
                double log=log10(phi);
                double lfv=0.5*log10(5);
                double llg=i*log-lfv;
                long exp=(long)floor(llg);
                double mant=pow(10,llg-exp);
                System.out.format("%d,%3.1fe%d\n",F(i),mant,exp);
            }
        }
    }

    long F(long k){
        long f0=1,f1=1,f2;
        for(int n=2;n<k;n++){
            f2=(f1+f0)%MOD;
            f0=f1;
            f1=f2;
        }
        return f1;
    }

    int A(long p){
        long f0=1,f1=1,f2;
        for(int n=3;;n++){
            f2=(f1+f0)%p;
            if(f2==0) return n;
            if(n*p>SIEVE) return -1;
            f0=f1;
            f1=f2;
        }
    }

}
