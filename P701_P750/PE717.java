package P701_P750;

import java.math.BigInteger;
import util.Util; 

public class PE717 {

    public static void main(String[] args){
        new PE717().solve(10000000);
    }
    
    void solve(int N){
        int[] pr=Util.primes(N);
        BigInteger sum=BigInteger.valueOf(0);
        BigInteger one=BigInteger.valueOf(1);
        BigInteger two=BigInteger.valueOf(2);
        for(int i=1;i<pr.length;i++){
            BigInteger p=BigInteger.valueOf(pr[i]);
            BigInteger a=two.modPow(p,p.multiply(p.subtract(one)));
            BigInteger b=(a.subtract(p)).mod(p.multiply(p.subtract(one)));
            BigInteger c=two.modPow(a,p.multiply(p)).divide(p);
            BigInteger d=two.modPow(b,p.multiply(p)).divide(p);
            BigInteger e=(c.subtract(two.multiply(d))).mod(p);
            sum=sum.add(e);
        }
        System.out.println(sum);
    }
   
    
}
