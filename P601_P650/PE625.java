package P601_P650;

import java.util.HashMap;
import java.util.Map;

public class PE625 {

    public static void main(String[] args) {
        new PE625().solve(10);
        new PE625().solve(100000000000L);
    }

    long M=998244353;
    Map<Long,Long> dict=new HashMap<>();
    
    void solve(long n){
        long b,c=0,d=0,s=t(n);
        for(long a=1;a<=n;a++){
            b=n/a; a=n/b;
            if(c>0) s=(s+t(d)*(f(c)-f(b)))%M;
            d=a; c=b;
        }
        System.out.println(s);
    }
    
    long t(long n){
        return ((n&1)==0)
          ?(((n/2)%M)*((n+1)%M))%M
          :((n%M)*(((n+1)/2)%M))%M;
    }
    
    long f(long n){
        Long f=dict.get(n);
        if(f!=null) return f;
        long rv=t(n);
        long j=(long)Math.sqrt(n);
        for(long g=2;g<=j;g++){
            rv+=M-f(n/g);
            rv%=M;
        }
        for(long z=1;z<=j;z++){
            if(n/z==z) continue;
            rv+=M-(((((n/z)-(n/(z+1)))%M )*(f(z)%M))%M);
            rv%=M;
        }
        dict.put(n,rv);
        return rv;
    }   
    
}
