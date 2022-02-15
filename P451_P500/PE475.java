package P451_P500;

import static util.Util.modCh;
import static util.Util.modFac;
import static util.Util.modInv;
import static util.Util.modMul;

public class PE475{

    public static void main(String[] args){
        new PE475().f(12);
        new PE475().f(24);
        new PE475().f(600);
    }

    long[][] chm;
    long[][][] mem;
    long mod=1000000007;

    void f(int n){
        int q=n/3;
        mem=new long[q+1][q+1][q+1];
        mem[0][0][0]=1;
        chm=new long[q+1][5];
        for(int i=0;i<=q;i++)
            for(int j=0;j<=4;j++)
                chm[i][j]=modCh(i,j,mod);
        long s=ways(q,0,0);
        long m=modInv(modFac(q,mod),mod);
        System.out.format("f(%d)=%d%n",n,(s*m)%mod);
    }

    long ways(int t0,int t1,int t2){
        long w=mem[t0][t1][t2];
        if(w>0) return w;
        for(int i=0,j,k;i<=4;i++)
            for(j=0,k=4-i;i+j<=4;j++,k--)
                if(i<=t0&&j<=t1&&k<=t2)
                    w=(w+modMul(
                        24,
                        chm[t0][i],
                        chm[t1][j],
                        chm[t2][k],
                        ways(t0-i,t1+i-j,t2+j-k)
                    ,mod))%mod;
        mem[t0][t1][t2]=w;
        return w;
    }

}