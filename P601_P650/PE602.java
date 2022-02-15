package P601_P650;

import static util.Util.modInv;
import static util.Util.modMul;
import static util.Util.modPow;

public class PE602{

    public static void main(String[] args){
        new PE602().solve(100,40,1000000007);
        new PE602().solve(10000000,4000000,1000000007);
    }

    //            k
    //  c(n,k) =  ∑ (−1)^i * choose(n+1,i) * (n−i)^n
    //           i=0
    void solve(long n,long k,long m){
        long s,ch=1,sum=0;
        for(long i=0;i<=k;i++){
            s=modMul(ch,modPow(k-i,n,m),m);
            sum=(i&1)==0?(sum+s)%m:(sum-s)%m;
            ch=modMul(ch,n-i+1,modInv(i+1,m),m);
        }
        System.out.format("c(%d,%d)=%d%n",n,k,(sum+m)%m);
    }

}