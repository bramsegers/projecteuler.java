package P451_P500;

import static util.Util.modInv;
import static util.Util.modPow;

public class PE479 {

    /*    
        1. Let q(k)=(a+b)(a+c)(b+c)
        2. Calculate q(k) for small values of k using Laguerre solver
        3. Observe pattern q(k)=1-k^2
        4. Use closed formula for geometric series

             n    n              n   q(k)*(q(k)^n-1)
             Σ    Σ  q(k)^p  =   Σ   ---------------   
            k=1  p=1            k=1      q(k)-1    
    */    
    public static void main(String[] args) {
        new PE479().solve(1000000,1000000007);        
    }
    
    void solve(long n,long m){
        long q,s,S=0;
        for(long k=1;k<=n;k++){
            q=1-k*k;
            q=((q%m)+m)%m;
            s=((modPow(q,n,m)-1)*q)%m;
            s=(s*modInv(q-1,m))%m;
            S=(S+s)%m;
        }
        System.out.format("S(%d) mod %d = %d%n",n,m,S);       
    }
}
