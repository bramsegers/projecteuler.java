package P301_P350;

import util.Primes;
import util.Util;

public class PE350 {

    public static void main(String[] args){
        new PE350().solve();
    }

    long G=(long)1e6;
    long L=(long)1e12;
    long N=(long)1e18;
    long M=(long)Math.pow(101,4);

    Primes pri;
    long[] pow;


    void solve(){

        pri=new Primes(L/G);
        pow=new long[64];
        for(int e=0;e<64;e++)
            pow[e]=Util.modPow(e,N,M);

        long c,m,n,f=0;
        for(n=1;n<=L/G;n++){
            m=L/n;
            if(m>=G){
                c=(m-G+1)%M;
                f=(f+c*h(n))%M;
            }
        }

        System.out.println(f);
    }


    long h(long n){
        long p,h=1;
        for(int e:pri.exp(n)){
            p=pow[e+1];
            p-=2*pow[e];
            p+=pow[e-1];
            h=(h*p)%M;
        }
        return (h+M)%M;
    }

}
