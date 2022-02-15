package P451_P500;

import util.Util;

public class PE496{

    public static void main(String[] args){
        new PE496().solve2((int)1e9);
        new PE496().solve((int)1e9); //2042473533769142717 (37 minutes 34 seconds)
    }

    int N,pi;
    int[] pr;

    void solve(int n){
        N=n;
        pr=Util.primes(N);
        pi=pr.length;
        long f=solve(0,1,0,new long[64]);
        System.out.format("F(%d)=%d%n",n,f);
    }

    long solve(int i,long p,int s,long[] sig){
        long rv=solve(1,p,0,s,sig);
        for(int j=i;j<pi&&p*pr[j]<=N;j++)
            for(long e=0,q=p*pr[j];q<=N;q*=pr[j]){
                sig[s]=pr[j];
                sig[s+32]=(e+=2);
                rv+=solve(j+1,q,s+1,sig);
            }
        return rv;
    }

    long solve(long q,long p,int i,int s,long[] sig){
        if(q>=p) return 0;
        long r,rv=valid(p,p*p/q-q,q)?p:0;
        for(int j=i;j<s;j++){
            if((r=q*sig[j])>p) break;
            for(int e=1;e<=sig[j+32]&&r<=p;e++){
                rv+=solve(r,p,j+1,s,sig);
                r*=sig[j];
            }
        }
        return rv;
    }

    boolean valid(long p,long b,long c){
        if(p>b) return valid(b,p,c);
        if(b>c) return valid(p,c,b);
        return p+b>c;
    }


    void solve2(long n){
        long f=solve2(2,1,n)+solve2(3,1,n);
        System.out.format("F(%d)=%d%n",n,f);
    }

    long solve2(long m,long n,long N){
        long k=m*(m+n),rv=0;
        if(k<=N){
            rv+=k*(N/k)*(N/k+1)/2;
            rv+=solve2(2*m-n,m,N);
            rv+=solve2(2*m+n,m,N);
            rv+=solve2(m+2*n,n,N);
        }
        return rv;
    }
}