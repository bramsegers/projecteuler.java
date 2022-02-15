package P351_P400;

import util.Util;

public class PE396{

    public static void main(String[] args){
        new PE396().solve();
    }

    int N=16;
    int M=1000000000;

    void solve(){
        long sum=0;
        for(int n=1;n<N;n++){
            long G=G(n);
            sum=(sum+G)%M;
            System.out.format("G(%d)=%d%n",n,G);
        }
        System.out.println("Σ="+sum);
    }

    long G(int x){
        long b=3;
        if((x&1)>0) b=(b+1)%M;
        if((x&2)>0) b=(b+b)%M;
        if((x&4)>0) b=(b*(Util.modPow(2,b,M)))%M;
        if((x&8)>0) for(long i=b;i>0;i--) b=(b*(Util.modPow(2,b,M)))%M;
        return (b+M-3)%M;
    }

}