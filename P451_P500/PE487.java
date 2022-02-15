package P451_P500;

/*
           n                            n         n
     1)    ∑ (n+1−i)*i^p   =   (n+1) * ∑ i^p  -  ∑ i^(p+1)
          i=0                          i=0       i=0

     2)    Faulhaber's formula

*/

import util.Primes;
import static util.Util.faulhaber;

public class PE487{

    static int P=10000;
    static long N=1000000000000L;
    static long p1=2000000000;
    static long p2=2000002000;

    public static void main(String[] args){
        long sum=0,q=(int)Math.sqrt(p2);
        Primes pr=new Primes(q);
        for(long t,p=p1;p<=p2;p++){
            if(pr.isPrime(p)){
                t=faulhaber(N,P,p);
                t=(t*((N+1)%p))%p;
                t=(t-faulhaber(N,P+1,p)+p)%p;
                sum+=t;
            }
        }
        System.out.println(sum); // 106650212746, 4min 11sec
    }

}
