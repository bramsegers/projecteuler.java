package P501_P550;

import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;
import static util.Util.gcd;

public class PE510 {

    //http://euler.stephan-brumme.com/510/
    public static void main(String[] args) {
        new PE510().solve((long)1e9);
    }

    void solve(long N) {
        long sum=0,k,g,m;
        for (long i=1;i*i<=N;i++) {
            for (long j=1;j<=i;j++) {
                if((i*j)%(i+j)==0){
                    k=(i*j)/(i+j);
                    g=gcd(i*i,j*j);
                    g=gcd(g,k*k);
                    if(g==1){
                        m=N/(i*i);
                        sum+=m*(m+1)/2*(i*i+j*j+k*k);
                    }
                }                
            }
        }
        System.out.println(sum);
    }

}
