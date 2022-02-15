package P601_P650;

import java.math.BigInteger;
import java.util.TreeSet;  
import static util.Util.*;
import static java.lang.Math.max;
import static java.lang.Math.max;
import static java.lang.Math.max;
import static java.lang.Math.max;

public class PE615 {
    
    /*
        - Hardcode upper bound P for max prime factor
        - Hardcode upper bound Q for max #prime factors != 2
        - BigInteger the shit out of all the mess            
    */
    public static void main(String[] args) {
        new PE615().solve(1000000,123454321);
    }
    
    int P=1000000,Q=100;
    int[] pr=primes(P); 
    TreeSet<BigInteger> set=new TreeSet<>();
    
    void solve(int N, int M) {         
        BigInteger p=bi(2).pow(Q-1);
        for (int n=2;n<=N+1;n++) set.add(p.multiply(bi(n)));
        for (int i=Q-2;i>0;i--) search(bi(2).pow(i),i,1);           
        p=set.last().multiply(bi(2).pow(N-Q));
        print(p.mod(bi(M)));   
    }    
    
    void search(BigInteger p,int f,int pi) {
        if(f>=Q) { set.add(p);set.pollLast(); }
        BigInteger m,q,p1,p2;
        for (int i=pi;;i++) {
            m=set.last();
            q=bi(pr[i]);
            p1=p.multiply(q);
            p2=p.multiply(q.pow(max(0,Q-f)));
            if(p1.compareTo(m)>0) break;
            if(p2.compareTo(m)>0) break; 
            search(p1,f+1,i);
        }
    }

}
