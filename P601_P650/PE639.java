package P601_P650;

import java.util.Arrays;
import util.Util;

public class PE639{

    // 797866893
    // ~150 CPU hours
    public static void main(String[] args){
        solve();
    }
    
    static final long N=(long)1e12, M=1000000007, E=50;
    static final int P=100000,S=(int)(N/P);
    static final int[] PR=Util.primes((int)Math.sqrt(N));
    static final int[][] PF=new int[S+1][11];
    static int[] np=new int[S+1];
    
    static void solve(){
        long s,ts=0;
        for(long k=0L*S;k<N;k+=S){
            Arrays.fill(np,0);    
            for(int p:PR){
                int p0=p-(int)(k%p);
                for(int q=p0;q<=S;q+=p)
                    PF[q][np[q]++]=p;               
            }
            s=0;
            for(int i=1;i<=S;i++)
                s=(s+sgn(k+i,np[i],PF[i]))%M;
            ts=(ts+s)%M;
            System.out.println(k/S+"/"+P+" "+s);            
        }
        System.out.println(ts);
    }
    
    static long sgn(long n,int np,int[] pf){
        long p=1,q=1,sum=0;
        for(int i=0;i<np;i++){
            p*=pf[i];
            while(n%pf[i]==0) n/=pf[i]; 
        }
        if(n>1) p*=n;
        p%=M;
        for(int i=0;i<E;i++){
            q=(q*p)%M;
            sum+=q;
        }
        return sum%M;
    }
    
}