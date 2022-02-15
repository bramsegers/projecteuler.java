package P651_P700;

import static util.Util.modInv;
import static util.Util.modPow;

public class PE657{

    public static void main(String[] args){
        new PE657().solve((long)1e7,(long)1e12,1000000007);
    }

    void solve(long a,long n,long m){
        long sum=modPow(a,n+1,m)-1;
        sum=(sum*modInv(a-1,m))%m;
        for(long t,bin=1,i=0;i<a;i++){
            t=modPow(a-i,n,m)-1;
            t=(t*modInv(a-i-1,m))%m;
            t=(a-i==1)?(n%m):t;
            t=(t*((a*bin)%m))%m;
            bin=(bin*(a-i-1))%m;
            bin=(bin*modInv(i+1,m))%m;
            sum=(sum+((i%2==0)?m-t:t))%m;
        }
        System.out.format("I(%d,%d)=%d%n",a,n,sum);
    }

}