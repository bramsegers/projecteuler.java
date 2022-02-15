package P401_P450;

import static util.Util.gcd;

public class PE410 {

    public static void main(String[] args) {
        new PE410().solve(100000000,1000000000);
    }

    void solve(long R,long X){
        long s=0;
        long L=R<X?R:X;
        for(long n=1;n*n<L;n++){
            for(long m=n+1;n*m<=L;m+=2-(n&1)){
                if(gcd(n,m)>1) continue;
                long p=R/n/m;
                long q=X/n/m;
                long r=(m+n)&1;
                s+=(p*q+r)/(r+1);
            }
        }
        long a=2*(4*s+2*R*X);
        System.out.println(a);
    }

}
