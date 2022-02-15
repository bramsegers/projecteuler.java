package P401_P450;

import static java.lang.Math.abs;
import static util.Util.gcd;

public class PE404{

    public static void main(String[] args){
        new PE404().solve((long)1e3);
        new PE404().solve((long)1e4);
        new PE404().solve((long)1e6);
        new PE404().solve((long)1e17);
    }

    void solve(long N){
        long x,y,z,a,b,c,m,n,C=0;
        for(m=2;m*m*m*m<=N;m++){
            for(n=(m&1)+1;n<m;n+=2){
                x=abs(4*m*n-m*m+n*n);
                y=abs(2*m*n+2*m*m-2*n*n);
                z=m*m+n*n;
                a=x*y/2;
                b=x*z;
                c=y*z;
                if(a<b&&a<c&&b<2*a&&c<2*a&&a<=N&&gcd(a,gcd(b,c))==1) C+=N/a;
                x=abs(4*m*n+m*m-n*n);
                y=abs(2*m*n-2*m*m+2*n*n);
                a=x*y/2;
                b=x*z;
                c=y*z;
                if(a<b&&a<c&&b<2*a&&c<2*a&&a<=N&&gcd(a,gcd(b,c))==1) C+=N/a;
            }
        }
        System.out.format("C(%d)=%d%n",N,C);
    }

}
