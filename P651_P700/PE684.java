package P651_P700;

import util.Util;

public class PE684 {

    public static void main(String[] args){
        new PE684().solve(90,1000000007);
    }

    void solve(int n,int mod){
        long sum=0;
        long f0=0,f1=1,f;
        for(int i=2;i<=n;i++){
            f=f0+f1;
            f0=f1;
            f1=f;
            sum+=S(f,mod);
            sum%=mod;
        }
        System.out.println(sum);
    }

    long S(long n,long mod){
        long q=n/9;
        long r=n-9*q;
        long S=(r*(r+3)/2+6);
        S*=Util.modPow(10,q,mod);
        S-=(9*q+r+6)%mod;
        S=(S+mod)%mod;
        return S;
    }

}
