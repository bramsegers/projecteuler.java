package P451_P500;

import static util.Util.modInv;
import static util.Util.modPow;
import static util.Util.primes;

public class PE457 {
    
    public static void main(String[] args){
        new PE457().solve(10000000);
    }
    
    void solve(int N){
        long s=0,n,p2,r,t,c1,c2,n1,n2;
        int[] pr=primes(N);
        for(long p:pr){
            if(p<=13){
                for(n=1;n<=p*p;n++)
                    if((n*n-3*n-1)%(p*p)==0) break;
                s+=(n<=p*p)?n:0;
            }
            if(p>13 && modPow(13,(p-1)/2,p)!=p-1){
                p2=p*p;
                r=shanks(13,p,pr);
                t=((13-r*r)/p*modInv(2*r,p))%p;
                t=(t+p)%p;
                c1=p*t+r; 
                c2=p2-c1;
                n1=c1%2==0?(c1+3+p2)/2:(c1+3)/2;
                n2=c2%2==0?(c2+3+p2)/2:(c2+3)/2;
                s+=n1<n2?n1:n2;
            }
        }
        System.out.format("SR(%d)=%d%n",N,s);
    }

    long shanks(long a,long p,int[] pr){
        if((p&3)==3) return modPow(a,(p+1)/4,p);
        long x,b,g,h,n=0,e=0,s=p-1;
        while((s&1)==0){s/=2;e++;}
        for(long q:pr){
            x=modPow(n=q,(p-1)/2,p);
            if(x==p-1) break;
        }
        x=modPow(a,(s+1)/2,p);
        b=modPow(a,s,p);
        g=modPow(n,s,p);
        for(long t=b,m,r=e;;t=b){
            for(m=0;m<r&&t!=1;m++) t=(t*t)%p;
            h=modPow(g,1<<(r-m-1),p);
            if(m==0) return x;
            g=(h*h)%p;
            x=(x*h)%p;
            b=(b*g)%p;
            r=m;
        }
    }

}


