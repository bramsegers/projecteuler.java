package P601_P650;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import util.Util;

public class PE632 {

    public static void main(String[] args) {
        new PE632().solve((long)1e8);
        new PE632().solve((long)1e16);
    } 
    
    long N;
    int[] primes;
    long[] tot=new long[30];
    List<Integer> excl=new ArrayList<>(), comb=new ArrayList<>();
            
    void solve(long n){
        N=n;
        tot[0]=N;
        primes=Util.primes((int)Math.sqrt(n));
        search(1,0,new int[0],0);
        long p=1, mod=1000000007L;
        for(int i=0;tot[i]>0;i++){
            System.out.println(i+" "+(tot[i]-tot[i+1]));
            p*=(tot[i]-tot[i+1])%mod;
            p%=mod; 
        }
        System.out.format("P(%d)=%d%n%n",N,p);
    }    
    
    
    void search(long p, int pi, int[] sol, int len) {
        long np=N/p;
        if(len>0){
            excl.clear();
            for(int j=0,i=0;i<primes.length;i++){
                int q=primes[i];
                if(q>=sol[len-1]) break;
                if((long)q*q>np) break;
                if(q==sol[j]) j++;
                else excl.add(q);
            }
            long tsum=np;
            long sign=-1;
            long snp=(long)Math.sqrt(np);
            for(int i=1;i<=excl.size();i++){
                comb.clear();
                combine(1,0,i,snp);
                if(comb.isEmpty()) break;
                long sum=0;
                for(int r:comb) sum+=np/((long)r*r);
                tsum+=sign*sum;
                sign=-sign;
            }
            tot[len]+=tsum;
        }        
        for(int i=pi;i<primes.length;i++){
            int p2=primes[i];
            if((long)p2*p2>np) break;
            int[] sol2=Arrays.copyOf(sol,len+1);
            sol2[len]=p2;
            search(p*p2*p2,i+1,sol2,len+1);
        }
    }

    void combine(int p, int pi, int level, long snp) {
        if(level==0) {comb.add(p);return;}
        for(int i=pi;i<excl.size();i++){
            int p2=excl.get(i);
            if(p*p2>snp) break;
            combine(p*p2,i+1,level-1,snp);
        }
    }
   
}