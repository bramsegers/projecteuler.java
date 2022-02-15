package P451_P500;

import static util.Util.modChoose;

public class PE498{

    public static void main(String[] args){
        new PE498().solve(6,3,1,999999937);
        new PE498().solve(100,10,4,999999937);
        new PE498().solve((long)1e13,(long)1e12,10000,999999937);
    }
    
    
    void solve(long n,long m,long d,long mod){
        long s=(modChoose(n,d,mod)*modChoose(n-d-1,n-m,mod))%mod;
        System.out.format("C(%d,%d,%d) mod %d = %d%n",n,m,d,mod,s);
    }

}