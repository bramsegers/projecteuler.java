package P251_P300;

import java.util.BitSet;
import util.Util;

public class PE257 {

    public static void main(String[] args) {
        new PE257().solve((int)1e8);
    }
    
    /*
    (a+b)(a+c)/bc = k (INTEGER)
    a<=b<=c, this means 2<=k<=4

    1. k=4 : this means a=b=c, so answer is N/3
    2. k=2 : (b-a)(c-a) = 2a^2
    3. k=3 : (2b-a)(2c-a) = 3a^2
    */
    
    int N, count;
    
    void solve(int n){
        N=n;
        count=N/3;
        BitSet primes=Util.getPrimesBS(N/3);
        for(int a=N/3;a>1;a--)
            count+=count(a,1,0,factorize(a,primes));
        System.out.printf("P(%d)=%d%n",N,count); 
    }
    
    int[][] factorize(int n, BitSet primes) {
        int[][] f=new int[2][10];
        int i=0,p=2;
        while(n>1){
            int e=0;
            while(n%p==0){
                n/=p;
                e++;
            }
            if(e>0){
                f[0][i]=p;
                f[1][i]=e;
                i++;
            }
            if(primes.get(n)){
                f[0][i]=n;
                f[1][i]=1;
                return f;
            }
            p=primes.nextSetBit(p+1);
        }
        return f;
    }
     
    long count(long a,long p,int pi, int[][] f){
        if(p>a) return 0;
        long rv=0,P=f[0][pi];
        if(P==0){
            rv+=k2(a,p*2,a*a/p);
            rv+=k3(a,p*3,a*a/p);
            return rv;
        }
        for(long pe=1,me=2*f[1][pi],e=0;e<=me;e++){
            rv+=count(a,p*pe,pi+1,f);
            pe*=P;
        }
        return rv;
    }
    
    long k2(long a,long p,long q){
        if(p>q && (q&1)==0) return 0;
        if(p>q) return k2(a,q,p);
        long b=p+a;
        long c=q+a;
        return(a+b+c<=N&&a+b>c)?1:0; 
    }
    
    long k3(long a,long p,long q){
        if(p>q && (q%3)==0) return 0;
        if(p>q) return k3(a,q,p);
        long b=p+a;
        long c=q+a;
        if((b&1)==1) return 0;
        if((c&1)==1) return 0;
        b>>=1; 
        c>>=1;
        return(a+b+c<=N&&a+b>c)?1:0; 
    }
   
}