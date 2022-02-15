package P401_P450;

import util.Primes;
import util.Util;

public class PE443{

    public static void main(String[] args){
        new PE443().solve((long)1e6,5,13);
        new PE443().solve((long)1e15,5,13);
    }
    
    void solve(long N,long n,long g){
        int M=(int)Math.sqrt(N);
        Primes P=new Primes(M);
        while(true){
            long p=0,d=g-n,x=0,y=0;
            while((p=P.next(p))>0&&p*p<=d){
                if(d%p!=0) continue;
                y=(n%p==0)?n:n+p-n%p;
                if(x==0||x>y) x=y;
                do{d/=p;} while(d%p==0);              
            }
            if(d>1) y=(n%d==0)?n:n+d-n%d;
            if(x==0||x>y) x=y;
            if(x>N) g+=N-n+1;
            if(x>N) break;
            g+=x-n;
            g+=Util.gcd(x,g);
            if(x==N) break;
            n=x+1;
        }
        System.out.format("g(%d)=%d%n",N,g);
    }

}
