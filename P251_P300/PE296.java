package P251_P300;

import static util.Util.print;
import static java.lang.Math.min;
import static util.Util.gcd;

public class PE296 {

    public static void main(String[] args) {
        new PE296().solve(100000);
    }

    void solve(long N) {
        long a,b,c,f,m,count=0;
        for (a=1;a+a+a<=N;a++) { 
            for (b=a;a+b+b<=N;b++) {
                f=b; 
                m=(a+b)/gcd(a,b);
                c=min(N-a-b,a+b-1);
                if(f%m>0) f+=m-(b%m);
                if(f<=c) count+=(c-f)/m+1; 
            }
        }
        print(count);        
    }

}
