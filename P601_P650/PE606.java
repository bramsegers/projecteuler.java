package P601_P650;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import static util.Util.bi; 
import static util.Util.primes;

public class PE606{

    // 32127889716676327217544526040207578811158452775
    // (total time: 48 minutes 39 seconds)
    public static void main(String[] args){
        new PE606().solve((long)1e12);
    }
    
    void solve(long n){
        int[] pr=primes((int)Math.sqrt(n));
        BigInteger q,sum=bi(0);
        for(long p:pr){
            System.out.println(p);
            q=sumLucy3(n/p);
            q=q.subtract(sumLucy3(p));
            q=q.multiply(bi(p).pow(3));
            sum=sum.add(q);
        }
        System.out.println(sum);
    }
    
    BigInteger sumLucy3(long n){
        BigInteger b,s;
        int r=(int)Math.sqrt(n);        
        ArrayList<Long> V=new ArrayList<>();
        for(int i=1;i<=r;i++) V.add(n/i);
        for(long i=V.get(V.size()-1)-1;i>0;i--) V.add(i);        
        HashMap<Long,BigInteger> S=new HashMap<>();
        for(long i:V){
            b=bi(i).multiply(bi(i+1));
            b=b.multiply(b).divide(bi(4));
            S.put(i,b.subtract(bi(1)));
        }
        for(long p=2;p<=r;p++){
            if(S.get(p).compareTo(S.get(p-1))>0){
                long q=p*p;
                s=S.get(p-1);
                for(long v:V){
                    if(v<q) break;
                    b=S.get(v/p).subtract(s);
                    b=bi(p).pow(3).multiply(b);
                    b=S.get(v).subtract(b);
                    S.put(v,b);
                }
            }
        }
        return S.get(n);
    }

}