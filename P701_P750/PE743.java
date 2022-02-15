package P701_P750;

import static util.Util.modInv;
import static util.Util.modPow;

public class PE743 {

    public static void main(String[] args) {
        new PE743().solve(3,9,1000000007);
        new PE743().solve(4,20,1000000007);
        new PE743().solve((int)1e8,(long)1e16,1000000007);
    }
        
    void solve(int k, long n,long m){
        long A=0,p,q;
        long r=modPow(2,n/k,m);
        long s=modInv(r*r,m);
        long t=modPow(r,k,m);
        long[] f=new long[k+1];
        for(int i=0;i<=k;i++)
            f[i]=i<2?1:(f[i-1]*i)%m;
        for(int i=0;i<=k/2;i++){
            p=(t*f[k])%m;
            t=(t*s)%m;
            q=(f[i]*f[i])%m;
            q=(q*f[k-2*i])%m;
            A=(A+p*modInv(q,m))%m;
        }
        System.out.println(A%m);
    }
}