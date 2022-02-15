package P651_P700;

import util.Util;

public class PE668 {

    public static void main(String[] args){
        System.out.println(new PE668().LPF(1,0,0));
    }
    
    long N=10000000000L;
    int[] P=Util.primes((int)Math.sqrt(N));
    int L=P.length;
    
    long LPF(long p,int i,long f){
        long rv=f*f<p?1:0;
        for(int j=i;j<L&&p*P[j]<=N;j++)
            rv+=LPF(p*P[j],j,P[j]);        
        return rv;
    }
    
}
