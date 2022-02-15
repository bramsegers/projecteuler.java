package P351_P400; 

import java.math.BigInteger;
import java.util.*;
import static util.Util.*;

public class PE354 {

    /*
     Let n=L^2/3, then we get an equation x^2+xy+y^2=n, which according to 
     http://www.mat.univie.ac.at/~slc/wpapers/s42hirsch.html 
     has the number of solutions equal to 6(d(n,1,3)−d(n,2,3)), 
     where d(n,r,m) is the number of divisors of n congruent to r modulo m.
     
     So we need to find all n with d(n,1,3)−d(n,2,3)=75=3∗5∗5.  
     So factorization of n:
      - can have any power of 3
      - can have even powers of prime numbers congruent to 2 modulo 3
      - must have powers of prime numbers congruent to 1 modulo 3,
        either p1^4*p2^4^p3^2 or p1^14*p2^4 or p1^24*p2^2 or p1^74.
    */
    public static void main(String[] args) {
        timer();
        new PE354().solve((long)5e11);
        print(timer());
    }
    
    List<Integer> pm1,pm2;
    TreeSet<BigInteger> set1,set2;
    BigInteger N;
    
    void solve(long M) {
        
        //1. init globals
        pm1=new ArrayList<>();
        pm2=new ArrayList<>();
        set1=new TreeSet<>();
        set2=new TreeSet<>();
        N=bi(M).pow(2).divide(bi(3));
        
        //2. init primes ≡ 1 or 2 (mod3)
        int P=(int)(M/(7*7*13*13*Math.sqrt(3)));
        for(int p:primes(P)){
            if(p%3==1)pm1.add(p);
            if(p%3==2)pm2.add(p);
        }
        
        //3. obligatory part of n (primes ≡ 1 (mod3)) 
        set1(4,4,2);
        set1(14,4);
        set1(24,2);
        set1(74);
               
        //4. optional part of n (any power of 3, even number of primes ≡ 2 (mod3))
        long q=3L*7*7*7*7*13*13*13*13*19*19;
        set2(0,bi(1),bi(M).pow(2).divide(bi(q)));        
        
        //5. combine parts
        long count=0;
        for(BigInteger b1:set1){
            for(BigInteger b2:set2){
                BigInteger n=b1.multiply(b2);
                if(n.compareTo(N)>0) break;
                count++;
            }
        }
        print(M,count);
    }   
        
    void set1(int... e){
        BigInteger T1,T2,T3;
        for (int p1:pm1) {
            T1=bi(p1).pow(e[0]);
            if(T1.compareTo(N)>0) break;
            if(e.length==1) {set1.add(T1);continue;}
            for (int p2:pm1) {
                if(p2==p1) continue;
                T2=T1.multiply(bi(p2).pow(e[1]));
                if(T2.compareTo(N)>0) break;
                if(e.length==2) {set1.add(T2);continue;}
                for (int p3:pm1) {
                    if(p3==p1||p3==p2) continue;
                    T3=T2.multiply(bi(p3).pow(e[2]));
                    if(T3.compareTo(N)>0) break;
                    set1.add(T3);        
                }           
            }
        }        
    }
    
    void set2(int i,BigInteger p,BigInteger N) {
        BigInteger p2,q=bi(pm2.get(i)).pow(2);
        if(p.multiply(q).compareTo(N)>0){
            while(p.compareTo(N)<=0){
                set2.add(p);
                p=p.multiply(bi(3));
            }
            return;
        }
        set2(i+1,p,N);
        p2=p.multiply(q);
        while(p2.compareTo(N)<=0){
            set2(i+1,p2,N);
            p2=p2.multiply(q);
        }
    }

}
