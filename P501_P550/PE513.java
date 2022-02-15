package P501_P550;

import static java.lang.Math.min;
import static util.Util.gcd;

public class PE513{

    // Parametric solution for all triangles with integer median is:
    // a=ps+de+es
    // b=ps+de+2pd
    // c=2de+2dp+es
    // where gcd(p,e)=1 and es is even
    public static void main(String[] args){
        new PE513().solve(10);
        new PE513().solve(50);
        new PE513().solve(100000);
    }

    void solve(long n){
        long e,p,d,m,f=0;
        for(e=1;e+4<=n;e++)
            for(p=1;3*e+2*p<=n;p++)
                if(gcd(p,e)==1)
                for(d=1;2*d*(e+p)+e<=n;d++){
                    m=min(2*p*d,(n-2*d*(e+p)))/e;
                    if(p>e) m=min(m,e*d/(p-e));
                    f+=((e&1)==1)?m/2:m;
                }
        System.out.format("F(%d)=%d%n",n,f);
    }

}
