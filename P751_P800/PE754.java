package P751_P800;

import java.util.stream.LongStream;
import util.Primes;
import util.Util;

public class PE754 {

    public static void main(String[] args) {
        new PE754().solve();
    }

    int N=100000000;
    int M=1000000007;

    void solve(){
        Primes pr = new Primes(N);
        long ans=LongStream
            .range(2,N)
            .parallel()
            .map(n->Util.modPow(n,c(n,1,1,0,pr.factorsA(n)),M))
            .reduce((a,b)->(a*b)%M)
            .getAsLong();
        System.out.println(ans);
    }
    
    long c(long n,long p,int m,int i,long[] f){
        return (i==f.length) ? (N/p-n/p)*m :
        c(n,p,m,i+1,f) + c(n,p*f[i],-m,i+1,f);
    }

}
