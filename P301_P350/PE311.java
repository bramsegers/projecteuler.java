package P301_P350; 

import java.util.ArrayList;
import java.util.List; 
import java.util.TreeSet;
import static util.Util.*;

public class PE311 {

    public static void main(String[] args) {
        new PE311().solve((long)1e10);
    } 
     
    long count;
    List<Integer> pm1,pm3;    
    TreeSet<Long> Q;
    
    void solve(long N){        
        
        int[] primes=primes((int)(N/(4*5*5)));        
        pm1=new ArrayList<>();
        pm3=new ArrayList<>(); 
        for(int p:primes){
            if(p%4==1) pm1.add(p);
            if(p%4==3) pm3.add(p);
        }        
        
        Q=new TreeSet<>();
        solveQ(new ArrayList<>(),1,0,N/4);
        
        int s=pm1.size(); 
        for (int i=0;i<s;i++){
            for (int j=i;j<s&&p(i)*p(j)<=N/4;j++){
                for (int k=j;k<s&&p(i)*p(j)*p(k)<=N/4;k++){
                    List<Long> list=new ArrayList<>();
                    list.add(p(i));
                    list.add(p(j));
                    list.add(p(k));                   
                    solveP(list,k,p(i)*p(j)*p(k),N/4); 
                }   
            }   
        }
        print(count); 
    }
    
    void solveP(List<Long> list, int i, long p, long N) {        
        long P=P(list);
        for (long q:Q) {
            if(p*q>N) break;
            long e=0,m2=1;
            while(p*q*m2<=N){ 
                count+=sum(P,P%2==1&&e%2==1);
                m2*=2;
                e++;
            }
        }        
        for (int j=i;j<pm1.size()&&p*p(j)<=N;j++) {
            list.add(p(j));
            solveP(list,j,p*p(j),N);
            list.remove(p(j));
        }
    }   
    
    void solveQ(List<Long> list, long q, int i, long N){
        Q.add(q);
        for (int j=i;q*q(j)*q(j)<=N;j++) {
            list.add(q(j));
            solveQ(list,q*q(j)*q(j),j,N);
            list.remove(q(j));
        }
    }
     
    long p(int i){
        return pm1.get(i);
    }
    
    long q(int i){
        return pm3.get(i);
    }
    
    long P(List<Long> list){        
        long p=1,i=0,last=0;
        for(long n:list){
            if(last==0||last==n) i++; 
            else {p*=i+1;i=1;} 
            last=n;
        }
        return p*(i+1);       
    }
    
    long sum(long P,boolean dsq){
        if(dsq) P++;
        P/=2; 
        return P*(P-1)*(P-2)/6;
    }
    
}
