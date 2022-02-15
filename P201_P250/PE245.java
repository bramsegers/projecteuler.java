package P201_P250;
 
import java.util.TreeMap;
import util.Primes;
import util.Util;

public class PE245 {

    public static void main(String[] args){
        //new PE245().brute();
        new PE245().solve();
    }
    
    // observations:
    // (1) n is odd
    // (2) n is square-free
    // (3) max factor of n = limit^(2/3)
    // (4) if n has 2 prime factors (n=p*q) then q<p*p
    void brute(){
        Primes pr=new Primes(N);
        for(long n=2;n<N;n++){
            long t=pr.totient(n);
            if((n-1)%(n-t)==0 && !pr.contains(n)){
                TreeMap<Long,Integer> f=pr.factorize(n);
                System.out.format("%d %d %d %s %n",n,n-1,n-t,f);
            }
        }
    }
   
    long N=(long)2e11;
    int s=(int)Math.pow(N,2d/3);                                                //(3)
    int[] pr=Util.primes(s);

    void solve(){
        long sum=0;
        sum+=semi();
        sum+=multi(0,1,1,1);                                                    //(1)
        System.out.println(sum);
    }
    
    long semi(){
        long p,q,sum=0;
        for(int i=1;(p=pr[i])*p<N;i++){                                         //(1)
            for(int j=i+1;p*(q=pr[j])<N;j++){                                   //(2)
                if(q>p*p) break;                                                //(4)
                if((p*q-1)%(p+q-1)==0) sum+=p*q;
            }
        }
        return sum;
    }
    
    long multi(int f,int pi,long p,long phi){
        long q,sum=0;
        if(f>2&&(p-1)%(p-phi)==0) sum=p;
        for(int i=pi;i<pr.length;i++){
            q=pr[i];
            if(p*q>N) break;
            if(f==0&&q*q*q>N) break;
            if(f==1&&p*q*q>N) break;
            sum+=multi(f+1,i+1,p*q,phi*(q-1));                                  //(2)
        }
        return sum;
    }

}
