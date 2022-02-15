package P401_P450;

import java.util.Arrays;
import java.util.stream.LongStream;
import util.Util;

public class PE432{

    static long M=1000000000L;
    static long N=100000000000L;
    static int S=(int)Math.sqrt(N);
    static int[] P=Util.primes(S);

    //754862080 (74min 57sec on i5 4core @3.2GHz)
    public static void main(String[] args){
        long sum=LongStream
            .range(0,(N+S-1)/S)
            .parallel()
            .map(i->solve(i*S,S))
            .sum()%M;
        System.out.println((sum*92160)%M);
    }

    static long solve(long k,int s){
        if(N-k<s) s=(int)(N-k);
        long q,r,sum=0;
        long[] a=new long[s+1];
        long[] b=new long[s+1];
        Arrays.fill(a,1);
        for(int i=1;i<=s;i++) b[i]=k+i;
        for(long p:P){
            for(q=p-(k%p);q<=s;q+=p){
                   a[(int)q]*=p;
                   if(p>17){
                       b[(int)q]/=p;
                       b[(int)q]*=p-1;
                   }
            }
            for(q=p*p;q<=k+s;q*=p)
                for(r=q-(k%q);r<=s;r+=q)
                   a[(int)r]*=p;
        }
        for(int i=1;i<=s;i++){
            if(a[i]!=k+i){
                q=(k+i)/a[i];
                b[i]/=q;
                b[i]*=q-1;
            }            
            sum+=b[i];
            sum%=M;
        }
        return sum;
    }   

}