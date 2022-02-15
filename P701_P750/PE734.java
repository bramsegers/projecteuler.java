package P701_P750;

import util.Util;

public class PE734 {

    public static void main(String[] args) {
        new PE734().solve();
    }

    //int N=100, K=3, M=1000000007;
    //int N=1000, K=10, M=1000000007;
    int N=1000000, K=999983, M=1000000007;

    void solve(){

        Util.initPrimes(N+1);
        int[] A=new int[N+1];
        long[] pow=new long[N];

        for(int n=1;n<=N;n++)
            for(int s=n;s>0;s=n&(s-1))
                if(Util.isPrime[s]) A[n]++;

        for(int n=0;n<N;n++)
            pow[n]=Util.modPow(n,K,M);

        long ans=0;
        for(int n=2;n<=N;n++)
            if(Util.isPrime[n])
                for(int p=parity(n),s=n;s>0;s=n&(s-1))
                    ans+=(parity(s)==p?1:-1)*pow[A[s]];
        System.out.println(((ans%M)+M)% M);
    }

    int parity(int n){
        int c=0;
        while(n>0){c+=n&1;n>>=1;}
        return c&1;
    }
}