package P701_P750;

import static util.Util.modInv;

public class PE709 {

    // f(n) = a000111(n)
    // https://oeis.org/A000111
    
    int N=24680;
    int M=1020202009;

    void solve(){
        
        long im2=modInv(2,M);
        long[] a=new long[N+1];
        long[] f=new long[N+1];
        long[] g=new long[N+1];
        a[0]=a[1]=1;
        f[0]=g[0]=1;
        
        for(int n=1;n<=N;n++){
            f[n]=(f[n-1]*n)%M;
            g[n]=modInv(f[n],M);
        }
        
        for(int n=2;n<=N;n++){
            for(int k=0;k<n;k++){
                long b=(a[k]*a[n-k-1])%M;
                long c=(f[n-1]*((g[k]*g[n-1-k])%M))%M;
                a[n]=(a[n]+b*c)%M;
            }
            a[n]=(a[n]*im2)%M;
        }
        
        System.out.println(a[N]);
        
    }

    
    public static void main(String[] args){
        new PE709().solve();
    }

}
