package P601_P650;

import util.Util;

public class PE604 {

    public static void main(String[] args){
        long N=(long)1e18;
        System.out.println(F(N));
    }

    static long F(long N){
        Util.initPrimes(10000);
        long d,r=0,s=0,n=2*N;
        while(true){
            d=Util.totient(++s);
            if(d*s<=n){r+=d;n-=d*s;}
            else return r+n/s;
        }
    }

}
