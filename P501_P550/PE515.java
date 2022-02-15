package P501_P550;

import util.Primes;
import util.Util;

public class PE515 {

    public static void main(String[] args){
        new PE515().solve(1000000000,100000,100000);
    }

    void solve(int a,int b,int k){
        long sum=0;
        long sq=(long)Math.sqrt(a+b);
        Primes pr=new Primes(sq);
        for(int p=a;p<a+b;p++)
            if(pr.isPrime(p)) 
                sum+=Util.modInv(k-1,p);
        System.out.println(sum);
    }

}