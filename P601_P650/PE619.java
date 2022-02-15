package P601_P650;

import static util.Util.modPow;
import static util.Util.primes;

public class PE619{

    public static void main(String[] args){
        new PE619().solve();
    }
    
    int A=1000000;
    int B=1234567;
    int M=1000000007;

    void solve(){
        long s,c=0;
        for(long p:primes(B)) 
            if(A/p!=(B+1)/p) c++;
        s=modPow(2,B-A-c+1,M)-1;
        System.out.println(s);
    }

}
