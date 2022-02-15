package P351_P400;

import static util.Util.bi;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class PE388{

    public static void main(String[] args){
        System.out.println(new PE388().D(1000000L));
        System.out.println(new PE388().D(10000000000L));
    }
    
    Map<Long,BigInteger> map=new HashMap<>();
    
    BigInteger D(long n){
        if(map.containsKey(n)) return map.get(n);
        BigInteger v=bi(n+1).pow(3).subtract(bi(1));
        long cut=n<9?2:(long)Math.sqrt(n);
        for(long a=2;a<cut;a++) 
            v=v.subtract(D(n/a));
        for(long b=1;b<=n/cut;b++){
            BigInteger A=bi(n/b);
            BigInteger B=bi(n/(b+1));
            
            v=v.subtract((A.subtract(B)).multiply(D(b)));
        }
        map.put(n,v);
        return v;
    }
    
}